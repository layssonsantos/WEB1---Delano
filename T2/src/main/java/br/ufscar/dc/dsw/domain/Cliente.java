package br.ufscar.dc.dsw.domain;

import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
@Entity
@Table(name = "Cliente")
public class Cliente extends Usuario {

    @NotBlank(message = "{NotBlank.cliente.telefone}")
    @Size(min = 15, max = 15, message = "{Size.cliente.telefone}")
    @Column(nullable = false, length = 15)
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

    // Getters e Setters

    public Cliente() {
    }

    public Cliente(String nome, String email, String CPF, String senha, String papel, String telefone, String sexo, Date dataNascimento) {
        super(nome, email, CPF, senha, papel);
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
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
