package br.ufscar.dc.dsw.dao;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.domain.Cliente;

public interface IClienteDAO extends CrudRepository<Cliente, Long> {

    List<Cliente> findAll(Sort sort);

    <S extends Cliente> S save(S cliente);

    void deleteById(Long id);
}
