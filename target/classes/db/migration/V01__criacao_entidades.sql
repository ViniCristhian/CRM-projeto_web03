-- Tabela de Perfis de Acesso
CREATE TABLE PERFIS
(
    id_perfil   INT PRIMARY KEY IDENTITY (1,1),
    nome_perfil VARCHAR(50) UNIQUE NOT NULL,
    descricao   VARCHAR(255)
)

-- Criação da Tabela de Usuários
CREATE TABLE USUARIOS
(
    id_usuario   INT PRIMARY KEY IDENTITY (1,1),
    nome         VARCHAR(100)        NOT NULL,
    email        VARCHAR(100)        NOT NULL,
    senha        VARCHAR(255) UNIQUE NOT NULL,
    id_perfil    INT                 NOT NULL DEFAULT 4 FOREIGN KEY REFERENCES PERFIS (id_perfil) ,
    ativo        BIT                          DEFAULT 1,
    data_criacao DATE                NOT NULL DEFAULT GETDATE()
)

-- Criação da Tabela de Setores
CREATE TABLE SETORES
(
    id_setor  INT PRIMARY KEY IDENTITY (1,1),
    titulo    VARCHAR(50) UNIQUE NOT NULL,
    descricao VARCHAR(255)
)

-- Criação da Tabela de Cargos
CREATE TABLE CARGOS
(
    id_cargo  INT PRIMARY KEY IDENTITY (1,1),
    titulo    VARCHAR(50) UNIQUE NOT NULL,
    descricao VARCHAR(255)
)

-- Criação da Tabela de Tipos de Interação
CREATE TABLE TIPOS_INTERACAO
(
    id_tipo_interacao INT PRIMARY KEY IDENTITY (1,1),
    tipo              VARCHAR(50) UNIQUE NOT NULL,
    descricao         VARCHAR(255)
)

-- Criação da Tabela de Estágios de Venda
CREATE TABLE ESTAGIOS_VENDA
(
    id_estagio INT PRIMARY KEY IDENTITY (1,1),
    estagio    VARCHAR(50) UNIQUE NOT NULL,
    descricao  VARCHAR(255)
)

-- Criação da Tabela de Status de Tarefa
CREATE TABLE STATUS_TAFERA
(
    id_status_tarefa INT PRIMARY KEY IDENTITY (1,1),
    status           VARCHAR(50) UNIQUE NOT NULL,
    descricao        VARCHAR(255)
)

-- Criação da Tabela de Contas
CREATE TABLE CONTAS
(
    id_conta      INT PRIMARY KEY IDENTITY (1, 1),
    nome_empresa  VARCHAR(150) NOT NULL,
    cnpj          VARCHAR(18)  NOT NULL UNIQUE,
    data_cadastro DATETIME DEFAULT GETDATE(),
    id_setor      INT          NOT NULL FOREIGN KEY REFERENCES SETORES (id_setor)
)

-- Criação da Tabela de Contatos
CREATE TABLE CONTATOS
(
    id_contato INT PRIMARY KEY IDENTITY (1,1),
    nome       VARCHAR(100) NOT NULL,
    email      VARCHAR(100) NOT NULL,
    telefone   VARCHAR(20),
    id_cargo   INT          NOT NULL FOREIGN KEY REFERENCES CARGOS (id_cargo),
    id_conta   INT          NOT NULL FOREIGN KEY REFERENCES CONTAS (id_conta)
)

-- Criação da Tabela de Oportunidades
CREATE TABLE OPORTUNIDADES
(
    id_oportunidade INT PRIMARY KEY IDENTITY (1,1),
    nome_negocio    VARCHAR(100)   NOT NULL,
    valor           DECIMAL(12, 2) NOT NULL,
    data_criacao    DATE           NOT NULL,
    data_fechamento DATE,
    id_estagio      INT            NOT NULL FOREIGN KEY REFERENCES ESTAGIOS_VENDA (id_estagio),
    id_conta        INT            NOT NULL FOREIGN KEY REFERENCES CONTAS (id_conta)
)

