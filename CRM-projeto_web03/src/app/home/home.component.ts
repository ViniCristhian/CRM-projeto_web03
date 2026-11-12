import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { forkJoin } from 'rxjs';
import { ClientService } from '../clients/client.service';
import { ContactService } from '../contacts/contact.service';
import { OpportunityService } from '../opportunities/opportunity.service';
import { AuthService } from '../security/auth.service';
import { UserService } from '../users/user.service';

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
    private clientService: ClientService,
    private contactService: ContactService,
    private opportunityService: OpportunityService,
    private userService: UserService
  ) {
    this.loadDashboardData();
  }

  get username(): string {
    return this.auth.jwtPayload?.nome || this.auth.jwtPayload?.name || this.auth.jwtPayload?.sub || 'Usuário';
  }

  private loadDashboardData(): void {
    forkJoin({
      contas: this.clientService.listAll(),
      contatos: this.contactService.listAll(),
      oportunidades: this.opportunityService.listAll(),
      usuarios: this.userService.listAll()
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
