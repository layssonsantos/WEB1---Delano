<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <fmt:bundle basename="messages">
        <div class="center highlight">
            <h2 class="welcome"><fmt:message key="welcome.message"/></h2>
        </div>
        <div class="center">
            <ul class="left-aligned">
                <li><a href="consultas"><fmt:message key="link.consultas"/></a></li>
                <li><a href="usuarios"><fmt:message key="link.reserve"/></a></li>
                <li><a href="usuarios/cadastro"><fmt:message key="link.cadastro"/></a></li>
                <li><a href="usuarios/listaUsuarios?filter=specialty"><fmt:message key="link.filterSpecialty"/></a></li>
            </ul>
        </div>
    </fmt:bundle>
</body>
</html>
