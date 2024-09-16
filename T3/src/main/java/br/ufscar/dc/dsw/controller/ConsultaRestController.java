package br.ufscar.dc.dsw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.service.spec.IConsultaService;
import jakarta.validation.Valid;

@RestController
public class ConsultaRestController {

    @Autowired
    private IConsultaService service;

    @GetMapping(path = "/api/consultas")
    public ResponseEntity<List<Consulta>> lista() {
        List<Consulta> lista = service.buscarTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/api/consultas/{id}")
    public ResponseEntity<Consulta> lista(@PathVariable("id") long id) {
        Consulta consulta = service.buscarPorId(id);
        if (consulta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consulta);
    }

    @PostMapping(path = "/api/consultas")
    @ResponseBody
    public ResponseEntity<Consulta> cria(@Valid @RequestBody Consulta consulta, BindingResult result) {
        if (result.hasErrors() || service.existeConflito(consulta)) {
            return ResponseEntity.badRequest().body(null);
        } else {
            service.salvar(consulta);
            return ResponseEntity.ok(consulta);
        }
    }

    @PutMapping(path = "/api/consultas/{id}")
    public ResponseEntity<Consulta> atualiza(@PathVariable("id") long id, @Valid @RequestBody Consulta consulta, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        } else {
            Consulta c = service.buscarPorId(id);
            if (c == null) {
                return ResponseEntity.notFound().build();
            } else {
                consulta.setId(id);
                service.salvar(consulta);
                return ResponseEntity.ok(consulta);
            }
        }
    }

    @DeleteMapping(path = "/api/consultas/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {
        Consulta consulta = service.buscarPorId(id);
        if (consulta == null) {
            return ResponseEntity.notFound().build();
        } else {
            service.excluir(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
    }
}
