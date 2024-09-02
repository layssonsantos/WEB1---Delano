package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {

    List<Usuario> findAll();

    Usuario findById(long id);

    Usuario findByCPF(String CPF);

    Usuario findByEmail(String email);

    Usuario findByNome(String nome);

    <S extends Usuario> S save(S usuario);

    void deleteById(Long id);

    @Query("SELECT u FROM Usuario u WHERE u.nome = :nome")
    public Usuario getUsuarioByNome(@Param("nome") String nome);

    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario getUsuarioByEmail(@Param("email") String email);
}
