package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Usuario;

public interface IConsultaService {

    Consulta buscarPorId(Long id);

    List<Consulta> buscarPorUsuario(Usuario usuario);

    List<Consulta> buscarTodos();

    void salvar(Consulta consulta);

    void excluir(Long id);

    boolean existeConflito(Consulta consulta);
}