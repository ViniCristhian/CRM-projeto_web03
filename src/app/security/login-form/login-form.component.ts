import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    InputTextModule,
    ButtonModule,
    RouterModule
  ],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.css'
})
export class LoginFormComponent {
  msg = '';

  constructor(
    private auth: AuthService,
    private router: Router
  ) {}

  async login(user: string, password: string): Promise<void> {
    if (!user || !password) {
      this.msg = 'Informe o e-mail e a senha.';
      return;
    }

    try {
      await this.auth.login(user, password);
      this.router.navigate(['/home']);
    } catch (error: any) {
      this.msg = typeof error === 'string' ? error : 'Usuário e/ou senha inválida!';
    }
  }
}
