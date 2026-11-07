import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router, RouterModule } from '@angular/router';

import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';

import { ErrorHandlerService } from '../../core/error-handler.service';
import { User } from '../../core/model';
import { MessageComponent } from '../../shared/message/message.component';
import { AuthService } from '../../security/auth.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-register',
  standalone: true,
  imports: [
    FormsModule,
    InputTextModule,
    ButtonModule,
    SelectModule,
    RouterModule,
    MessageComponent
  ],
  templateUrl: './user-register.component.html',
  styleUrl: './user-register.component.css'
})
export class UserRegisterComponent {

  user = new User();

  constructor(
    private userService: UserService,
    private errorHandler: ErrorHandlerService,
    private messageService: MessageService,
    private router: Router,
    private title: Title,
    private auth: AuthService
  ){}

  ngOnInit(): void {
    this.title.setTitle('Cadastro de Usuário');
  }

  save() {
    this.userService.add(this.user)
      .then(async () => {
        this.messageService.add({ severity: 'success', detail: 'Usuário adicionado com sucesso!' });

        try {
          await this.auth.login(this.user.email, this.user.senha);
          this.router.navigate(['/home']);
        } catch {
          this.router.navigate(['/login']);
        }
      })
      .catch(error => this.errorHandler.handle(error));
  }

}