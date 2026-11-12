import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { Contact } from '../../core/model';
import { ContactService } from '../contact.service';

@Component({
  selector: 'app-contact-register',
  standalone: true,
  imports: [CommonModule, FormsModule, InputTextModule, ButtonModule, RouterModule],
  templateUrl: './contact-register.component.html'
})
export class ContactRegisterComponent implements OnInit {
  contact = new Contact();
  isEdit = false;

  constructor(
    private readonly route: ActivatedRoute,
    private readonly router: Router,
    private readonly title: Title,
    private readonly contactService: ContactService
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    this.isEdit = !!idParam;
    this.title.setTitle(this.isEdit ? 'Editar contato' : 'Novo contato');

    if (idParam) {
      this.contactService.findById(Number(idParam)).then((contact) => {
        this.contact = contact;
      });
    }
  }

  async save(): Promise<void> {
    try {
      this.contact.cargo ??= { id: 1 };
      this.contact.conta ??= { id: 1 };
      await this.contactService.save(this.contact);
      this.router.navigate(['/contacts']);
    } catch (error) {
      console.error(error);
    }
  }
}
