package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Sort;

import br.ufscar.dc.dsw.domain.Cliente;

@SuppressWarnings("unchecked")
public interface IClienteDAO extends CrudRepository<Cliente, Long>{

	Cliente findById(long id);

    Cliente findByCPF(String CPF);

	List<Cliente> findAll(Sort sort);
	
	Cliente save(Cliente livro);

	void deleteById(Long id);
}