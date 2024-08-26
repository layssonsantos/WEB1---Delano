package br.ufscar.dc.dsw.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort;

import br.ufscar.dc.dsw.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;

@Service
@Transactional(readOnly = false)
public class ProfissionalService implements IProfissionalService {

    @Autowired
    IProfissionalDAO dao;
    
    public void salvar(Profissional profissional) {
        dao.save(profissional);
    }

    public void excluir(Long id) {
        dao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Profissional buscarPorId(Long id) {
        return dao.findById(id.longValue()).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Profissional> buscarTodos(String campo) {
        List<Profissional> profissionais = new ArrayList<>();
        for (Profissional profissional : dao.findAll(orderBy(campo))) {
            profissionais.add(profissional);
        }
        return profissionais;
    }

    @Transactional(readOnly = true)
    public List<Profissional> buscarTodos() {
        List<Profissional> profissionais = new ArrayList<>();
        for (Profissional profissional : dao.findAll()) {
            profissionais.add(profissional);
        }
        return profissionais;
    }

    @Transactional(readOnly = true)
    public List<Profissional> buscarPorEspecialidade(String especialidade, String campo) {
        List<Profissional> profissionais = new ArrayList<>();
        for (Profissional profissional : dao.findByEspecialidade(especialidade, orderBy(campo))) {
            profissionais.add(profissional);
        }
        return profissionais;
    }

    private Sort orderBy(String campo) {
        return Sort.by(Order.by(campo));
    }
}
