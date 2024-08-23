package br.ufscar.dc.dsw.domain;

import java.util.List;

import br.ufscar.dc.dsw.validation.UniqueCPF;
import jakarta.persistence.Basic;
import jakarta.persistence.Lob;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Profissional")
public class Profissional extends AbstractEntity<Long> {

    @NotBlank(message = "{NotBlank.profissional.email}")
    @Email
    @Size(max = 60)
    @Column(nullable = false, unique = true, length = 60)
    private String email;

    @UniqueCPF (message = "{Unique.profissional.CPF}")
    @NotBlank(message = "{NotBlank.profissional.cpf}")
    @Size(min = 14, max = 14, message = "{Size.profissional.cpf}")
    @Column(nullable = false, unique = true, length = 14)
    private String CPF;

    @NotBlank(message = "{NotBlank.profissional.nome}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String nome;

    @NotBlank(message = "{NotBlank.profissional.especialidade}")
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String especialidade;

    @Lob
	@Basic
	@Column(length = 10485760) // 1MB
	private byte[] qualificacao;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public byte[] getQualificacao() {
        return qualificacao;
    }

    public void setQualificacao(byte[] qualificacao) {
        this.qualificacao = qualificacao;
    }
}
