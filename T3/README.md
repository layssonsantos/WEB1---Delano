# WEB1 - T3 REST API
Desenvolvimento do T3  (Trabalho 3)

# Projeto de Sistema para agendamento de consultas online com profissionais
Este projeto é um sistema para agendamento de consultas online entre clientes e profissionais liberais, desenvolvido utilizando uma REST API para interações com os clientes, profissionais e consultas.

## Arquitetura: Modelo-Visão-Controlador

## Funcionalidades
- ### Cadastro de Clientes
  - **POST** _/api/clientes_: Criação de novos clientes.
  - **GET** _/api/clientes_: Retorna a lista de clientes cadastrados.
  - **GET** _/api/clientes/{id}_: Retorna um cliente específico pelo ID.
  - **PUT** _/api/clientes/{id}_: Atualiza os dados de um cliente pelo ID.
  - **DELETE** _/api/clientes/{id}_: Remove um cliente do sistema pelo ID.
- ### Cadastro de Profissionais
  - **POST** _/api/profissionais_: Criação de novos profissionais.
  - **GET** _/api/profissionais_: Retorna a lista de profissionais cadastrados.
  - **GET** _/api/profissionais/{id}_: Retorna um profissional específico pelo ID.
  - **GET** _/api/profissionais/especialidades/{nome}_: Retorna a lista de profissionais filtrados pela especialidade.
  - **PUT** _/api/profissionais/{id}_: Atualiza os dados de um profissional pelo ID.
  - **DELETE** _/api/profissionais/{id}_: Remove um profissional do sistema pelo ID.
- ### Agendamento de Consultas
  - **POST** _/api/consultas_: Criação de novas consultas.
  - **GET** _/api/consultas_: Retorna a lista de todas as consultas agendadas.
  - **GET** _/api/consultas/{id}_: Retorna uma consulta específica pelo ID.
  - **GET** _/api/consultas/clientes/{id}_: Retorna a lista de consultas de um cliente específico.
  - **GET** _/api/consultas/profissionais/{id}_: Retorna a lista de consultas de um profissional específico.


## Usuários populados
- Administrador:
  - login: admin@admin.com
  - senha: admin
	@@ -52,28 +50,52 @@ Este projeto é um sistema para agendamento de consultas online entre clientes e
  - Profissional (3):
    - login: carla.rodrigues@example.com
    - senha: 45678912

## Tecnologias Utilizadas
  - Spring MVC (Controladores REST), Spring Data JPA, Spring Security & Thymeleaf (Lado
  Servidor)

# Checklist dos requisitos (R1-R3):

## R1: CRUD de Clientes via REST API:
> A API permite criar, listar, editar e excluir clientes do sistema por meio de endpoints HTTP.
```
(X) Implementado () Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Laysson Santos da Silva (100%)
```
## R2: CRUD de Profissionais via REST API:
> A API permite criar, listar, editar e excluir profissionais do sistema por meio de endpoints HTTP.
```
(X) Implementado () Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: 
```
## R3: CRUD de Consultas via REST API:
> A API permite listar consultas agendadas por clientes com profissionais via endpoints HTTP.
```
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: 
