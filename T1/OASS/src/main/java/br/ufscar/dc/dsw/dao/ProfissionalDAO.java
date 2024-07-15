package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Profissional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfissionalDAO extends GenericDAO {

    public void insert(Profissional profissional) {
        String sql = "INSERT INTO Profissional (CPF, especialidade) VALUES (?, ?)";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setLong(1, profissional.getCPF());
            statement.setString(2, profissional.getEspecialidade());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Profissional> getAll() {
        List<Profissional> listaProfissionais = new ArrayList<>();
        String sql = "SELECT * FROM Profissional";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long cpf = resultSet.getLong("CPF");
                String especialidade = resultSet.getString("especialidade");

                Profissional profissional = new Profissional(cpf, especialidade);
                listaProfissionais.add(profissional);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaProfissionais;
    }

    public Profissional get(Long cpf) {
        Profissional profissional = null;
        String sql = "SELECT * FROM Profissional WHERE CPF = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setLong(1, cpf);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String especialidade = resultSet.getString("especialidade");

                    profissional = new Profissional(cpf, especialidade);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return profissional;
    }

    public void update(Profissional profissional) {
        String sql = "UPDATE Profissional SET especialidade = ? WHERE CPF = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, profissional.getEspecialidade());
            statement.setLong(2, profissional.getCPF());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Profissional profissional) {
        String sql = "DELETE FROM Profissional WHERE CPF = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setLong(1, profissional.getCPF());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
