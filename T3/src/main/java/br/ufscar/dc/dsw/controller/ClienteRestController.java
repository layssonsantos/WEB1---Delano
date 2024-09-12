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

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import jakarta.validation.Valid;

@RestController
public class ClienteRestController {

    @Autowired
    private IClienteService service;

    @GetMapping(path = "/api/clientes")
    public ResponseEntity<List<Cliente>> lista() {
        List<Cliente> lista = service.buscarTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/api/clientes/{id}")
    public ResponseEntity<Cliente> lista(@PathVariable("id") long id) {
        Cliente cliente = service.buscarPorId(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @PostMapping(path = "/api/clientes")
    @ResponseBody
    public ResponseEntity<Cliente> cria(@Valid @RequestBody Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        } else {
            service.salvar(cliente);
            return ResponseEntity.ok(cliente);
        }
    }

    @PutMapping(path = "/api/clientes/{id}")
    public ResponseEntity<Cliente> atualiza(@PathVariable("id") long id, @Valid @RequestBody Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        } else {
            Cliente c = service.buscarPorId(id);
            if (c == null) {
                return ResponseEntity.notFound().build();
            } else {
                cliente.setId(id);
                service.salvar(cliente);
                return ResponseEntity.ok(cliente);
            }
        }
    }

    @DeleteMapping(path = "/api/clientes/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {
        Cliente cliente = service.buscarPorId(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        } else {
            service.excluir(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
    }
}
