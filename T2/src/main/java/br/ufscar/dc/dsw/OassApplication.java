package br.ufscar.dc.dsw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;

import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;

@SpringBootApplication
public class OassApplication {

	public static void main(String[] args) {
		SpringApplication.run(OassApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IClienteDAO clienteDAO) {
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

		};
	}

}
