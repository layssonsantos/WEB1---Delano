package br.ufscar.dc.dsw.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import br.ufscar.dc.dsw.validation.UniqueCPF;
import jakarta.persistence.Basic;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Past;

@SuppressWarnings("serial")
@Entity
@Table(name = "Cliente")
public class Cliente extends AbstractEntity<Long> {

    @NotBlank(message = "{NotBlank.cliente.email}")
    @Email
    @Size(max = 60)
    @Column(nullable = false, unique = true, length = 60)
    private String email;

    @UniqueCPF (message = "{Unique.cliente.CPF}")
    @NotBlank(message = "{NotBlank.cliente.cpf}")
    @Size(min = 14, max = 14, message = "{Size.cliente.cpf}")
    @Column(nullable = false, unique = true, length = 14)
    private String CPF;

    @NotBlank(message = "{NotBlank.cliente.nome}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String nome;

    @NotBlank(message = "{NotBlank.cliente.telefone}")
    @Size(min = 15, max = 15, message = "{Size.cliente.telefone}")
    @Column(nullable = false, length = 60)
    private String telefone;

    @NotBlank(message = "{NotBlank.cliente.sexo}")
    @Size(max = 10)
    @Column(nullable = false, length = 10)
    private String sexo;

    @Basic
    @NotNull(message = "{NotNull.cliente.data}")
    @Past(message = "A data de nascimento deve estar no passado.")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "dataNascimento", nullable = false)
    private Date dataNascimento;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cPF) {
        CPF = cPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
