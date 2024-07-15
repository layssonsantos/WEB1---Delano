package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends GenericDAO {

    public void insert(Cliente cliente) {
        String sql = "INSERT INTO Cliente (name, email, senha, CPF, telefone, sexo, dataDeNascimento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, cliente.getName());
            statement.setString(2, cliente.getEmail());
            statement.setString(3, cliente.getSenha());
            statement.setLong(4, cliente.getCPF());
            statement.setLong(5, cliente.getTelefone());
            statement.setString(6, cliente.getSexo());
            statement.setDate(7, cliente.getDataDeNascimento());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> getAll() {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                Long CPF = resultSet.getLong("CPF");
                Long telefone = resultSet.getLong("telefone");
                String sexo = resultSet.getString("sexo");
                Date dataDeNascimento = resultSet.getDate("dataDeNascimento");
                Cliente cliente = new Cliente(name, email, senha, CPF, telefone, sexo, dataDeNascimento);
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaClientes;
    }

    public Cliente get(Long CPF) {
        Cliente cliente = null;
        String sql = "SELECT * FROM Cliente WHERE CPF = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, CPF);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String senha = resultSet.getString("senha");
                    Long telefone = resultSet.getLong("telefone");
                    String sexo = resultSet.getString("sexo");
                    Date dataDeNascimento = resultSet.getDate("dataDeNascimento");
                    cliente = new Cliente(name, email, senha, CPF, telefone, sexo, dataDeNascimento);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

    public void update(Cliente cliente) {
        String sql = "UPDATE Cliente SET name = ?, email = ?, senha = ?, telefone = ?, sexo = ?, dataDeNascimento = ? WHERE CPF = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, cliente.getName());
            statement.setString(2, cliente.getEmail());
            statement.setString(3, cliente.getSenha());
            statement.setLong(4, cliente.getTelefone());
            statement.setString(5, cliente.getSexo());
            statement.setDate(6, cliente.getDataDeNascimento());
            statement.setLong(7, cliente.getCPF());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Cliente cliente) {
        String sql = "DELETE FROM Cliente WHERE CPF = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, cliente.getCPF());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
