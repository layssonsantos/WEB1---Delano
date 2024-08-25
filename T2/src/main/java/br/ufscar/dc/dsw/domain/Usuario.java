package br.ufscar.dc.dsw.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Usuario")
@Inheritance(strategy = InheritanceType.JOINED) // Utilizando a estratégia JOINED para herança
public class Usuario extends AbstractEntity<Long> {

    @NotBlank(message = "{NotBlank.usuario.nome}")
    @Size(max = 60)
    @Column(nullable = false,unique = true, length = 60)
    private String nome;

    @NotBlank(message = "{NotBlank.usuario.email}")
    @Email
    @Size(max = 60)
    @Column(nullable = false, unique = true, length = 60)
    private String email;

    @NotBlank(message = "{NotBlank.usuario.CPF}")
    @Size(min = 14, max = 14, message = "{Size.usuario.CPF}")
    @Column(nullable = false, unique = true, length = 14)
    private String CPF;

    @NotBlank(message = "{NotBlank.usuario.senha}")
    @Size(min = 5, message = "{Size.usuario.senha}")
    @Column(nullable = false, length = 255)
    private String senha;

    @Size(max = 15)
    @Column(nullable = false, length = 15)
    private String papel;

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }
}
