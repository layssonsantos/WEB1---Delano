<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Lista de Usuários</title>
</head>
<body>
    <div align="center">
        <h1>Lista de Usuários</h1>
        <h2>
            <a href="${pageContext.request.contextPath}">Menu Principal</a> &nbsp;&nbsp;&nbsp; 
            <a href="${pageContext.request.contextPath}/usuarios/cadastro">Adicionar Novo Usuário</a>
        </h2>
    </div>

    <div align="center">
        <table border="1">
            <caption>Lista de Usuários</caption>
            <tr>
                <th>CPF</th>
                <th>Nome</th>
                <th>E-mail</th>
                <th>Papel</th>
                <th>Ações</th>
            </tr>
            <c:forEach var="usuario" items="${requestScope.listaUsuarios}">
                <tr>
                    <td>${usuario.CPF}</td>
                    <td>${usuario.nome}</td>
                    <td>${usuario.email}</td>
                    <td>${usuario.papel}</td>
                    
                        <a href="${requestScope.contextPath}/usuarios/edicao?CPF=${usuario.CPF}">Editar</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="${requestScope.contextPath}/usuarios/remocao?CPF=${usuario.CPF}"
                           onclick="return confirm('Tem certeza de que deseja excluir este usuário?');">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
