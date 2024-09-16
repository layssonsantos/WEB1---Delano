package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalRestController {

    @Autowired
    private IProfissionalService profissionalService;

    @PostMapping
    public ResponseEntity<Profissional> criaProfissional(@RequestBody Profissional profissional,
            @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {

        if (file != null && !file.isEmpty()) {
            profissional.setQualificacao(file.getBytes());
        }
        profissionalService.salvar(profissional);
        return ResponseEntity.status(HttpStatus.CREATED).body(profissional);
    }

    @GetMapping
    public ResponseEntity<List<Profissional>> listaProfissionais() {
        List<Profissional> lista = profissionalService.buscarTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> buscaProfissionalPorId(@PathVariable Long id) {
        Profissional profissional = profissionalService.buscarPorId(id);
        if (profissional == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profissional);
    }

    @GetMapping("/especialidades/{nome}")
    public ResponseEntity<List<Profissional>> buscaProfissionaisPorEspecialidade(
            @PathVariable String nome,
            @RequestParam(name = "campo", defaultValue = "id") String campo) {

        List<Profissional> profissionais = profissionalService.buscarPorEspecialidade(nome, campo);

        if (profissionais.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(profissionais);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profissional> atualizaProfissional(@PathVariable Long id,
            @RequestBody Profissional profissionalAtualizado,
            @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {

        Profissional profissional = profissionalService.buscarPorId(id);
        if (profissional == null) {
            return ResponseEntity.notFound().build();
        }

        if (file != null && !file.isEmpty()) {
            profissionalAtualizado.setQualificacao(file.getBytes());
        }

        profissionalAtualizado.setId(id); // Certifique-se de que o ID é mantido
        profissionalService.salvar(profissionalAtualizado);
        return ResponseEntity.ok(profissionalAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProfissional(@PathVariable Long id) {
        Profissional profissional = profissionalService.buscarPorId(id);
        if (profissional == null) {
            return ResponseEntity.notFound().build();
        }
        profissionalService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadQualificacao(@PathVariable Long id) {
        Profissional profissional = profissionalService.buscarPorId(id);
        if (profissional == null || profissional.getQualificacao() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .body(profissional.getQualificacao());
    }
}
