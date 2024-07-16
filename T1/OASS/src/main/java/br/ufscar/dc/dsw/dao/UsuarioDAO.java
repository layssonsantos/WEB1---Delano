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
        String sql = "INSERT INTO Usuario (nome, email, senha, CPF, papel) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setLong(4, usuario.getCPF());
            statement.setString(5, usuario.getPapel());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> getAll() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario ORDER BY nome";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                Long CPF = resultSet.getLong("CPF");
                String papel = resultSet.getString("papel");

                Usuario usuario = new Usuario(nome, email, senha, CPF, papel);
                listaUsuarios.add(usuario);
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
                    String nome = resultSet.getString("nome");
                    String email = resultSet.getString("email");
                    String senha = resultSet.getString("senha");
                    String papel = resultSet.getString("papel");

                    usuario = new Usuario(nome, email, senha, cpf, papel);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usuario;
    }

    public void update(Usuario usuario) {
        String sql = "UPDATE Usuario SET nome = ?, email = ?, senha = ?, papel = ? WHERE CPF = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setString(4, usuario.getPapel());
            statement.setLong(5, usuario.getCPF());
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
