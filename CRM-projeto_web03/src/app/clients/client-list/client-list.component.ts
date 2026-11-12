import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { TableModule } from 'primeng/table';
import { Client } from '../../core/model';
import { ClientService } from '../client.service';

@Component({
  selector: 'app-client-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, ButtonModule, TableModule, InputTextModule],
  templateUrl: './client-list.component.html',
  styleUrl: './client-list.component.css'
})
export class ClientListComponent implements OnInit {
  clients: Client[] = [];
  filtro = '';

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.list();
  }

  list(): void {
    this.clientService.listAll().then((result) => this.clients = result);
  }

  get filteredClients(): Client[] {
    if (!this.filtro) {
      return this.clients;
    }

    const termo = this.filtro.toLowerCase();
    return this.clients.filter((client) =>
      (client.nomeEmpresa || client.empresa || '').toLowerCase().includes(termo) ||
      (client.cnpj || '').toLowerCase().includes(termo)
    );
  }

  async delete(id: number | undefined): Promise<void> {
    if (!id) {
      return;
    }

    const shouldDelete = window.confirm('Deseja excluir este cliente?');
    if (!shouldDelete) {
      return;
    }

    await this.clientService.delete(id);
    this.list();
  }
}
