import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../security/auth.service';

@Component({
  selector: 'app-default-route',
  standalone: true,
  template: ''
})
export class DefaultRouteComponent implements OnInit {
  constructor(
    private router: Router,
    private auth: AuthService
  ) {}

  ngOnInit(): void {
    this.router.navigate([this.auth.isInvalidAccessToken() ? '/login' : '/home']);
  }
}
