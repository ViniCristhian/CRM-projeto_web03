import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { AuthService } from '../security/auth.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  readonly stats = [
    { label: 'Clientes ativos', value: '128', color: 'blue', details: '+12% este mês' },
    { label: 'Contatos registrados', value: '482', color: 'green', details: '+18 novos' },
    { label: 'Oportunidades em aberto', value: '36', color: 'blue', details: '7 prioritárias' },
    { label: 'Taxa de conversão', value: '24%', color: 'green', details: 'meta: 30%' }
  ];

  constructor(public auth: AuthService) {}

  get username(): string {
    return this.auth.jwtPayload?.nome || this.auth.jwtPayload?.name || this.auth.jwtPayload?.sub || 'Usuário';
  }
}
