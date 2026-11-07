import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { forkJoin } from 'rxjs';
import { AuthService } from '../security/auth.service';

interface DashboardStat {
  label: string;
  value: string;
  color: string;
  details: string;
}

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  stats: DashboardStat[] = [
    { label: 'Clientes ativos', value: '0', color: 'blue', details: 'Carregando...' },
    { label: 'Contatos registrados', value: '0', color: 'green', details: 'Carregando...' },
    { label: 'Oportunidades em aberto', value: '0', color: 'blue', details: 'Carregando...' },
    { label: 'Taxa de conversão', value: '0%', color: 'green', details: 'Carregando...' }
  ];

  constructor(
    public auth: AuthService,
    private http: HttpClient
  ) {
    this.loadDashboardData();
  }

  get username(): string {
    return this.auth.jwtPayload?.nome || this.auth.jwtPayload?.name || this.auth.jwtPayload?.sub || 'Usuário';
  }

  private loadDashboardData(): void {
    forkJoin({
      contas: this.http.get<any[]>('http://localhost:8080/conta/list'),
      contatos: this.http.get<any[]>('http://localhost:8080/contato/list'),
      oportunidades: this.http.get<any[]>('http://localhost:8080/oportunidade/list'),
      usuarios: this.http.get<any[]>('http://localhost:8080/usuario/list')
    }).subscribe({
      next: ({ contas, contatos, oportunidades, usuarios }) => {
        const totalContas = contas?.length ?? 0;
        const totalContatos = contatos?.length ?? 0;
        const totalOportunidades = oportunidades?.length ?? 0;
        const abertas = (oportunidades ?? []).filter((oportunidade) => {
          const estagio = oportunidade?.estagioVenda?.estagio ?? '';
          return estagio !== 'Fechado Ganho' && estagio !== 'Fechado Perdido';
        }).length;
        const fechadas = (oportunidades ?? []).filter((oportunidade) => {
          return oportunidade?.estagioVenda?.estagio === 'Fechado Ganho';
        }).length;
        const taxaConversao = totalOportunidades > 0 ? Math.round((fechadas / totalOportunidades) * 100) : 0;

        this.stats = [
          { label: 'Clientes ativos', value: String(totalContas), color: 'blue', details: `${totalContas} empresas cadastradas` },
          { label: 'Contatos registrados', value: String(totalContatos), color: 'green', details: `${totalContatos} contatos ativos` },
          { label: 'Oportunidades em aberto', value: String(abertas), color: 'blue', details: `${fechadas} fechadas` },
          { label: 'Taxa de conversão', value: `${taxaConversao}%`, color: 'green', details: `${usuarios?.length ?? 0} usuários ativos` }
        ];
      },
      error: () => {
        this.stats = [
          { label: 'Clientes ativos', value: '0', color: 'blue', details: 'Sem dados no banco' },
          { label: 'Contatos registrados', value: '0', color: 'green', details: 'Sem dados no banco' },
          { label: 'Oportunidades em aberto', value: '0', color: 'blue', details: 'Sem dados no banco' },
          { label: 'Taxa de conversão', value: '0%', color: 'green', details: 'Sem dados no banco' }
        ];
      }
    });
  }
}
