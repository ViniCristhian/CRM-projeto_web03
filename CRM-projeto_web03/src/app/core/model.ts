export class User {
  id!: number;
  nome!: string;
  email!: string;
  senha!: string;
  ativo = true;

  static toJson(user: User): any {
    return {
      id: user.id,
      nome: user.nome,
      email: user.email,
      senha: user.senha,
      ativo: user.ativo
    };
  }
}

export class Client {
  id!: number;
  nome!: string;
  email!: string;
  telefone!: string;
  empresa!: string;
  status = 'Ativo';
}
