package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Consulta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO extends GenericDAO {

    public void insert(Consulta consulta) {
        String sql = "INSERT INTO Consulta (data, hora, CPFCliente, CPFProfissional) VALUES (?, ?, ?, ?)";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setDate(1, consulta.getData());
            statement.setTime(2, consulta.getHora());
            statement.setLong(3, consulta.getCPFCliente());
            statement.setLong(4, consulta.getCPFProfissional());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Consulta> getAll() {
        List<Consulta> listaConsultas = new ArrayList<>();
        String sql = "SELECT * FROM Consulta";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Date data = resultSet.getDate("data");
                Time hora = resultSet.getTime("hora");
                Long CPFCliente = resultSet.getLong("CPFCliente");
                Long CPFProfissional = resultSet.getLong("CPFProfissional");
                Consulta consulta = new Consulta(id, data, hora, CPFCliente, CPFProfissional);
                listaConsultas.add(consulta);
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
                    Date data = resultSet.getDate("data");
                    Time hora = resultSet.getTime("hora");
                    Long CPFCliente = resultSet.getLong("CPFCliente");
                    Long CPFProfissional = resultSet.getLong("CPFProfissional");
                    consulta = new Consulta(id, data, hora, CPFCliente, CPFProfissional);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consulta;
    }

    public void update(Consulta consulta) {
        String sql = "UPDATE Consulta SET data = ?, hora = ?, CPFCliente = ?, CPFProfissional = ? WHERE id = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setDate(1, consulta.getData());
            statement.setTime(2, consulta.getHora());
            statement.setLong(3, consulta.getCPFCliente());
            statement.setLong(4, consulta.getCPFProfissional());
            statement.setLong(5, consulta.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Consulta consulta) {
        String sql = "DELETE FROM Consulta WHERE id = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, consulta.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