-- Criação da Tabela de Interações
CREATE TABLE INTERACOES
(
    id_interacao      INT PRIMARY KEY IDENTITY (1,1),
    data_interacao    DATETIME NOT NULL,
    descricao         TEXT     NOT NULL,
    id_tipo_interacao INT      NOT NULL FOREIGN KEY REFERENCES TIPOS_INTERACAO (id_tipo_interacao),
    id_contato        INT      NOT NULL FOREIGN KEY REFERENCES CONTATOS (id_contato)
)

-- Criação da Tabela de Tarefas
CREATE TABLE TAREFAS
(
    id_tarefa        INT PRIMARY KEY IDENTITY (1,1),
    titulo           VARCHAR(100) NOT NULL,
    data_vencimento  DATE         NOT NULL,
    id_status_tarefa INT          NOT NULL FOREIGN KEY REFERENCES STATUS_TAFERA (id_status_tarefa),
    id_oportunidade  INT          NOT NULL FOREIGN KEY REFERENCES OPORTUNIDADES (id_oportunidade)
)

-- Populando a tabela de Perfis de Acesso
INSERT INTO PERFIS (nome_perfil, descricao)
VALUES ('Administrador', 'Acesso total ao sistema, relatórios gerenciais e gerenciamento de usuários.'),
       ('Gestor', 'Acesso completo a visualização de pipelines de vendas e relatórios da equipe.'),
       ('Vendedor', 'Acesso restrito para criar e gerenciar suas próprias contas e oportunidades.'),
       ('Leitor', 'Acesso somente leitura para visualizar informações de contas e oportunidades.');


-- Populando a tabela de Setores
INSERT INTO SETORES (titulo, descricao)
VALUES ('Tecnologia', 'Empresas de desenvolvimento de software, infraestrutura de TI e SaaS.'),
       ('Logística', 'Empresas de transporte, armazenamento e supply chain.'),
       ('Saúde', 'Hospitais, clínicas, laboratórios e indústrias farmacêuticas.'),
       ('Varejo', 'Cadeias de lojas, e-commerce e comércio de bens de consumo.');

-- Populando a tabela de Cargos
INSERT INTO CARGOS (titulo, descricao)
VALUES ('Diretor Executivo', 'Cargo de nível C-Level (CEO, CTO, COO) com poder de decisão final.'),
       ('Gerente de Projetos', 'Responsável pela coordenação técnica e tática das equipes.'),
       ('Comprador Sênior', 'Profissional responsável pelas negociações e aquisições da empresa.'),
       ('Analista de Operações', 'Profissional técnico focado na execução dos processos do dia a dia.');

-- Populando a tabela de Tipos de Interação
INSERT INTO TIPOS_INTERACAO (tipo, descricao)
VALUES ('E-mail', 'Troca de mensagens eletrônicas formais.'),
       ('Chamada Telefônica', 'Contato rápido por ligação de voz ou WhatsApp.'),
       ('Reunião Presencial', 'Alinhamento físico na sede da empresa ou do cliente.'),
       ('Videoconferência', 'Reunião online via Teams, Zoom ou Google Meet.');

-- Populando a tabela de Estágios de Venda
INSERT INTO ESTAGIOS_VENDA (estagio, descricao)
VALUES ('Prospecção', 'Contato inicial estabelecido com o potencial cliente.'),
       ('Qualificação', 'Identificação de necessidades, orçamento e tomadores de decisão.'),
       ('Proposta Comercial', 'Envio oficial dos valores e escopo do projeto/produto.'),
       ('Negociação', 'Discussão de termos contratuais e ajustes de valores.'),
       ('Fechado Ganho', 'Contrato assinado e venda realizada com sucesso.'),
       ('Fechado Perdido', 'Negociação encerrada sem fechamento de contrato.');

