<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script>
    function showFields() {
        // A função pode ser personalizada se houver necessidade de lógica específica
        var papel = document.getElementById("papel").value;
        var clienteFields = document.getElementById("clienteFields");
        var profissionalFields = document.getElementById("profissionalFields");
        
        clienteFields.style.display = (papel === "CLIENTE" || papel === "AMBOS") ? "block" : "none";
        profissionalFields.style.display = (papel === "PROFISSIONAL" || papel === "AMBOS") ? "block" : "none";

        // Definir campos obrigatórios dinamicamente
        var fields = document.querySelectorAll('#clienteFields input, #profissionalFields input');
        fields.forEach(function(field) {
            if (papel === "CLIENTE" || papel === "AMBOS") {
                if (field.name === 'telefone' || field.name === 'sexo' || field.name === 'dataDeNascimento') {
                    field.setAttribute('required', 'required');
                } else {
                    field.removeAttribute('required');
                }
            } else if (papel === "PROFISSIONAL" || papel === "AMBOS") {
                if (field.name === 'especialidade') {
                    field.setAttribute('required', 'required');
                } else {
                    field.removeAttribute('required');
                }
            } else {
                field.removeAttribute('required');
            }
        });
    }

    function validateTime() {
        var input = document.getElementById("dataHora");
        var value = input.value;
        if (value) {
            var dateTime = new Date(value);
            var hours = dateTime.getHours();
            var minutes = dateTime.getMinutes();

            if (minutes !== 0) {
                alert("Por favor, insira um horário com minutos iguais a zero (por exemplo, 13:00, 14:00).");
                input.value = ""; // Limpa o campo se o horário não for válido
            }
        }
    }
</script>

<form action="${pageContext.request.contextPath}/consultas/${consulta != null ? 'atualizacao' : 'insercao'}" method="post">
    <table border="1">
        <caption>
            <c:choose>
                <c:when test="${consulta != null}">
                    Edição
                </c:when>
                <c:otherwise>
                    Cadastro
                </c:otherwise>
            </c:choose>
        </caption>
        <c:if test="${consulta != null}">
            <input type="hidden" name="id" value="${consulta.id}" />
        </c:if>
        <tr>
            <td><label for="dataHora">Data e Hora:</label></td>
            <td>
                <input type="datetime-local" id="dataHora" name="dataHora" required value="${consulta.dataHora != null ? consulta.dataHora : ''}" onchange="validateTime()" />
            </td>
        </tr>
        <tr>
            <td><label for="CPFCliente">CPF do Cliente:</label></td>
            <td><input type="text" id="CPFCliente" name="CPFCliente" required value="${consulta.CPFCliente}" /></td>
        </tr>
        <tr>
            <td><label for="CPFProfissional">CPF do Profissional:</label></td>
            <td><input type="text" id="CPFProfissional" name="CPFProfissional" required value="${consulta.CPFProfissional}" /></td>
        </tr>
    </table>

    <table border="1">
        <tr>
            <td colspan="2" align="center"><input type="submit" value="Salvar" /></td>
        </tr>
    </table>
</form>

<script>
    // Inicializar a exibição correta dos campos com base no papel atual
    document.addEventListener("DOMContentLoaded", function () {
        showFields();
    });
</script>
