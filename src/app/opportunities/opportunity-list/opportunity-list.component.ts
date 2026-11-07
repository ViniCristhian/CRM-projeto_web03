import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-opportunity-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './opportunity-list.component.html',
  styleUrl: './opportunity-list.component.css'
})
export class OpportunityListComponent {
  opportunities = [
    { nome: 'Expansão Norte', valor: 'R$ 48.000', status: 'Em prospecção' },
    { nome: 'Novo contrato corporativo', valor: 'R$ 72.500', status: 'Negociação' },
    { nome: 'Migração da operação', valor: 'R$ 36.000', status: 'Aguardando aprovação' }
  ];
}
