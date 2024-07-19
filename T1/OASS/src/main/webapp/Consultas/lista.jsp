<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Consultas</title>
</head>
<body>
    <div align="center">
        <h1>Lista de Consultas</h1>
        <h2>
            <a href="${pageContext.request.contextPath}">Menu Principal</a> &nbsp;&nbsp;&nbsp; 
            <a href="${pageContext.request.contextPath}/consultas/cadastro">Adicionar Nova Consulta</a>
        </h2>
    </div>

    <div align="center">
        <table border="1" cellpadding="10" cellspacing="0" style="border-collapse: collapse;">
            <caption>Consultas</caption>
            <tr>
                <th>ID</th>
                <th>Data e Hora</th>
                <th>CPF Cliente</th>
                <th>CPF Profissional</th>
                <th>Ações</th>
            </tr>
            <c:forEach var="consulta" items="${requestScope.listaConsultas}">
                <tr>
                    <td>${consulta.id}</td>
                    <td>${consulta.dataHora}</td>
                    <td>${consulta.CPFCliente}</td>
                    <td>${consulta.CPFProfissional}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/consultas/edicao?id=${consulta.id}">Editar</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/consultas/remocao?id=${consulta.id}"
                           onclick="return confirm('Tem certeza de que deseja excluir esta consulta?');">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
