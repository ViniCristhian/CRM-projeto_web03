import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { Title } from '@angular/platform-browser';

import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';
import { MessageService } from 'primeng/api';

import { User } from '../../core/model';
import { UserService } from '../user.service';
import { ErrorHandlerService } from '../../core/error-handler.service';
import { MessageComponent } from '../../shared/message/message.component';

@Component({
  selector: 'app-user-register',
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
    private title: Title
  ){}

  ngOnInit(): void {
    this.title.setTitle('Cadastro de Usuário');
  }

  save() {
    this.userService.add(this.user)
      .then(() => {
        this.messageService.add({ severity: 'success', detail: 'Usuário adicionado com sucesso!' });
        this.router.navigate(['/login']);
      })
      .catch(error => this.errorHandler.handle(error));
  }

}