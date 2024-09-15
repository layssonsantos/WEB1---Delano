# WEB1 - T1
Desenvolvimento do T1  (Trabalho 1)

# Projeto de Sistema para agendamento de consultas online com profissionais

## Funcionalidades
- Cadastro de Usuários (Clientes e Profissionais)
- Login de Usuários
- Cadastro de Consultas
- Listagem de Consultas
- Upload de Arquivos
  
## Tecnologias Utilizadas
- Java
- Javascript
- CSS
- Servlets
- JSP
- JSTL
- JDBC
- MySQL

## Roteiro de execução:
- SGBD: **MySQL**
- Nome do Banco de Dados: **OASS**
- Script sql: **source db\MySQL\create.sql**
- Usuários populados: **Admin, Cliente, Profissional e Ambos**
  
# Checklist dos requisitos (R1-R8):

## R1: CRUD 1 de clientes (requer login de administrador):
```
() Implementado (X) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Daniel de Souza Sobrinho Macedo (50%) e Gabriel Henrique Alves Zago (50%)
```
## R2: CRUD de profissionais liberais (requer login de administrador):
```
() Implementado (X) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Daniel de Souza Sobrinho Macedo (50%) e Laysson Santos da Silva (50%)
```
## R3: Listagem de todos os profissionais liberais em uma única página (não requer login). O sistema deve prover a funcionalidade de filtrar os profissionais por especialidade:
```
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Daniel de Souza Sobrinho Macedo (33%), Laysson Santos da Silva (33%) e Gabriel Henrique Alves Zago (33%)
```
## R4: Agendamento de horário de consulta com um profissional (requer login do cliente via email + senha):
> Ao clicar em um profissional (requisito R3), o cliente pode cadastrar uma consulta. Para isso, deve escolher uma data/horário, e deve ser gravado o horário da consulta na base de dados. O profissional e cliente devem ser informado (via e-mail) sobre o agendamento da consulta -- o link da videoconferência (google meet, zoom, etc) deve estar presente no corpo da mensagem enviada.
```
() Implementado (X) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Daniel de Souza Sobrinho Macedo (33%), Laysson Santos da Silva (33%) e Gabriel Henrique Alves Zago (33%)
```
## R5: O sistema não deve permitir o cadastro de consulta de um mesmo profissional ou de um mesmo cliente em uma mesma data/horário:
```
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Laysson Santos da Silva (100%)
```
## R6: Listagem de todas os horários de consultas de um cliente (requer login do cliente via email + senha). Depois de fazer login, o cliente pode visualizar todos os seus horários de consultas cadastrados:
```
() Implementado (X) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Daniel de Souza Sobrinho Macedo (33%), Laysson Santos da Silva (33%) e Gabriel Henrique Alves Zago (33%)
```
## R7: Listagem de todas as consultas de um profissional (requer login do profissional via e-mail + senha). Depois de fazer login, o profissional pode visualizar todos os horários de atendimento cadastrados:
```
### () Implementado (X) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Daniel de Souza Sobrinho Macedo (33%), Laysson Santos da Silva (33%) e Gabriel Henrique Alves Zago (33%)
```
## R8: O sistema deve ser internacionalizado em pelo menos dois idiomas: português + outro de sua escolha:
```
() Implementado (X) Parcialmente implementado ( ) Não implementado
Divisão na implementação da funcionalidade: Daniel de Souza Sobrinho Macedo (33%), Laysson Santos da Silva (33%) e Gabriel Henrique Alves Zago (33%)
```
