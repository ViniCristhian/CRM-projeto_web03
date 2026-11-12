import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { Client } from '../../core/model';
import { ClientService } from '../client.service';

@Component({
  selector: 'app-client-register',
  standalone: true,
  imports: [FormsModule, InputTextModule, ButtonModule, RouterModule],
  templateUrl: './client-register.component.html',
  styleUrl: './client-register.component.css'
})
export class ClientRegisterComponent implements OnInit {
  client = new Client();
  isEdit = false;

  constructor(
    private clientService: ClientService,
    private router: Router,
    private route: ActivatedRoute,
    private title: Title
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.isEdit = !!id;
    this.title.setTitle(this.isEdit ? 'Editar cliente' : 'Cadastro de cliente');

    if (id) {
      this.clientService.findById(Number(id)).then((client) => {
        this.client = client;
      });
    }
  }

  save(): void {
    this.clientService.save(this.client).then(() => {
      this.router.navigate(['/clients']);
    });
  }
}
