package br.ufscar.dc.dsw.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;

@SuppressWarnings("unchecked")
public interface IConsultaDAO extends CrudRepository<Consulta, Long> {

    Consulta findById(long id);
    
    List<Consulta> findAll();
    
    Consulta save(Consulta consulta);
    
    void deleteById(Long id);

    List<Consulta> findByCliente(Cliente cliente);
    
    List<Consulta> findByProfissional(Profissional profissional);
    
    List<Consulta> findByDataHora(Date dataHora);

    List<Consulta> findByProfissionalAndDataHora(Profissional profissional, Date dataHora);
    
    boolean existsByProfissionalAndDataHora(Profissional profissional, Date dataHora);
    
    boolean existsByClienteAndDataHora(Cliente cliente, Date dataHora);
}
