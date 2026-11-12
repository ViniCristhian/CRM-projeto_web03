import { Routes } from '@angular/router';
import { PageNotFoundComponent } from './core/page-not-found.component';
import { ClientListComponent } from './clients/client-list/client-list.component';
import { ClientRegisterComponent } from './clients/client-register/client-register.component';
import { HomeComponent } from './home/home.component';
import { LoginFormComponent } from './security/login-form/login-form.component';
import { authGuard } from './security/auth.guard';
import { UserRegisterComponent } from './users/user-register/user-register.component';
import { ContactListComponent } from './contacts/contact-list/contact-list.component';
import { ContactRegisterComponent } from './contacts/contact-register/contact-register.component';
import { OpportunityListComponent } from './opportunities/opportunity-list/opportunity-list.component';
import { OpportunityRegisterComponent } from './opportunities/opportunity-register/opportunity-register.component';
import { DefaultRouteComponent } from './core/default-route.component';

export const routes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [authGuard] },
  { path: 'clients', component: ClientListComponent, canActivate: [authGuard], data: { roles: ['ROLE_SEARCH_ACCOUNT'] } },
  { path: 'clients/new', component: ClientRegisterComponent, canActivate: [authGuard], data: { roles: ['ROLE_REGISTER_ACCOUNT'] } },
  { path: 'clients/:id/edit', component: ClientRegisterComponent, canActivate: [authGuard], data: { roles: ['ROLE_REGISTER_ACCOUNT'] } },
  { path: 'contacts', component: ContactListComponent, canActivate: [authGuard], data: { roles: ['ROLE_SEARCH_CONTACT'] } },
  { path: 'contacts/new', component: ContactRegisterComponent, canActivate: [authGuard], data: { roles: ['ROLE_REGISTER_CONTACT'] } },
  { path: 'contacts/:id/edit', component: ContactRegisterComponent, canActivate: [authGuard], data: { roles: ['ROLE_REGISTER_CONTACT'] } },
  { path: 'opportunities', component: OpportunityListComponent, canActivate: [authGuard], data: { roles: ['ROLE_SEARCH_OPPORTUNITY'] } },
  { path: 'opportunities/new', component: OpportunityRegisterComponent, canActivate: [authGuard], data: { roles: ['ROLE_REGISTER_OPPORTUNITY'] } },
  { path: 'opportunities/:id/edit', component: OpportunityRegisterComponent, canActivate: [authGuard], data: { roles: ['ROLE_REGISTER_OPPORTUNITY'] } },
  { path: 'users/new', component: UserRegisterComponent },
  { path: 'login', component: LoginFormComponent },
  { path: '', component: DefaultRouteComponent },
  { path: '**', component: PageNotFoundComponent }
];
