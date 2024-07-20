<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> Lista de Consultas </title>
</head>
<body>
    <fmt:bundle basename="messages">
        <div align="center">
            <h1><fmt:message key="header.consultationList"/></h1>
            <h2>
                <a href="${pageContext.request.contextPath}"><fmt:message key="link.mainMenu"/></a> &nbsp;&nbsp;&nbsp; 
                <a href="${pageContext.request.contextPath}/consultas/cadastro"><fmt:message key="link.addConsultation"/></a>
            </h2>
        </div>

        <div align="center">
            <table border="1" cellpadding="10" cellspacing="0" style="border-collapse: collapse;">
                <caption><fmt:message key="caption.consultations"/></caption>
                <tr>
                    <th><fmt:message key="column.id"/></th>
                    <th><fmt:message key="column.dateTime"/></th>
                    <th><fmt:message key="column.clientCPF"/></th>
                    <th><fmt:message key="column.professionalCPF"/></th>
                    <th><fmt:message key="column.actions"/></th>
                </tr>
                <c:forEach var="consulta" items="${requestScope.listaConsultas}">
                    <tr>
                        <td>${consulta.id}</td>
                        <td>${consulta.dataHora}</td>
                        <td>${consulta.CPFCliente}</td>
                        <td>${consulta.CPFProfissional}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/consultas/edicao?id=${consulta.id}"><fmt:message key="action.edit"/></a>
                            &nbsp;&nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/consultas/remocao?id=${consulta.id}"
                               onclick="return confirm(<fmt:message key='confirm.deleteConsultation' />);"><fmt:message key="action.delete"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </fmt:bundle>
</body>
</html>
