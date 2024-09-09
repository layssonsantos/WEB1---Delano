<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script>
    function showFields() {
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
</script>

<form action="${pageContext.request.contextPath}/usuarios/insercao" method="post">
    <table border="1">
        <caption>
            <c:choose>
                <c:when test="${usuario != null}">
                    Edição
                </c:when>
                <c:otherwise>
                    Cadastro
                </c:otherwise>
            </c:choose>
        </caption>
        <c:if test="${usuario != null}">
            <input type="hidden" name="CPF" value="${usuario.CPF}" />
        </c:if>
        <tr>
            <td><label for="nome">Nome:</label></td>
            <td><input type="text" id="nome" name="nome" required value="${usuario.nome}" /></td>
        </tr>
        <tr>
            <td><label for="email">Email:</label></td>
            <td><input type="email" id="email" name="email" required value="${usuario.email}" /></td>
        </tr>
        <tr>
            <td><label for="senha">Senha:</label></td>
            <td><input type="password" id="senha" name="senha" required value="${usuario.senha}" /></td>
        </tr>
        <tr>
            <td><label for="CPF">CPF:</label></td>
            <td><input type="text" id="CPF" name="CPF" required value="${usuario.CPF}" /></td>
        </tr>
        <tr>
            <td><label for="papel">Papel:</label></td>
            <td>
                <select id="papel" name="papel" onchange="showFields()">
                    <option value="ADMIN" ${usuario.papel=='ADMIN' ? 'selected' : '' }>ADMIN</option>
                    <option value="CLIENTE" ${usuario.papel=='CLIENTE' ? 'selected' : '' }>CLIENTE</option>
                    <option value="PROFISSIONAL" ${usuario.papel=='PROFISSIONAL' ? 'selected' : '' }>PROFISSIONAL</option>
                    <option value="AMBOS" ${usuario.papel=='AMBOS' ? 'selected' : '' }>CLIENTE e PROFISSIONAL</option>
                </select>
            </td>
        </tr>
    </table>

    <fieldset id="clienteFields" style="display:none">
        <legend>Informações do Cliente</legend>
        <table border="1">
            <tr>
                <td><label for="telefone">Telefone:</label></td>
                <td><input type="text" id="telefone" name="telefone" value="${usuario.telefone}" /></td>
            </tr>
            <tr>
                <td><label for="sexo">Sexo:</label></td>
                <td>
                    <select id="sexo" name="sexo">
                        <option value="M" ${usuario.sexo=='M' ? 'selected' : '' }>Masculino</option>
                        <option value="F" ${usuario.sexo=='F' ? 'selected' : '' }>Feminino</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="dataDeNascimento">Data de Nascimento:</label></td>
                <td>
                    <input type="date" id="dataDeNascimento" name="dataDeNascimento" 
                           value="${usuario.dataDeNascimento != null ? usuario.dataDeNascimento : ''}" />
                </td>
            </tr>
        </table>
    </fieldset>

    <fieldset id="profissionalFields" style="display:none">
        <legend>Informações do Profissional</legend>
        <table border="1">
            <tr>
                <td><label for="especialidade">Especialidade:</label></td>
                <td><input type="text" id="especialidade" name="especialidade" value="${usuario.especialidade}" /></td>
            </tr>
        </table>
    </fieldset>

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
