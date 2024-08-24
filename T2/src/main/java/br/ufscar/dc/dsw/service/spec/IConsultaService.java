package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Consulta;

public interface IConsultaService {

    Consulta buscarPorId(Long id);

    List<Consulta> buscarTodos();

    void salvar(Consulta consulta);

    void excluir(Long id);

    boolean existeConflito(Consulta consulta);
}
