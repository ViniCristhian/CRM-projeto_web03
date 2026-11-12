import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { Opportunity } from '../core/model';

@Injectable({
  providedIn: 'root'
})
export class OpportunityService {
  private readonly baseUrl = 'http://localhost:8080/oportunidade';

  constructor(private http: HttpClient) {}

  async listAll(): Promise<Opportunity[]> {
    return firstValueFrom(this.http.get<Opportunity[]>(`${this.baseUrl}/list`));
  }

  async findById(id: number): Promise<Opportunity> {
    return firstValueFrom(this.http.get<Opportunity>(`${this.baseUrl}/find/${id}`));
  }

  async save(opportunity: Opportunity): Promise<Opportunity> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const payload = {
      ...opportunity,
      conta: opportunity.conta ?? { id: 1 },
      estagioVenda: opportunity.estagioVenda ?? { id: 1 }
    };

    const request$ = opportunity.id
      ? this.http.put<Opportunity>(`${this.baseUrl}/update/${opportunity.id}`, payload, { headers, withCredentials: true })
      : this.http.post<Opportunity>(`${this.baseUrl}/register`, payload, { headers, withCredentials: true });

    return firstValueFrom(request$);
  }

  async delete(id: number): Promise<void> {
    await firstValueFrom(this.http.delete<void>(`${this.baseUrl}/delete/${id}`, { withCredentials: true }));
  }
}
