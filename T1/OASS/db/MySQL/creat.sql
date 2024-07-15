drop database if exists OASS;

create database OASS;

use OASS;

create table Usuario (
    nome varchar(100) not null,
    email varchar(100) not null,
    senha varchar(50) not null,
    CPF bigint not null,
    papel varchar(10) not null,
    primary key (CPF)
);

create table Cliente (
    CPF bigint not null,
    telefone bigint not null,
    sexo varchar(1) not null,
    dataDeNascimento date not null,
    primary key (CPF)
    foreign key (CPF) references Usuario(CPF)
);

create table Profissional (
    CPF bigint not null,
    especialidade varchar(100) not null,
    primary key (CPF)
    foreign key (CPF) references Usuario(CPF)
);

create table Cunsulta (
    id bigint not null auto_increment,
    data date not null,
    hora time not null,
    CPFCliente bigint not null,
    CPFProfissional bigint not null,
    primary key (id),
    foreign key (CPFCliente) references Cliente(CPF),
    foreign key (CPFProfissional) references Profissional(CPF)
);

insert into Usuario values ('ADMIN', 'ADMIN', 'ADMIN', 12345678901, 'ADMIN');
