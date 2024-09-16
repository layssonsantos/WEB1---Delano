# WEB1 - T2 CRUD
Desenvolvimento do T2  (Trabalho 2)

# Projeto de Sistema para agendamento de consultas online com profissionais

## Arquitetura: Modelo-Visão-Controlador

## Tecnologias Utilizadas
- Lado Servidor
  - Java com **Spring Boot**
  - **Spring Data JPA** para integração com o banco de dados MySQL
  - **Thymeleaf** para renderização de páginas dinâmicas
  - **Spring Security** para controle de autenticação e autorização
- Lado Cliente
  - Javascript
  - CSS
  - Bootstrap

## Funcionalidades
- Cadastro e Listagem de Clientes
- Cadastro e Listagem de Profissionais Liberais
- Login de Usuários (Cliente e Profissional)
- Cadastro e Listagem de Consultas
- Consulta via e-mail
- Upload de Arquivos
- Validação de campos
- Internacionalização

## Usuários populados:
- Administrador:
  - login: admin@admin.com
  - senha: admin
- Clientes:
  - Cliente (1):
    - login: maria@exemple.com
    - senha: 12345678
  - Cliente (2):
    - login: joao.santos@example.com
    - senha: 87654321
  - Cliente (3):
    - login: ana.souza@example.com
    - senha: 45678912
- Profissionais:
  - Profissional (1):
    - login: ana.silva@example.com
    - senha: 12345678
  - Profissional (2):
    - login: joao.souza@example.com
    - senha: 87654321
  - Profissional (3):
    - login: carla.rodrigues@example.com
    - senha: 45678912
  
# Checklist dos requisitos (R1-R9):

## R1: CRUD 1 de clientes (requer login de administrador):
```
(X) Implementado () Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Daniel de Souza Sobrinho Macedo (50%) e Laysson Santos da Silva (50%)
```
## R2: CRUD de profissionais liberais (requer login de administrador):
```
(X) Implementado () Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Daniel de Souza Sobrinho Macedo (50%) e Laysson Santos da Silva (50%)
```
## R3: Listagem de todos os profissionais liberais em uma única página (não requer login). O sistema deve prover a funcionalidade de filtrar os profissionais por especialidade:
```
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Laysson Santos da Silva (100%)
```
## R4: Agendamento de horário de consulta com um profissional (requer login do cliente via email + senha):
> Ao clicar em um profissional (requisito R3), o cliente pode cadastrar uma consulta. Para isso, deve escolher uma data/horário, e deve ser gravado o horário da consulta na base de dados. O profissional e cliente devem ser informado (via e-mail) sobre o agendamento da consulta -- o link da videoconferência (google meet, zoom, etc) deve estar presente no corpo da mensagem enviada.
```
(X) Implementado () Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Daniel de Souza Sobrinho Macedo (50%) e Lucas Roberto da Silva(50%)
```
## R5: O sistema não deve permitir o cadastro de consulta de um mesmo profissional ou de um mesmo cliente em uma mesma data/horário:
```
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Daniel de Souza Sobrinho Macedo (50%) e Gabriel Henrique Alves Zago (50%)
```
## R6: Listagem de todas os horários de consultas de um cliente (requer login do cliente via email + senha). Depois de fazer login, o cliente pode visualizar todos os seus horários de consultas cadastrados:
```
(X) Implementado () Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Daniel de Souza Sobrinho Macedo (33%), Laysson Santos da Silva (33%) e Gabriel Henrique Alves Zago (33%)
```
## R7: Listagem de todas as consultas de um profissional (requer login do profissional via e-mail + senha). Depois de fazer login, o profissional pode visualizar todos os horários de atendimento cadastrados:
```
(X) Implementado () Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Daniel de Souza Sobrinho Macedo (33%), Laysson Santos da Silva (33%) e Gabriel Henrique Alves Zago (33%)
```
## R8: O sistema deve ser internacionalizado em pelo menos dois idiomas: português + outro de sua escolha:
```
(X) Implementado () Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Daniel de Souza Sobrinho Macedo (50%) e Gabriel Henrique Alves Zago (50%)
```
## R9: O sistema deve validar (tamanho, formato, etc) todas as informações (campos nos formulários) cadastradas e/ou editadas:
```
(X) Implementado () Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Daniel de Souza Sobrinho Macedo (25%), Laysson Santos da Silva (25%), Gabriel Henrique Alves Zago (25%) e Lucas Roberto da Silva(25%)

