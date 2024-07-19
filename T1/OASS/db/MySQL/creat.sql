DROP DATABASE IF EXISTS OASS;

CREATE DATABASE OASS;

USE OASS;

CREATE TABLE Usuario (
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    CPF BIGINT NOT NULL,
    papel VARCHAR(30) NOT NULL,
    telefone BIGINT,
    sexo VARCHAR(1),
    dataDeNascimento DATE,
    especialidade VARCHAR(100),
    PRIMARY KEY (CPF)
);

CREATE TABLE Consulta (
    id BIGINT NOT NULL AUTO_INCREMENT,
    dataHora DATETIME NOT NULL,
    CPFCliente BIGINT NOT NULL,
    CPFProfissional BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (CPFCliente) REFERENCES Usuario(CPF),
    FOREIGN KEY (CPFProfissional) REFERENCES Usuario(CPF)
);

INSERT INTO Usuario (nome, email, senha, CPF, papel) VALUES ('ADMIN', 'ADMIN@ADMIN', 'ADMIN', 0, 'ADMIN');
INSERT INTO Usuario (nome, email, senha, CPF, papel, telefone, sexo, dataDeNascimento) VALUES ('CLIENTE', 'CLIENTE@CLIENTE', 'CLIENTE', 1, 'CLIENTE', 123456789, 'M', '1999-01-01');
INSERT INTO Usuario (nome, email, senha, CPF, papel, especialidade) VALUES ('PROFISSIONAL', 'PROFISSIONAL@PROFISSIONAL', 'PROFISSIONAL', 2, 'PROFISSIONAL', 'MEDICO');
INSERT INTO Usuario (nome, email, senha, CPF, papel, telefone, sexo, dataDeNascimento, especialidade) VALUES ('AMBOS', 'AMBOS@AMBOS', 'AMBOS', 3, 'AMBOS', 123456789, 'M', '1999-01-01', 'Advogado');
INSERT INTO Consulta (dataHora, CPFCliente, CPFProfissional) VALUES ('2021-06-01 10:00:00', 1, 2);
