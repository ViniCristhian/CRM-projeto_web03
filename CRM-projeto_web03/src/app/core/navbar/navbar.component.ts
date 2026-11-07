import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '../../security/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  displayingMenu = false;

  constructor(
    public auth: AuthService,
    private router: Router
  ) {}

  get username(): string {
    return this.auth.jwtPayload?.nome || this.auth.jwtPayload?.name || this.auth.jwtPayload?.sub || 'Usuário';
  }

  async logout(): Promise<void> {
    try {
      await this.auth.logout();
    } finally {
      this.displayingMenu = false;
      this.router.navigate(['/login']);
    }
  }
}
