import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { Contact } from '../core/model';

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  private readonly baseUrl = 'http://localhost:8080/contato';

  constructor(private http: HttpClient) {}

  async listAll(): Promise<Contact[]> {
    return firstValueFrom(this.http.get<Contact[]>(`${this.baseUrl}/list`));
  }

  async findById(id: number): Promise<Contact> {
    return firstValueFrom(this.http.get<Contact>(`${this.baseUrl}/find/${id}`));
  }

  async save(contact: Contact): Promise<Contact> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const payload = {
      ...contact,
      cargo: contact.cargo ?? { id: 1 },
      conta: contact.conta ?? { id: 1 }
    };

    const request$ = contact.id
      ? this.http.put<Contact>(`${this.baseUrl}/update/${contact.id}`, payload, { headers, withCredentials: true })
      : this.http.post<Contact>(`${this.baseUrl}/register`, payload, { headers, withCredentials: true });

    return firstValueFrom(request$);
  }

  async delete(id: number): Promise<void> {
    await firstValueFrom(this.http.delete<void>(`${this.baseUrl}/delete/${id}`, { withCredentials: true }));
  }
}
