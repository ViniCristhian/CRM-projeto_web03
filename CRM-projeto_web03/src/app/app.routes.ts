import { Routes } from '@angular/router';
import { PageNotFoundComponent } from './core/page-not-found.component';
import { ClientListComponent } from './clients/client-list/client-list.component';
import { ClientRegisterComponent } from './clients/client-register/client-register.component';
import { HomeComponent } from './home/home.component';
import { LoginFormComponent } from './security/login-form/login-form.component';
import { authGuard } from './security/auth.guard';
import { UserRegisterComponent } from './users/user-register/user-register.component';
import { ContactListComponent } from './contacts/contact-list/contact-list.component';
import { OpportunityListComponent } from './opportunities/opportunity-list/opportunity-list.component';
import { DefaultRouteComponent } from './core/default-route.component';

export const routes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [authGuard] },
  { path: 'clients', component: ClientListComponent, canActivate: [authGuard] },
  { path: 'clients/new', component: ClientRegisterComponent, canActivate: [authGuard] },
  { path: 'contacts', component: ContactListComponent, canActivate: [authGuard] },
  { path: 'opportunities', component: OpportunityListComponent, canActivate: [authGuard] },
  { path: 'users/new', component: UserRegisterComponent },
  { path: 'login', component: LoginFormComponent },
  { path: '', component: DefaultRouteComponent },
  { path: '**', component: PageNotFoundComponent }
];
