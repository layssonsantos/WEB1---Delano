package br.ufscar.dc.dsw.controller;

import jakarta.validation.Valid;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.InternetAddress;

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
import org.springframework.security.core.context.SecurityContextHolder;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.service.spec.IConsultaService;
import br.ufscar.dc.dsw.service.spec.IEmailService;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.domain.Usuario;

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
        model.addAttribute("clientes", clienteService.buscarTodos());
        model.addAttribute("profissionais", profissionalService.buscarTodos());
        return "consulta/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("consultas", consultaService.buscarTodos());
        return "consulta/lista";
    }

    private Usuario getUsuario() {
        UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usuarioDetails.getUsuario();
    }

    @GetMapping("/minhasConsultas")
    public String MinhasConsultas(ModelMap model) {
        model.addAttribute("consultas", consultaService.buscarPorUsuario(this.getUsuario()));
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

        InternetAddress from = new InternetAddress();
        InternetAddress toCliente = new InternetAddress();
        InternetAddress toProfissional = new InternetAddress();

        try {

            from.setAddress("lucas.roberto@estudante.ufscar.br");
            from.setPersonal("Gerenciador Academico", "UTF-8");

            toCliente.setAddress(emailCliente);
            toCliente.setPersonal(consulta.getCliente().getNome(), "UTF-8");

            toProfissional.setAddress(emailProfissional);
            toProfissional.setPersonal(consulta.getProfissional().getNome(), "UTF-8");
            
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        

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
        model.addAttribute("clientes", clienteService.buscarTodosCampo(campo));
        model.addAttribute("profissionais", profissionalService.buscarTodosCampo(campo));
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