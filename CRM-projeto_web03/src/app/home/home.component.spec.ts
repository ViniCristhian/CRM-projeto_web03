import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AuthService } from '../security/auth.service';
import { HomeComponent } from './home.component';

describe('HomeComponent', () => {
  let fixture: ComponentFixture<HomeComponent>;
  let httpMock: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomeComponent, HttpClientTestingModule],
      providers: [
        {
          provide: AuthService,
          useValue: {
            jwtPayload: { nome: 'Guilherme Santos' }
          }
        }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(HomeComponent);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should load dashboard metrics from the backend', () => {
    const contasReq = httpMock.expectOne('http://localhost:8080/conta/list');
    contasReq.flush([
      { id: 1 },
      { id: 2 },
      { id: 3 },
      { id: 4 }
    ]);

    const contatosReq = httpMock.expectOne('http://localhost:8080/contato/list');
    contatosReq.flush([
      { id: 1 },
      { id: 2 },
      { id: 3 },
      { id: 4 },
      { id: 5 }
    ]);

    const oportunidadesReq = httpMock.expectOne('http://localhost:8080/oportunidade/list');
    oportunidadesReq.flush([
      { id: 1, estagioVenda: { estagio: 'Prospecção' } },
      { id: 2, estagioVenda: { estagio: 'Fechado Ganho' } },
      { id: 3, estagioVenda: { estagio: 'Negociação' } }
    ]);

    const usuariosReq = httpMock.expectOne('http://localhost:8080/usuario/list');
    usuariosReq.flush([
      { id: 1 },
      { id: 2 }
    ]);

    fixture.detectChanges();

    expect(fixture.componentInstance.stats[0].value).toBe('4');
    expect(fixture.componentInstance.stats[1].value).toBe('5');
    expect(fixture.componentInstance.stats[2].value).toBe('2');
    expect(fixture.componentInstance.stats[3].value).toBe('33%');
  });
});
