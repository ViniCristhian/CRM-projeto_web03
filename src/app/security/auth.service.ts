import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  oauthTokenUrl = 'http://localhost:8080/auth/login';
  refreshTokenUrl = 'http://localhost:8080/auth/refresh';
  logoutUrl = 'http://localhost:8080/auth/logout';
  jwtPayload: any = null;

  constructor(
    private http: HttpClient,
    private jwtHelper: JwtHelperService
  ) {
    this.loadToken();
  }

  async login(user: string, password: string): Promise<void> {
    const headers = new HttpHeaders()
      .append('Content-Type', 'application/json');

    const body = {
      email: user,
      senha: password
    };

    try {
      const response: any = await firstValueFrom(
        this.http.post(this.oauthTokenUrl, body, { headers, withCredentials: true })
      );
      this.storeToken(response['accessToken'] || response['token']);
    } catch (response: any) {
      if (response.status === 400 && response.error === 'invalid_grant') {
        return Promise.reject('Usuário e/ou senha inválida!');
      }
      return Promise.reject(response);
    }
  }

  private storeToken(token: string): void {
    if (!token) {
      this.jwtPayload = null;
      localStorage.removeItem('token');
      return;
    }

    this.jwtPayload = this.jwtHelper.decodeToken(token);
    localStorage.setItem('token', token);
  }

  private loadToken(): void {
    const token = localStorage.getItem('token');

    if (token) {
      this.storeToken(token);
    }
  }

  async getNewAccessToken(): Promise<void> {
    const headers = new HttpHeaders()
      .append('Content-Type', 'application/json');

    const body = {};

    try {
      const response: any = await firstValueFrom(
        this.http.post(this.refreshTokenUrl, body, { headers, withCredentials: true })
      );
      this.storeToken(response['accessToken'] || response['token']);
    } catch (response: any) {
      if (response.status === 400 && response.error === 'invalid_grant') {
        return Promise.reject('Erro ao renovar token.');
      }
      return Promise.reject(response);
    }
  }

  isInvalidAccessToken(): boolean {
    const token = localStorage.getItem('token');
    if (!token) {
      return true;
    }

    return this.jwtHelper.isTokenExpired(token);
  }

  clearAccessToken(): void {
    this.jwtPayload = null;
    localStorage.removeItem('token');
  }

  async logout(): Promise<void> {
    this.clearAccessToken();

    try {
      await firstValueFrom(
        this.http.post(this.logoutUrl, {}, { withCredentials: true })
      );
    } catch {
      // Ignora falha do servidor e mantém o usuário deslogado no frontend.
    }
  }
}


