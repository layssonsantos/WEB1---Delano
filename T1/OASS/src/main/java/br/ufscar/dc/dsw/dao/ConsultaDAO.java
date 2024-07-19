package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Consulta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO extends GenericDAO {

    public void insert(Consulta consulta) {
        String sql = "INSERT INTO Consulta (dataHora, CPFCliente, CPFProfissional) VALUES (?, ?, ?)";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            
            statement.setTimestamp(1, Timestamp.valueOf(consulta.getDataHora()));
            statement.setLong(2, consulta.getCPFCliente());
            statement.setLong(3, consulta.getCPFProfissional());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Consulta> getAll() {
        List<Consulta> listaConsultas = new ArrayList<>();
        String sql = "SELECT * FROM Consulta ORDER BY dataHora";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                listaConsultas.add(createConsultaFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsultas;
    }

    public Consulta get(Long id) {
        Consulta consulta = null;
        String sql = "SELECT * FROM Consulta WHERE id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    consulta = createConsultaFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consulta;
    }

    private Consulta createConsultaFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        LocalDateTime dataHora = resultSet.getTimestamp("dataHora").toLocalDateTime();
        Long CPFCliente = resultSet.getLong("CPFCliente");
        Long CPFProfissional = resultSet.getLong("CPFProfissional");

        return new Consulta(id, dataHora, CPFCliente, CPFProfissional);
    }

    public void update(Consulta consulta) {
        String sql = "UPDATE Consulta SET dataHora = ?, CPFCliente = ?, CPFProfissional = ? WHERE id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setTimestamp(1, Timestamp.valueOf(consulta.getDataHora()));
            statement.setLong(2, consulta.getCPFCliente());
            statement.setLong(3, consulta.getCPFProfissional());
            statement.setLong(4, consulta.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM Consulta WHERE id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Consulta> getByEspecialidade(String especialidade) {
        List<Consulta> listaConsultas = new ArrayList<>();
        String sql = "SELECT c.* FROM Consulta c " +
                     "JOIN Usuario u ON c.CPFProfissional = u.CPF " +
                     "WHERE u.especialidade = ? ORDER BY c.dataHora";
        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, especialidade);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    listaConsultas.add(createConsultaFromResultSet(resultSet));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsultas;
    }
}
