package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends GenericDAO {

    public void insert(Usuario usuario) {
        String sql = "INSERT INTO Usuario (nome, email, senha, CPF, papel, telefone, sexo, dataDeNascimento, especialidade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
    
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setLong(4, usuario.getCPF());
            statement.setString(5, usuario.getPapel());
            setOptionalParameters(statement, usuario, 6);
            statement.executeUpdate();
    
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    

    private void setOptionalParameters(PreparedStatement statement, Usuario usuario, int startIndex) throws SQLException {
        if ("CLIENTE".equals(usuario.getPapel()) || "AMBOS".equals(usuario.getPapel())) {
            statement.setLong(startIndex, usuario.getTelefone());
            statement.setString(startIndex + 1, usuario.getSexo());
            statement.setDate(startIndex + 2, usuario.getDataDeNascimento() != null ? java.sql.Date.valueOf(usuario.getDataDeNascimento()) : null);
        } else {
            statement.setNull(startIndex, java.sql.Types.BIGINT);
            statement.setNull(startIndex + 1, java.sql.Types.VARCHAR);
            statement.setNull(startIndex + 2, java.sql.Types.DATE);
        }
    
        if ("PROFISSIONAL".equals(usuario.getPapel()) || "AMBOS".equals(usuario.getPapel())) {
            statement.setString(startIndex + 3, usuario.getEspecialidade());
        } else {
            statement.setNull(startIndex + 3, java.sql.Types.VARCHAR);
        }
    }
    

    public List<Usuario> getAll() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario ORDER BY nome";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                listaUsuarios.add(createUsuarioFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaUsuarios;
    }

    public Usuario get(Long cpf) {
        Usuario usuario = null;
        String sql = "SELECT * FROM Usuario WHERE CPF = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setLong(1, cpf);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = createUsuarioFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usuario;
    }

    public Usuario get(String email, String senha) {
        Usuario usuario = null;
        String sql = "SELECT * FROM Usuario WHERE email = ? AND senha = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, email);
            statement.setString(2, senha);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = createUsuarioFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usuario;
    }

    private Usuario createUsuarioFromResultSet(ResultSet resultSet) throws SQLException {
        String nome = resultSet.getString("nome");
        String email = resultSet.getString("email");
        String senha = resultSet.getString("senha");
        Long CPF = resultSet.getLong("CPF");
        String papel = resultSet.getString("papel");

        Long telefone = resultSet.getLong("telefone");
        String sexo = resultSet.getString("sexo");
        java.sql.Date dataDeNascimento = resultSet.getDate("dataDeNascimento");
        String especialidade = resultSet.getString("especialidade");

        if ("ADMIN".equals(papel)) {
            return new Usuario(nome, email, senha, CPF, papel);
        } else if ("CLIENTE".equals(papel)) {
            return new Usuario(nome, email, senha, CPF, papel, telefone, sexo, dataDeNascimento != null ? dataDeNascimento.toLocalDate() : null);
        } else if ("PROFISSIONAL".equals(papel)) {
            return new Usuario(nome, email, senha, CPF, papel, especialidade);
        } else if ("AMBOS".equals(papel)) {
            return new Usuario(nome, email, senha, CPF, papel, telefone, sexo, dataDeNascimento != null ? dataDeNascimento.toLocalDate() : null, especialidade);
        } else {
            throw new SQLException("Tipo de papel desconhecido: " + papel);
        }
    }

    public void update(Usuario usuario) {
        String sql = "UPDATE Usuario SET nome = ?, email = ?, senha = ?, papel = ?, telefone = ?, sexo = ?, dataDeNascimento = ?, especialidade = ? WHERE CPF = ?";
    
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
    
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setString(4, usuario.getPapel());
            setOptionalParameters(statement, usuario, 5);
            statement.setLong(9, usuario.getCPF());
            statement.executeUpdate();
    
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    

    public void delete(Usuario usuario) {
        String sql = "DELETE FROM Usuario WHERE CPF = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setLong(1, usuario.getCPF());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
