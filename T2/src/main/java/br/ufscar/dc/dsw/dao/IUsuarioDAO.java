package br.ufscar.dc.dsw.dao;

import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.domain.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {

    Usuario findById(long id);

    Usuario findByCPF(String CPF);

    Usuario findByEmail(String email);

    Usuario findByNome(String nome);

    <S extends Usuario> S save(S usuario);

    void deleteById(Long id);
}