-- Populando a tabela de Status de Tarefa
INSERT INTO STATUS_TAFERA (status, descricao)
VALUES ('Pendente', 'Tarefa aguardando o início ou a data programada.'),
       ('Em Andamento', 'Tarefa sendo executada pelo vendedor.'),
       ('Concluída', 'Atividade finalizada com sucesso.'),
       ('Atrasada', 'Prazo de vencimento expirado sem conclusão.');


-- Inserindo Usuários de teste (Administrador, Gestor e dois Vendedores)
INSERT INTO USUARIOS (nome, email, senha, id_perfil)
VALUES ('Guilherme Santos', 'admin@crmweb.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.', 1);


-- Inserindo Contas (Empresas) vinculadas a vendedores específicos
INSERT INTO CONTAS (nome_empresa, cnpj, id_setor, data_cadastro)
VALUES ('Tech Inovações LTDA', '12.345.678/0001-90', 1, '2026-01-10'),
       ('Global Logística S.A.', '98.765.432/0001-10', 2, '2026-01-15'),
       ('Alfa Alimentos', '45.678.901/0001-22', 4, '2026-02-01'),
       ('Delta Saúde', '34.567.890/0001-44', 3, '2026-03-01')

-- Inserindo Contatos das Empresas
INSERT INTO CONTATOS (id_conta, nome, email, telefone, id_cargo)
VALUES (1, 'Carlos Silva', 'carlos@techinova.com', '(11) 99999-1111', 1),
       (1, 'Ana Costa', 'ana@techinova.com', '(11) 99999-2222', 2),
       (2, 'Marcos Souza', 'marcos@globallog.com', '(21) 98888-3333', 1),
       (3, 'Patricia Lima', 'patricia@alfa.com', '(31) 97777-4444', 3),
       (4, 'Fernanda Mello', 'fernanda@deltasaude.com', '(19) 95555-6666', 1)

-- Inserindo Oportunidades de Negócio no Funil (Pipeline)
INSERT INTO OPORTUNIDADES (id_conta, nome_negocio, valor, id_estagio, data_criacao, data_fechamento)
VALUES (1, 'Migração para Cloud', 150000.00, 5, '2026-01-12', '2026-02-20'),
       (1, 'Licenciamento Anual BI', 45000.00, 3, '2026-05-10', NULL),
       (2, 'Otimização de ERP', 85000.00, 4, '2026-01-20', NULL),
       (3, 'Expansão de Gôndolas', 120000.00, 6, '2026-02-05', '2026-03-10'),
       (4, 'Suporte Técnico Premium', 30000.00, 5, '2026-03-05', '2026-04-01')

-- Inserindo Histórico de Interações (Quem atendeu, quem foi atendido e por qual canal)
INSERT INTO INTERACOES (id_contato, id_tipo_interacao, data_interacao, descricao)
VALUES (1, 3, '2026-01-11 10:00:00', 'Reunião Presencial: Alinhamento inicial do escopo de migração para nuvem.'),
       (1, 1, '2026-01-15 14:30:00', 'E-mail: Envio oficial do escopo técnico e valores de infraestrutura.'),
       (3, 2, '2026-01-22 09:15:00', 'Chamada Telefônica: Follow-up do ERP. Cliente solicitou desconto na licença.'),
       (4, 4, '2026-02-06 16:00:00', 'Videoconferência: Apresentação técnica do projeto para o time de compras.')

-- Inserindo Tarefas (O que precisa ser feito, prazos, status e quem deve executar)
INSERT INTO TAREFAS (id_oportunidade, titulo, data_vencimento, id_status_tarefa)
VALUES (1, 'Configurar servidores de produção em Cloud', '2026-03-01', 3),
       (2, 'Ajustar margem da proposta comercial de BI', '2026-07-25', 1),
       (3, 'Apresentar contraproposta com desconto solicitado', '2026-02-05', 4),
       (5, 'Ligar para colher feedback do suporte premium', '2026-04-15', 3)
GO