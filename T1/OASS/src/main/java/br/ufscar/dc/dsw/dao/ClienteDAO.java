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
        String sql = "INSERT INTO Cliente (CPF, telefone, sexo, dataDeNascimento) VALUES (?, ?, ?, ?)";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setLong(1, cliente.getCPF());
            statement.setLong(2, cliente.getTelefone());
            statement.setString(3, cliente.getSexo());
            statement.setDate(4, new java.sql.Date(cliente.getDataDeNascimento().getTime()));
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
                Long cpf = resultSet.getLong("CPF");
                Long telefone = resultSet.getLong("telefone");
                String sexo = resultSet.getString("sexo");
                java.util.Date dataDeNascimento = resultSet.getDate("dataDeNascimento");

                Cliente cliente = new Cliente(cpf, telefone, sexo, dataDeNascimento);
                listaClientes.add(cliente);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaClientes;
    }

    public Cliente get(Long cpf) {
        Cliente cliente = null;
        String sql = "SELECT * FROM Cliente WHERE CPF = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setLong(1, cpf);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long telefone = resultSet.getLong("telefone");
                    String sexo = resultSet.getString("sexo");
                    java.util.Date dataDeNascimento = resultSet.getDate("dataDeNascimento");

                    cliente = new Cliente(cpf, telefone, sexo, dataDeNascimento);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cliente;
    }

    public void update(Cliente cliente) {
        String sql = "UPDATE Cliente SET telefone = ?, sexo = ?, dataDeNascimento = ? WHERE CPF = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setLong(1, cliente.getTelefone());
            statement.setString(2, cliente.getSexo());
            statement.setDate(3, new java.sql.Date(cliente.getDataDeNascimento().getTime()));
            statement.setLong(4, cliente.getCPF());
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
