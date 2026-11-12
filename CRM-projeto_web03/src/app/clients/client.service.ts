import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { Client } from '../core/model';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private readonly baseUrl = 'http://localhost:8080/conta';

  constructor(private http: HttpClient) {}

  private mapConta(conta: any): Client {
    return {
      id: conta.id,
      nome: conta.nomeEmpresa || conta.nome || '',
      email: conta.email || '',
      telefone: conta.telefone || '',
      empresa: conta.nomeEmpresa || conta.empresa || '',
      nomeEmpresa: conta.nomeEmpresa || conta.empresa || '',
      cnpj: conta.cnpj || '',
      setor: conta.setor || { id: 1 },
      status: 'Ativo'
    };
  }

  async listAll(): Promise<Client[]> {
    const contas = await firstValueFrom(this.http.get<any[]>(`${this.baseUrl}/list`));
    return contas.map((conta) => this.mapConta(conta));
  }

  async findById(id: number): Promise<Client> {
    const conta = await firstValueFrom(this.http.get<any>(`${this.baseUrl}/find/${id}`));
    return this.mapConta(conta);
  }

  async save(client: Client): Promise<Client> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const payload = {
      id: client.id,
      nomeEmpresa: client.nomeEmpresa || client.empresa || client.nome,
      cnpj: client.cnpj || '',
      setor: client.setor || { id: 1 }
    };

    const request$ = client.id
      ? this.http.put<any>(`${this.baseUrl}/update/${client.id}`, payload, { headers, withCredentials: true })
      : this.http.post<any>(`${this.baseUrl}/register`, payload, { headers, withCredentials: true });

    const conta = await firstValueFrom(request$);
    return this.mapConta(conta);
  }

  async delete(id: number): Promise<void> {
    await firstValueFrom(this.http.delete<void>(`${this.baseUrl}/delete/${id}`, { withCredentials: true }));
  }
}
