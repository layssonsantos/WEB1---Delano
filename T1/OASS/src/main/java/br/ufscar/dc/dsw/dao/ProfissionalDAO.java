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
        String sql = "INSERT INTO Profissional (name, email, senha, CPF, especialidade) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, profissional.getName());
            statement.setString(2, profissional.getEmail());
            statement.setString(3, profissional.getSenha());
            statement.setLong(4, profissional.getCPF());
            statement.setString(5, profissional.getEspecialidade());
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
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                Long CPF = resultSet.getLong("CPF");
                String especialidade = resultSet.getString("especialidade");
                Profissional profissional = new Profissional(name, email, senha, CPF, especialidade);
                listaProfissionais.add(profissional);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProfissionais;
    }

    public Profissional get(Long CPF) {
        Profissional profissional = null;
        String sql = "SELECT * FROM Profissional WHERE CPF = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, CPF);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String senha = resultSet.getString("senha");
                    String especialidade = resultSet.getString("especialidade");
                    profissional = new Profissional(name, email, senha, CPF, especialidade);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }

    public void update(Profissional profissional) {
        String sql = "UPDATE Profissional SET name = ?, email = ?, senha = ?, especialidade = ? WHERE CPF = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, profissional.getName());
            statement.setString(2, profissional.getEmail());
            statement.setString(3, profissional.getSenha());
            statement.setString(4, profissional.getEspecialidade());
            statement.setLong(5, profissional.getCPF());
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
