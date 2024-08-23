package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;

@Controller
@RequestMapping("/profissionais")
public class ProfissionalController {

    @Autowired
    private IProfissionalService profissionalService;

    @GetMapping("/cadastrar")
    public String cadastrar(Profissional profissional) {
        return "profissional/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("profissionais", profissionalService.buscarTodos());
        return "profissional/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr, @RequestParam(name ="file") MultipartFile file) throws IOException {

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "profissional/cadastro";
        }

        if (!file.getOriginalFilename().isBlank()) {
			profissional.setQualificacao(file.getBytes());
		}

        profissionalService.salvar(profissional);
        attr.addFlashAttribute("success", "Profissional inserido com sucesso.");
        return "redirect:/profissionais/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("profissional", profissionalService.buscarPorId(id));
        return "profissional/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr, @RequestParam(name ="file") MultipartFile file) throws IOException {

        if (result.getFieldErrorCount() > 1 || result.getFieldError("CPF")  == null) {
			return "profissional/cadastro";
		}

        if (!file.getOriginalFilename().isBlank()) {
			profissional.setQualificacao(file.getBytes());
		}

        profissionalService.salvar(profissional);
        attr.addFlashAttribute("success", "Profissional editado com sucesso.");
        return "redirect:/profissionais/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        profissionalService.excluir(id);
        attr.addFlashAttribute("success", "Profissional excluído com sucesso.");
        return "redirect:/profissionais/listar";
    }
 
	@GetMapping(value = "/download/{id}")
	public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) {
		Profissional profissional = profissionalService.buscarPorId(id);

		// set content type
		response.setContentType("application/pdf");
		
		try {
			// copies all bytes to an output stream
			response.getOutputStream().write(profissional.getQualificacao());

			// flushes output stream
			response.getOutputStream().flush();
		} catch (IOException e) {
			System.out.println("Error :- " + e.getMessage());
		}
	}
}
