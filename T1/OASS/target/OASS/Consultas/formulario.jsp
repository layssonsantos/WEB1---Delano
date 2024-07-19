<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Gerenciamento de Consultas</title>
</head>

<body>
    <div align="center">
        <h1>Gerenciamento de Consultas</h1>
        <h2>
            <a href="lista">Lista de Consultas</a>
        </h2>
    </div>
    <div align="center">
        <c:choose>
            <c:when test="${consulta != null}">
                <form action="atualizacao" method="post">
                    <input type="hidden" name="id" value="${consulta.id}"/>
                    <%@include file="campos.jsp"%>
                </form>
            </c:when>
            <c:otherwise>
                <form action="insercao" method="post">
                    <%@include file="campos.jsp"%>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
    <c:if test="${!empty requestScope.mensagens}">
        <ul class="erro">
            <c:forEach items="${requestScope.mensagens}" var="mensagem">
                <li>${mensagem}</li>
            </c:forEach>
        </ul>
    </c:if>
</body>
</html>
