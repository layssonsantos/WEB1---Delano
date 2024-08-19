package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Livro;

public interface ILivroService {

	Livro buscarPorId(Long id);
	
	List<Livro> buscarTodos(String campo);
	
	void salvar(Livro livro);
	
	void excluir(Long id);
	
}
