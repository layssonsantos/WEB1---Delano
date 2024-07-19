<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalhes da Consulta</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .center {
            text-align: center;
        }
        table {
            width: 50%;
            margin: 0 auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div class="center">
        <h2>Detalhes da Consulta</h2>
        <table>
            <tr>
                <th>ID</th>
                <td>${consulta.id}</td>
            </tr>
            <tr>
                <th>Paciente</th>
                <td>${consulta.paciente}</td>
            </tr>
            <tr>
                <th>Profissional</th>
                <td>${consulta.profissional}</td>
            </tr>
            <tr>
                <th>Data</th>
                <td>${consulta.data}</td>
            </tr>
            <tr>
                <th>Hora</th>
                <td>${consulta.hora}</td>
            </tr>
            <tr>
                <th>Especialidade</th>
                <td>${consulta.especialidade}</td>
            </tr>
            <tr>
                <th>Observações</th>
                <td>${consulta.observacoes}</td>
            </tr>
        </table>
        <br>
        <a href="${pageContext.request.contextPath}/consultas">Voltar à Lista</a>
    </div>
</body>
</html>
