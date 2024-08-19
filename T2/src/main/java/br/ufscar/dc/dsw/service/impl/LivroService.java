package br.ufscar.dc.dsw.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.ILivroDAO;
import br.ufscar.dc.dsw.domain.Livro;
import br.ufscar.dc.dsw.service.spec.ILivroService;

@Service
@Transactional(readOnly = false)
public class LivroService implements ILivroService {

	@Autowired
	ILivroDAO dao;
	
	public void salvar(Livro livro) {
		dao.save(livro);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Livro buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Livro> buscarTodos(String campo) {
		List<Livro> livros = new ArrayList<>();
		for (Livro livro: dao.findAll(orderBy(campo))) {
			livros.add(livro);
		}
		return livros;
	}
	
	private Sort orderBy(String campo) {
		return Sort.by(Order.by(campo));
	}
}
