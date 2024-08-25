package br.ufscar.dc.dsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import java.nio.file.Files;
import java.text.SimpleDateFormat;

import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;

import java.nio.file.Path;

@SpringBootApplication
public class OassApplication {

	public static void main(String[] args) {
		SpringApplication.run(OassApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IClienteDAO clienteDAO, IProfissionalDAO profissionalDAO, IUsuarioDAO UsuarioDAO) {
		return (args) -> {

			//Criando administrador
			Usuario admin = new Usuario();
			admin.setNome("admin");
			admin.setEmail("admin@admin.com");
			admin.setSenha("admin");
			admin.setCPF("123.456.789-00");
			admin.setPapel("ADMIN");
			UsuarioDAO.save(admin);
			

			// Criando clientes
			Cliente cliente1 = new Cliente();
			cliente1.setNome("Maria Eduarda");
			cliente1.setEmail("maria@example.com");
			cliente1.setSenha("12345678");
			cliente1.setCPF("133.456.789-00");
			cliente1.setPapel("CLIENTE");
			cliente1.setTelefone("(11) 98765-4321");
			cliente1.setSexo("Feminino");
			cliente1.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("15/03/1985"));
			clienteDAO.save(cliente1);

			Cliente cliente2 = new Cliente();
			cliente2.setNome("João Santos");
			cliente2.setEmail("joao.santos@example.com");
			cliente2.setSenha("87654321");
			cliente2.setCPF("987.654.321-00");
			cliente2.setPapel("CLIENTE");
			cliente2.setTelefone("(21) 91234-5678");
			cliente2.setSexo("Masculino");
			cliente2.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("22/07/1990"));
			clienteDAO.save(cliente2);

			Cliente cliente3 = new Cliente();
			cliente3.setNome("Ana Souza");
			cliente3.setEmail("ana.souza@example.com");
			cliente3.setSenha("45678912");
			cliente3.setCPF("456.788.123-00");
			cliente3.setPapel("CLIENTE");
			cliente3.setTelefone("(31) 99876-5432");
			cliente3.setSexo("Feminino");
			cliente3.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("08/11/1988"));
			clienteDAO.save(cliente3);

			// Criando profissionais
			Profissional p1 = new Profissional();
			p1.setNome("Ana Maria Silva");
			p1.setEmail("ana.silva@example.com");
			p1.setSenha("12345678");
			p1.setCPF("124.456.789-00");
			p1.setPapel("PROFISSIONAL");
			p1.setEspecialidade("Cardiologia");
			p1.setQualificacao(Files.readAllBytes(Path.of("ana_silva_qualificacao.pdf")));
			profissionalDAO.save(p1);

			Profissional p2 = new Profissional();
			p2.setNome("João Pedro Souza");
			p2.setEmail("joao.souza@example.com");
			p2.setSenha("87654321");
			p2.setCPF("988.654.321-00");
			p2.setPapel("PROFISSIONAL");
			p2.setEspecialidade("Dermatologia");
			p2.setQualificacao(Files.readAllBytes(Path.of("joao_souza_qualificacao.pdf")));
			profissionalDAO.save(p2);

			Profissional p3 = new Profissional();
			p3.setNome("Carla Rodrigues");
			p3.setEmail("carla.rodrigues@example.com");
			p3.setSenha("45678912");
			p3.setCPF("466.789.123-00");
			p3.setPapel("PROFISSIONAL");
			p3.setEspecialidade("Neurologia");
			p3.setQualificacao(Files.readAllBytes(Path.of("carla_rodrigues_qualificacao.pdf")));
			profissionalDAO.save(p3);
		};
	}
}
