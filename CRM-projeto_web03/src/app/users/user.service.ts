import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';

import { User } from '../core/model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  readonly registerUrl = 'http://localhost:8080/usuario/register';
  readonly listUrl = 'http://localhost:8080/usuario/list';

  constructor(private http: HttpClient) { }

  async listAll(): Promise<User[]> {
    return firstValueFrom(this.http.get<User[]>(this.listUrl));
  }

  async add(user: User): Promise<User> {
      const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
      const body = User.toJson(user);
      return await firstValueFrom(this.http.post<User>(this.registerUrl, body, { headers, withCredentials: true }));
    }
}