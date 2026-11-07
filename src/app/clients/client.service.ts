import { Injectable } from '@angular/core';
import { Client } from '../core/model';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private clients: Client[] = [
    { id: 1, nome: 'Maria Santos', email: 'maria@greencrm.com', telefone: '(11) 99999-1111', empresa: 'GreenLeaf', status: 'Ativo' },
    { id: 2, nome: 'João Pereira', email: 'joao@bluewave.com', telefone: '(11) 98888-2222', empresa: 'BlueWave', status: 'Potencial' },
    { id: 3, nome: 'Ana Costa', email: 'ana@northview.com', telefone: '(11) 97777-3333', empresa: 'NorthView', status: 'Ativo' }
  ];

  listAll(): Promise<Client[]> {
    return Promise.resolve([...this.clients]);
  }

  save(client: Client): Promise<Client> {
    if (client.id) {
      this.clients = this.clients.map(item => item.id === client.id ? client : item);
      return Promise.resolve(client);
    }

    client.id = this.clients.length ? Math.max(...this.clients.map(item => item.id)) + 1 : 1;
    this.clients = [...this.clients, { ...client }];
    return Promise.resolve(client);
  }
}
