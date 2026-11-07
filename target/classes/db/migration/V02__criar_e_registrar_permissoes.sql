CREATE TABLE PERMISSOES
(
    id        INT PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL
)

CREATE TABLE PERFIS_PERMISSOES
(
    id_perfil    INT NOT NULL,
    id_permissao INT NOT NULL,
    PRIMARY KEY (id_perfil, id_permissao),
    FOREIGN KEY (id_perfil) REFERENCES PERFIS (id_perfil),
    FOREIGN KEY (id_permissao) REFERENCES PERMISSOES (id)
)

-- Inserção de Permissões
    INSERT INTO PERMISSOES VALUES
    (1, 'ROLE_REGISTER_USER'),
    (2, 'ROLE_REMOVE_USER'),
    (3, 'ROLE_SEARCH_USER'),
    (4, 'ROLE_REGISTER_ACCOUNT'),
    (5, 'ROLE_REMOVE_ACCOUNT'),
    (6, 'ROLE_SEARCH_ACCOUNT'),
    (7, 'ROLE_REGISTER_CONTACT'),
    (8, 'ROLE_REMOVE_CONTACT'),
    (9, 'ROLE_SEARCH_CONTACT'),
    (10, 'ROLE_REGISTER_OPPORTUNITY'),
    (11, 'ROLE_REMOVE_OPPORTUNITY'),
    (12, 'ROLE_SEARCH_OPPORTUNITY'),
    (13, 'ROLE_REGISTER_INTERACTION'),
    (14, 'ROLE_REMOVE_INTERACTION'),
    (15, 'ROLE_SEARCH_INTERACTION'),
    (16, 'ROLE_REGISTER_TASK'),
    (17, 'ROLE_REMOVE_TASK'),
    (18, 'ROLE_SEARCH_TASK'),
    (19, 'ROLE_REGISTER_TASK_STATUS'),
    (20, 'ROLE_REMOVE_TASK_STATUS'),
    (21, 'ROLE_SEARCH_TASK_STATUS'),
    (22, 'ROLE_REGISTER_SALES_STAGE'),
    (23, 'ROLE_REMOVE_SALES_STAGE'),
    (24, 'ROLE_SEARCH_SALES_STAGE'),
    (25, 'ROLE_REGISTER_INTERACTION_TYPE'),
    (26, 'ROLE_REMOVE_INTERACTION_TYPE'),
    (27, 'ROLE_SEARCH_INTERACTION_TYPE'),
    (28, 'ROLE_REGISTER_POSITION'),
    (29, 'ROLE_REMOVE_POSITION'),
    (30, 'ROLE_SEARCH_POSITION'),
    (31, 'ROLE_REGISTER_SECTOR'),
    (32, 'ROLE_REMOVE_SECTOR'),
    (33, 'ROLE_SEARCH_SECTOR')

-- Permissões para cada perfil
INSERT INTO PERFIS_PERMISSOES VALUES
    (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9),
    (1, 10), (1, 11), (1, 12), (1, 13), (1, 14), (1, 15), (1, 16), (1, 17),
    (1, 18), (1, 19), (1, 20), (1, 21), (1, 22), (1, 23), (1, 24), (1, 25),
    (1, 26), (1, 27), (1, 28), (1, 29), (1, 30), (1, 31), (1, 32), (1, 33),
    (2, 6), (2, 9), (2, 12), (2, 15), (2, 18), (2, 21), (2, 24), (2, 27), (2, 30), (2, 33),
    (3, 6), (3, 9), (3, 12), (3, 15), (3, 18), (3, 21), (3, 24), (3, 27), (3, 30), (3, 33),
    (4, 6), (4, 9), (4, 12), (4, 15), (4, 18), (4, 21), (4, 24), (4, 27), (4, 30), (4, 33)
GO
