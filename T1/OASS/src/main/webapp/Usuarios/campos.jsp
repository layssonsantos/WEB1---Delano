<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script>
function showFields() {
    var papel = document.getElementById("papel").value;
    document.getElementById("clienteFields").style.display = papel === "CLIENTE" || papel === "AMBOS" ? "block" : "none";
    document.getElementById("profissionalFields").style.display = papel === "PROFISSIONAL" || papel === "AMBOS" ? "block" : "none";
}
</script>

<fieldset>
    <legend>Informações do Usuário</legend>

    <div>
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" value="${usuario.nome}" required />
    </div>

    <div>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${usuario.email}" required />
    </div>

    <div>
        <label for="senha">Senha:</label>
        <input type="password" id="senha" name="senha" value="${usuario.senha}" required />
    </div>

    <div>
        <label for="cpf">CPF:</label>
        <input type="text" id="cpf" name="cpf" value="${usuario.cpf}" required readonly />
    </div>

    <div>
        <label for="papel">Papel:</label>
        <select id="papel" name="papel" onchange="showFields()">
            <option value="ADMIN" ${usuario.papel == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
            <option value="CLIENTE" ${usuario.papel == 'CLIENTE' ? 'selected' : ''}>CLIENTE</option>
            <option value="PROFISSIONAL" ${usuario.papel == 'PROFISSIONAL' ? 'selected' : ''}>PROFISSIONAL</option>
            <option value="AMBOS" ${usuario.papel == 'AMBOS' ? 'selected' : ''}>CLIENTE e PROFISSIONAL</option>
        </select>
    </div>
</fieldset>

<fieldset id="clienteFields" style="display:none">
    <legend>Informações do Cliente</legend>

    <div>
        <label for="telefone">Telefone:</label>
        <input type="text" id="telefone" name="telefone" value="${cliente.telefone}" />
    </div>

    <div>
        <label for="sexo">Sexo:</label>
        <select id="sexo" name="sexo">
            <option value="M" ${cliente.sexo == 'M' ? 'selected' : ''}>Masculino</option>
            <option value="F" ${cliente.sexo == 'F' ? 'selected' : ''}>Feminino</option>
        </select>
    </div>

    <div>
        <label for="dataDeNascimento">Data de Nascimento:</label>
        <input type="date" id="dataDeNascimento" name="dataDeNascimento" value="${fmt:formatDate(cliente.dataDeNascimento, 'yyyy-MM-dd')}" />
    </div>
</fieldset>

<fieldset id="profissionalFields" style="display:none">
    <legend>Informações do Profissional</legend>

    <div>
        <label for="especialidade">Especialidade:</label>
        <input type="text" id="especialidade" name="especialidade" value="${profissional.especialidade}" />
    </div>
</fieldset>

<script>
    // Inicializar a exibição correta dos campos com base no papel atual
    document.addEventListener("DOMContentLoaded", function() {
        showFields();
    });
</script>
