<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <fmt:bundle basename="messages">
        <div align="center">
            <h1>Bem-vindo à Home</h1>
            <c:choose>
                <c:when test="${usuario.papel == 'ADMIN'}">
                    <h2>Admin</h2>
                    <a href="${pageContext.request.contextPath}/usuarios/lista"><fmt:message key="link.crudUsuarios"/></a>
                    <br>
                    <a href="${pageContext.request.contextPath}/consultas/lista"><fmt:message key="link.crudConsultas"/></a>
                </c:when>
                <c:when test="${usuario.papel == 'CLIENTE'}">
                    <h2>Cliente</h2>
                    <a href="${pageContext.request.contextPath}/profissionais"><fmt:message key="link.profissionais"/></a>
                    <h3>Suas Consultas</h3>
                    <c:forEach var="consulta" items="${requestScope.listaConsultasCliente}">
                        <p>${consulta.data} - ${consulta.horario} - ${consulta.profissional}</p>
                    </c:forEach>
                </c:when>
                <c:when test="${usuario.papel == 'PROFISSIONAL'}">
                    <h2>Profissional</h2>
                    <h3>Consultas Marcadas</h3>
                    <c:forEach var="consulta" items="${requestScope.listaConsultasProfissional}">
                        <p>${consulta.data} - ${consulta.horario} - ${consulta.cliente}</p>
                    </c:forEach>
                </c:when>
                <c:when test="${usuario.papel == 'AMBOS'}">
                    <h2>Cliente e Profissional</h2>
                    <a href="${pageContext.request.contextPath}/profissionais"><fmt:message key="link.profissionais"/></a>
                    <h3>Suas Consultas como Cliente</h3>
                    <c:forEach var="consulta" items="${requestScope.listaConsultasCliente}">
                        <p>${consulta.data} - ${consulta.horario} - ${consulta.profissional}</p>
                    </c:forEach>
                    <h3>Consultas Marcadas como Profissional</h3>
                    <c:forEach var="consulta" items="${requestScope.listaConsultasProfissional}">
                        <p>${consulta.data} - ${consulta.horario} - ${consulta.cliente}</p>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>Papel desconhecido.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </fmt:bundle>
</body>
</html>
