package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufscar.dc.dsw.service.spec.IProfissionalService;

@Controller
public class HomeController {
    @Autowired
    private IProfissionalService profissionalService;

    @GetMapping("/")
    public String home(ModelMap model, @RequestParam(required = false, name = "especialidade") String especialidade, 
                      @RequestParam(required = false, name = "order", defaultValue = "id") String campo) {
        if (especialidade != null && !especialidade.isEmpty()) {
            model.addAttribute("profissionais", profissionalService.buscarPorEspecialidade(especialidade, campo));
        } else {
            model.addAttribute("profissionais", profissionalService.buscarTodosCampo(campo));
        }
        return "home";
    }
    
    @GetMapping("/pesquisar")
    public String pesquisarPorEspecialidade(@RequestParam(name = "especialidade", required = false) String especialidade,
                                            @RequestParam(name = "order", defaultValue = "id") String campo, ModelMap model) {
        if (especialidade != null && !especialidade.isEmpty()) {
            model.addAttribute("profissionais", profissionalService.buscarPorEspecialidade(especialidade, campo));
        } else {
            model.addAttribute("profissionais", profissionalService.buscarTodosCampo(campo));
        }
        return "home";
    }
}
