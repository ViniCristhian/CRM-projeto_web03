import moment from 'moment';

export class User {
  id!: number;
  nome!: string;
  email!: string;
  senha!: string;
  ativo = true;

  static toJson(user: User): any {
    return {
      id: user.id,
      name: user.nome,
      email: user.email,
      password: user.senha,
      active: user.ativo
    }
  }
}