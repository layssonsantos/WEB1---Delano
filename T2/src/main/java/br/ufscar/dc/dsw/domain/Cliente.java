package br.ufscar.dc.dsw.domain;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "clientes")
public class Cliente extends AbstractEntity<Long> {

    @NotBlank
    @Email
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @NotBlank
    @Size(min = 11, max = 11)
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String nome;

    @Size(max = 20)
    @Column(length = 20)
    private String telefone;

    @Size(max = 1)
    @Column(length = 1)
    private String sexo;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @OneToMany(mappedBy = "cliente")
    private List<Consulta> consultas;

    // Getters e Setters
}
