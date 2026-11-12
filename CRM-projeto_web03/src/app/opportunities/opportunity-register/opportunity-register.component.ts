import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { Opportunity } from '../../core/model';
import { OpportunityService } from '../opportunity.service';

@Component({
  selector: 'app-opportunity-register',
  standalone: true,
  imports: [CommonModule, FormsModule, InputTextModule, ButtonModule, RouterModule],
  templateUrl: './opportunity-register.component.html'
})
export class OpportunityRegisterComponent implements OnInit {
  opportunity = new Opportunity();
  isEdit = false;

  constructor(
    private readonly route: ActivatedRoute,
    private readonly router: Router,
    private readonly title: Title,
    private readonly opportunityService: OpportunityService
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    this.isEdit = !!idParam;
    this.title.setTitle(this.isEdit ? 'Editar oportunidade' : 'Nova oportunidade');

    if (idParam) {
      this.opportunityService.findById(Number(idParam)).then((opportunity) => {
        this.opportunity = opportunity;
      });
    }
  }

  async save(): Promise<void> {
    try {
      this.opportunity.conta ??= { id: 1 };
      this.opportunity.estagioVenda ??= { id: 1 };
      await this.opportunityService.save(this.opportunity);
      this.router.navigate(['/opportunities']);
    } catch (error) {
      console.error(error);
    }
  }
}
