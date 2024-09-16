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

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;
import jakarta.validation.Valid;

@RestController
public class ProfissionalRestController {

    @Autowired
    private IProfissionalService service;

    @GetMapping(path = "/api/profissionais")
    public ResponseEntity<List<Profissional>> lista() {
        List<Profissional> lista = service.buscarTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/api/profissionais/{id}")
    public ResponseEntity<Profissional> lista(@PathVariable("id") long id) {
        Profissional profissional = service.buscarPorId(id);
        if (profissional == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profissional);
    }

    @PostMapping(path = "/api/profissionais")
    @ResponseBody
    public ResponseEntity<Profissional> cria(@Valid @RequestBody Profissional profissional, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        } else {
            service.salvar(profissional);
            return ResponseEntity.ok(profissional);
        }
    }

    @PutMapping(path = "/api/profissionais/{id}")
    public ResponseEntity<Profissional> atualiza(@PathVariable("id") long id, @Valid @RequestBody Profissional profissional, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        } else {
            Profissional p = service.buscarPorId(id);
            if (p == null) {
                return ResponseEntity.notFound().build();
            } else {
                profissional.setId(id);
                service.salvar(profissional);
                return ResponseEntity.ok(profissional);
            }
        }
    }

    @DeleteMapping(path = "/api/profissionais/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {
        Profissional profissional = service.buscarPorId(id);
        if (profissional == null) {
            return ResponseEntity.notFound().build();
        } else {
            service.excluir(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
    }
}
