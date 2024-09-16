package br.ufscar.dc.dsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	public CommandLineRunner demo(IUsuarioDAO UsuarioDAO, BCryptPasswordEncoder encoder, IProfissionalDAO profissionalDAO, IClienteDAO clienteDAO) {
		return (args) -> {

			//Criando administrador
			Usuario admin = new Usuario("admin", "admin@admin.com", "000.000.000-00", encoder.encode("admin"), "ROLE_ADMIN");
			UsuarioDAO.save(admin);
			

			// Criando clientes
			Cliente cliente1 = new Cliente("Maria Eduarda", "daniel.sobrinho0017@gmail.com", "133.456.789-00", encoder.encode("12345678"), 
			"ROLE_CLIENTE", "(11) 98765-4321", "Feminino", new SimpleDateFormat("dd/MM/yyyy").parse("15/03/1985"));
			clienteDAO.save(cliente1);

			Cliente cliente2 = new Cliente("João Santos", "joao.santos@example.com", "987.654.321-00", encoder.encode("87654321"),
			"ROLE_CLIENTE", "(21) 91234-5678", "Masculino", new SimpleDateFormat("dd/MM/yyyy").parse("22/07/1990"));
			clienteDAO.save(cliente2);

			Cliente cliente3 = new Cliente("Ana Souza", "ana.souza@example.com", "456.788.123-00", encoder.encode("45678912"),
			"ROLE_CLIENTE", "(31) 99876-5432", "Feminino", new SimpleDateFormat("dd/MM/yyyy").parse("08/11/1988"));
			clienteDAO.save(cliente3);

			// Criando profissionais
			Profissional p1 = new Profissional("Ana Maria Silva", "lucas.roberto@estudante.ufscar.br", "124.456.789-00", encoder.encode("12345678"),
			"ROLE_PROFISSIONAL", "Cardiologia", Files.readAllBytes(Path.of("ana_silva_qualificacao.pdf")));
			profissionalDAO.save(p1);

			Profissional p2 = new Profissional("João Pedro Souza", "joao.souza@example.com", "988.654.321-00", encoder.encode("87654321"),
			"ROLE_PROFISSIONAL", "Dermatologia", Files.readAllBytes(Path.of("joao_souza_qualificacao.pdf")));
			profissionalDAO.save(p2);

			Profissional p3 = new Profissional("Carla Rodrigues", "carla.rodrigues@example.com", "456.789.120-00", encoder.encode("45678912"),
			"ROLE_PROFISSIONAL", "Neurologia", Files.readAllBytes(Path.of("carla_rodrigues_qualificacao.pdf")));
			profissionalDAO.save(p3);
		};
	}
}
