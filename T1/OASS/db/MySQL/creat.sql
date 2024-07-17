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
    data DATE NOT NULL,
    hora TIME NOT NULL,
    CPFCliente BIGINT NOT NULL,
    CPFProfissional BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (CPFCliente) REFERENCES Usuario(CPF),
    FOREIGN KEY (CPFProfissional) REFERENCES Usuario(CPF)
);

INSERT INTO Usuario (nome, email, senha, CPF, papel) VALUES ('ADMIN', 'ADMIN', 'ADMIN', 12345678901, 'ADMIN');
