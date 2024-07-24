<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>

<body>
    <fmt:bundle basename="messages">
        <div align="center">
            <form action="${pageContext.request.contextPath}/usuarios/valida" method="post">
                <label for="login"><fmt:message key="label.login"/></label>
                <input type="text" id="login" name="login" required="required" />
                <br />
                <label for="senha"><fmt:message key="label.password"/></label>
                <input type="password" id="senha" name="senha" required="required" />
                <br />
                <input type="submit" value="<fmt:message key='button.login'/>" />
            </form>
        </div>
        <c:if test="${!empty requestScope.mensagens}">
            <ul class="erro">
                <c:forEach items="${requestScope.mensagens}" var="mensagem">
                    <li>${mensagem}</li>
                </c:forEach>
            </ul>
        </c:if>
    </fmt:bundle>
</body>
</html>
