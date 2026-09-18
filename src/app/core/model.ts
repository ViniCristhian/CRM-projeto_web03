import moment from "moment"

export class User {
  id!: number;
  nome!: string;
  email!: string;
  senha!: string;
  data!: Date;
  ativo = true;

  static toJson(user: User): any {
    return {
      id: user.id,
      nome: user.nome,
      email: user.email,
      senha: user.nome,
      data: moment(user.data).format('DD/MM/YYYY'),
      ativo: user.ativo
    }
  }
}