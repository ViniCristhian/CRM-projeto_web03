import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { TableModule } from 'primeng/table';
import { Contact } from '../../core/model';
import { ContactService } from '../contact.service';

@Component({
  selector: 'app-contact-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, ButtonModule, TableModule, InputTextModule],
  templateUrl: './contact-list.component.html',
  styleUrl: './contact-list.component.css'
})
export class ContactListComponent implements OnInit {
  contacts: Contact[] = [];
  filtro = '';

  constructor(private contactService: ContactService) {}

  ngOnInit(): void {
    this.list();
  }

  async list(): Promise<void> {
    try {
      this.contacts = await this.contactService.listAll();
    } catch {
      this.contacts = [];
    }
  }

  get filteredContacts(): Contact[] {
    if (!this.filtro) {
      return this.contacts;
    }

    const termo = this.filtro.toLowerCase();
    return this.contacts.filter((contact) =>
      (contact.nome || '').toLowerCase().includes(termo) ||
      (contact.email || '').toLowerCase().includes(termo)
    );
  }

  async delete(id: number | undefined): Promise<void> {
    if (!id) {
      return;
    }

    const shouldDelete = window.confirm('Deseja excluir este contato?');
    if (!shouldDelete) {
      return;
    }

    await this.contactService.delete(id);
    this.list();
  }
}
