package br.ufscar.dc.dsw.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.service.spec.IConsultaService;
import br.ufscar.dc.dsw.service.spec.IEmailService;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private IConsultaService consultaService;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IProfissionalService profissionalService;

    @Autowired
    private IEmailService emailService;

    @GetMapping("/cadastrar")
    public String cadastrar(Consulta consulta, ModelMap model,
            @RequestParam(required = false, name = "order", defaultValue = "id") String campo) {
        model.addAttribute("clientes", clienteService.buscarTodos(campo));
        model.addAttribute("profissionais", profissionalService.buscarTodos(campo));
        return "consulta/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("consultas", consultaService.buscarTodos());
        return "consulta/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Consulta consulta, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "consulta/cadastro";
        }

        if (consultaService.existeConflito(consulta)) {
            result.rejectValue("dataHora", "error.consulta", "Já existe uma consulta agendada para este horário.");
            return "consulta/cadastro";
        }

        consultaService.salvar(consulta);

        // Recuperando as informações de cliente e profissional
        String emailCliente = consulta.getCliente().getEmail();
        String emailProfissional = consulta.getProfissional().getEmail();
        String assunto = "Confirmação de Consulta";
        String corpoEmail = "Sua consulta foi agendada com sucesso!";

        InternetAddress from = new InternetAddress("lucas.roberto@estudante.ufscar.br", "Gerenciador Academico");

        InternetAddress toCliente = new InternetAddress(emailCliente, consulta.getCliente().getNome());
        InternetAddress toProfissional = new InternetAddress(emailProfissional, consulta.getProfissional().getNome());

        emailService.send(from, toCliente, assunto, corpoEmail);
        emailService.send(from, toProfissional, assunto, corpoEmail);

        attr.addFlashAttribute("success", "Consulta agendada com sucesso.");
        return "redirect:/consultas/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model,
            @RequestParam(required = false, name = "order", defaultValue = "id") String campo) {
        Consulta consulta = consultaService.buscarPorId(id);
        model.addAttribute("consulta", consulta);
        model.addAttribute("clientes", clienteService.buscarTodos(campo));
        model.addAttribute("profissionais", profissionalService.buscarTodos(campo));
        return "consulta/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Consulta consulta, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "consulta/cadastro";
        }

        if (consultaService.existeConflito(consulta)) {
            result.rejectValue("dataHora", "error.consulta", "Já existe uma consulta agendada para este horário.");
            return "consulta/cadastro";
        }

        consultaService.salvar(consulta);
        attr.addFlashAttribute("success", "Consulta atualizada com sucesso.");
        return "redirect:/consultas/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        consultaService.excluir(id);
        attr.addFlashAttribute("success", "Consulta excluída com sucesso.");
        return "redirect:/consultas/listar";
    }
}