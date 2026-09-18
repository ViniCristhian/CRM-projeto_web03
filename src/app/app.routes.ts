import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginFormComponent } from './security/login-form/login-form.component';
import { UserRegisterComponent } from './users/user-register/user-register.component';

export const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'users/new', component: UserRegisterComponent },
  { path: 'login', component: LoginFormComponent },
  //{ path: '**', redirectTo: 'login' }
];
