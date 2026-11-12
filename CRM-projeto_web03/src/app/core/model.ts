export class User {
  id!: number;
  nome!: string;
  email!: string;
  senha!: string;
  ativo = true;
  perfil?: { id?: number; nome?: string };

  static toJson(user: User): any {
    return {
      id: user.id,
      nome: user.nome,
      email: user.email,
      senha: user.senha,
      ativo: user.ativo,
      perfil: user.perfil ?? { id: 4 }
    };
  }
}

export class Client {
  id!: number;
  nome!: string;
  email!: string;
  telefone!: string;
  empresa?: string;
  nomeEmpresa?: string;
  cnpj?: string;
  setor?: { id?: number; titulo?: string } | null;
  status = 'Ativo';
}

export class Contact {
  id!: number;
  nome!: string;
  email!: string;
  telefone?: string;
  cargo?: { id?: number; titulo?: string } | null;
  conta?: { id?: number; nomeEmpresa?: string } | null;
}

export class Opportunity {
  id!: number;
  nomeNegocio!: string;
  valor!: number | string;
  dataCriacao?: string;
  dataFechamento?: string | null;
  estagioVenda?: { id?: number; estagio?: string } | null;
  conta?: { id?: number; nomeEmpresa?: string } | null;
}
