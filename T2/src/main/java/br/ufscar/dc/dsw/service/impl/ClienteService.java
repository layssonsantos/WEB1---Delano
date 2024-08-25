package br.ufscar.dc.dsw.service.impl;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort;

import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.IClienteService;

@Service
@Transactional(readOnly = false)
public class ClienteService implements IClienteService {

	@Autowired
	IClienteDAO dao;
	
	public void salvar(Cliente cliente) {
		dao.save(cliente);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Cliente buscarPorId(Long id) {
		return dao.findById(id.longValue()).orElse(null);
	}

	@Transactional(readOnly = true)
	public List<Cliente> buscarTodos(String campo) {
		List<Cliente> clientes = new ArrayList<>();
		for (Cliente cliente: dao.findAll(orderBy(campo))) {
			clientes.add(cliente);
		}
		return clientes;
	}

	private Sort orderBy(String campo) {
		return Sort.by(Order.by(campo));
	}
}
