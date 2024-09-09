package br.ufscar.dc.dsw.dao;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.domain.Profissional;

public interface IProfissionalDAO extends CrudRepository<Profissional, Long> {

    List<Profissional> findByEspecialidade(String especialidade, Sort sort);

    List<Profissional> findAll(Sort sort);

    <S extends Profissional> S save(S profissional);

    void deleteById(Long id);
}
