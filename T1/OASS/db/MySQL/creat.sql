drop database if exists OASS;

create database OASS;

use OASS;

create table Cliente (
    name varchar(100) not null,
    email varchar(100) not null,
    senha varchar(50) not null,
    CPF bigint not null,
    telefone bigint not null,
    sexo varchar(1) not null,
    dataDeNascimento date not null,
    primary key (CPF)
);

create table Profissional (
    name varchar(100) not null,
    email varchar(100) not null,
    senha varchar(50) not null,
    CPF bigint not null,
    especialidade varchar(100) not null,
    primary key (CPF)
);

create table Cunsulta (
    id bigint not null auto_increment,
    data date not null,
    hora time not null,
    CPFCliente bigint not null,
    CPFProfissional bigint not null,
    primary key (id),
    foreign key (idCliente) references Cliente(CPF),
    foreign key (idProfissional) references Profissional(CPF)
);
