<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f0f0f0; 
        }
        .center {
            text-align: center;
        }
        .highlight {
            color: #333; 
            font-weight: bold; 
            margin: 20px 0; 
        }
        .welcome {
            color: #350e00; 
            font-size: 2em; 
            text-shadow: 2px 2px 4px #aaa; 
            margin: 20px 0;
        }
        a {
            color: #007bff; 
            text-decoration: none; 
            font-weight: bold; 
        }
        a:hover {
            text-decoration: underline; 
        }
    </style>
</head>
<body>
    <fmt:bundle basename="messages">
        <div class="center highlight">
            <h2 class="welcome"><fmt:message key="welcome.message"/></h2>
            <ul>
                <li><a href="consultas"><fmt:message key="link.consultas"/></a></li>
                <li><a href="usuarios"><fmt:message key="link.reserve"/></a></li>
                <li><a href="usuarios/cadastro"><fmt:message key="link.cadastro"/></a></li>
            </ul>
        </div>
    </fmt:bundle>
</body>
</html>
