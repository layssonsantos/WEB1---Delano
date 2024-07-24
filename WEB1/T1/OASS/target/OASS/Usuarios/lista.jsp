<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuários</title>
    <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
    <script>
        function toggleSpecialtyFilter() {
            var specialtyFilter = document.getElementById("specialtyFilter");
            var allUsersTable = document.getElementById("allUsersTable");
            var specialtyUsersTable = document.getElementById("specialtyUsersTable");
            if (allUsersTable.style.display === "none") {
                specialtyFilter.style.display = "none";
                allUsersTable.style.display = "table";
                specialtyUsersTable.style.display = "none";
            } else {
                specialtyFilter.style.display = "block";
                allUsersTable.style.display = "none";
                specialtyUsersTable.style.display = "table";
            }
        }

        function checkURLParams() {
            const urlParams = new URLSearchParams(window.location.search);
            if (urlParams.has('filter') && urlParams.get('filter') === 'specialty') {
                toggleSpecialtyFilter();
            }
        }

        window.onload = checkURLParams;

        function filterSpecialty() {
            var input, filter, table, tr, td, i, txtValue;
            input = document.getElementById("specialtyInput");
            filter = input.value.toUpperCase();
            table = document.getElementById("specialtyUsersTable");
            tr = table.getElementsByTagName("tr");

            for (i = 1; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[2];
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }       
            }
        }
    </script>
</head>
<body>
    <fmt:bundle basename="messages">
        <div align="center">
            <h1><fmt:message key="header.userList"/></h1>
            <h2>
                <a href="${pageContext.request.contextPath}"><fmt:message key="link.mainMenu"/></a> &nbsp;&nbsp;&nbsp; 
                <a href="${pageContext.request.contextPath}/usuarios/cadastro"><fmt:message key="link.addUser"/></a> &nbsp;&nbsp;&nbsp; 
                <a href="?filter=specialty"><fmt:message key="link.filterSpecialty"/></a>
            </h2>
        </div>

        <div align="center">
            <table id="allUsersTable" border="1">
                <caption><fmt:message key="caption.userList"/></caption>
                <tr>
                    <th><fmt:message key="column.cpf"/></th>
                    <th><fmt:message key="column.name"/></th>
                    <th><fmt:message key="column.email"/></th>
                    <th><fmt:message key="column.role"/></th>
                    <th><fmt:message key="column.phone"/></th>
                    <th><fmt:message key="column.gender"/></th>
                    <th><fmt:message key="column.birthDate"/></th>
                    <th><fmt:message key="column.specialty"/></th>
                    <th><fmt:message key="column.actions"/></th>
                </tr>
                <c:forEach var="usuario" items="${requestScope.listaUsuarios}">
                    <tr>
                        <td>${usuario.CPF}</td>
                        <td>${usuario.nome}</td>
                        <td>${usuario.email}</td>
                        <td>${usuario.papel}</td>
                        <td>${usuario.telefone}</td>
                        <td>${usuario.sexo}</td>
                        <td>${usuario.dataDeNascimento}</td>
                        <td>${usuario.especialidade}</td>
                        <td>
                            <c:if test="${usuario.CPF != 0}">
                                <a href="${pageContext.request.contextPath}/usuarios/edicao?CPF=${usuario.CPF}"><fmt:message key="action.edit"/></a>
                                &nbsp;&nbsp;&nbsp;
                                <a href="${pageContext.request.contextPath}/usuarios/remocao?CPF=${usuario.CPF}"><fmt:message key="action.delete"/></a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <div id="specialtyFilter" style="display:none;">
                <input type="text" id="specialtyInput" onkeyup="filterSpecialty()" placeholder="Filtrar por especialidade">
            </div>

            <table id="specialtyUsersTable" border="1" style="display:none">
                <h3><caption><fmt:message key="caption.specialtyProfessionals"/></caption></h3>
                <tr>
                    <th><fmt:message key="column.name"/></th>
                    <th><fmt:message key="column.email"/></th>
                    <th><fmt:message key="column.specialty"/></th>
                </tr>
                <c:forEach var="usuario" items="${requestScope.listaUsuarios}">
                    <c:if test="${usuario.especialidade != null && !usuario.especialidade.isEmpty()}">
                        <tr>
                            <td>${usuario.nome}</td>
                            <td>${usuario.email}</td>
                            <td>${usuario.especialidade}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
    </fmt:bundle>
</body>
</html>
