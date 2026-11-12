import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { TableModule } from 'primeng/table';
import { Opportunity } from '../../core/model';
import { OpportunityService } from '../opportunity.service';

@Component({
  selector: 'app-opportunity-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, ButtonModule, TableModule, InputTextModule],
  templateUrl: './opportunity-list.component.html',
  styleUrl: './opportunity-list.component.css'
})
export class OpportunityListComponent implements OnInit {
  opportunities: Opportunity[] = [];
  filtro = '';

  constructor(private opportunityService: OpportunityService) {}

  ngOnInit(): void {
    this.list();
  }

  async list(): Promise<void> {
    try {
      this.opportunities = await this.opportunityService.listAll();
    } catch {
      this.opportunities = [];
    }
  }

  get filteredOpportunities(): Opportunity[] {
    if (!this.filtro) {
      return this.opportunities;
    }

    const termo = this.filtro.toLowerCase();
    return this.opportunities.filter((opportunity) =>
      (opportunity.nomeNegocio || '').toLowerCase().includes(termo) ||
      (opportunity.conta?.nomeEmpresa || '').toLowerCase().includes(termo)
    );
  }

  async delete(id: number | undefined): Promise<void> {
    if (!id) {
      return;
    }

    const shouldDelete = window.confirm('Deseja excluir esta oportunidade?');
    if (!shouldDelete) {
      return;
    }

    await this.opportunityService.delete(id);
    this.list();
  }
}
