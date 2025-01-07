-- Criar a tabela tb_alunos
CREATE TABLE tb_alunos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    turma VARCHAR(50) NOT NULL,
    telefone VARCHAR(11) NOT NULL CHECK (telefone ~ '^\d{10,11}$'),
    transporte_escolar VARCHAR(255) NOT NULL
);

-- Criar a tabela tb_adultos_responsaveis
CREATE TABLE tb_adultos_responsaveis (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    grau_parentesco VARCHAR(50) NOT NULL,
    aluno_id INT NOT NULL,
    CONSTRAINT fk_aluno_id FOREIGN KEY (aluno_id) REFERENCES tb_alunos (id) ON DELETE CASCADE
);
