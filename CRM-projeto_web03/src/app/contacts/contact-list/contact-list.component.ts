import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-contact-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './contact-list.component.html',
  styleUrl: './contact-list.component.css'
})
export class ContactListComponent {
  contacts = [
    { nome: 'Patrícia Almeida', cargo: 'Diretora de Vendas', email: 'patricia@empresa.com' },
    { nome: 'Ricardo Nunes', cargo: 'Gerente de Conta', email: 'ricardo@empresa.com' },
    { nome: 'Lívia Costa', cargo: 'Coordenadora Comercial', email: 'livia@empresa.com' }
  ];
}
