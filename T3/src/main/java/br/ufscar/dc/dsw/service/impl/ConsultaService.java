package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IConsultaDAO;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.service.spec.IConsultaService;
import br.ufscar.dc.dsw.domain.Usuario;

@Service
@Transactional(readOnly = false)
public class ConsultaService implements IConsultaService {

    @Autowired
    IConsultaDAO dao;

    public void salvar(Consulta consulta) {
        dao.save(consulta);
    }

    public void excluir(Long id) {
        dao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Consulta buscarPorId(Long id) {
        return dao.findById(id.longValue());
    }

    @Transactional(readOnly = true)
    public List<Consulta> buscarPorUsuario(Usuario usuario) {
        return dao.findByUsuario(usuario);
    }

    @Transactional(readOnly = true)
    public List<Consulta> buscarTodos() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public boolean existeConflito(Consulta consulta) {
        List<Consulta> consultas = dao.findByProfissionalAndDataHora(consulta.getProfissional(), consulta.getDataHora());
        List<Consulta> consultas2 = dao.findByClienteAndDataHora(consulta.getCliente(), consulta.getDataHora());
        if(!consultas.isEmpty()){
            return true;
        }
        if(!consultas2.isEmpty()){
            return true;
        }
        return false;
    }
}