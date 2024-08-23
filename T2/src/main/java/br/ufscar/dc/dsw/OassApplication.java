package br.ufscar.dc.dsw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.file.Files;
import java.text.SimpleDateFormat;

import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.dao.IProfissionalDAO;

import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
public class OassApplication {

	public static void main(String[] args) {
		SpringApplication.run(OassApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IClienteDAO clienteDAO, IProfissionalDAO profissionalDAO) {
		return (args) -> {

			// Criando clientes
			// Exemplo 1
			Cliente cliente1 = new Cliente();
			cliente1.setNome("Maria Oliveira");
			cliente1.setEmail("maria.oliveira@example.com");
			cliente1.setCPF("123.456.789-00");
			cliente1.setTelefone("(11) 98765-4321");
			cliente1.setSexo("Feminino");
			cliente1.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("15/03/1985"));
			clienteDAO.save(cliente1);

			// Exemplo 2
			Cliente cliente2 = new Cliente();
			cliente2.setNome("João Santos");
			cliente2.setEmail("joao.santos@example.com");
			cliente2.setCPF("987.654.321-00");
			cliente2.setTelefone("(21) 91234-5678");
			cliente2.setSexo("Masculino");
			cliente2.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("22/07/1990"));
			clienteDAO.save(cliente2);

			// Exemplo 3
			Cliente cliente3 = new Cliente();
			cliente3.setNome("Ana Souza");
			cliente3.setEmail("ana.souza@example.com");
			cliente3.setCPF("456.789.123-00");
			cliente3.setTelefone("(31) 99876-5432");
			cliente3.setSexo("Feminino");
			cliente3.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("08/11/1988"));
			clienteDAO.save(cliente3);

			Profissional p1 = new Profissional();
			p1.setNome("Ana Maria Silva");
			p1.setEmail("ana.silva@example.com");
			p1.setCPF("123.456.789-00");
			p1.setEspecialidade("Cardiologia");
			p1.setQualificacao(Files.readAllBytes(Path.of("ana_silva_qualificacao.pdf")));
			profissionalDAO.save(p1);

			Profissional p2 = new Profissional();
			p2.setNome("João Pedro Souza");
			p2.setEmail("joao.souza@example.com");
			p2.setCPF("987.654.321-00");
			p2.setEspecialidade("Dermatologia");
			p2.setQualificacao(Files.readAllBytes(Path.of("joao_souza_qualificacao.pdf")));
			profissionalDAO.save(p2);

			Profissional p3 = new Profissional();
			p3.setNome("Carla Rodrigues");
			p3.setEmail("carla.rodrigues@example.com");
			p3.setCPF("456.789.123-00");
			p3.setEspecialidade("Neurologia");
			p3.setQualificacao(Files.readAllBytes(Path.of("carla_rodrigues_qualificacao.pdf")));
			profissionalDAO.save(p3);
		};
	}
}
