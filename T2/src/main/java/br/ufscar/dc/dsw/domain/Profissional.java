package br.ufscar.dc.dsw.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Profissional")
public class Profissional extends Usuario {

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String especialidade;

    @Lob
    @Basic
    @Column(length = 10485760) // 1MB
    private byte[] qualificacao;

    // Getters e Setters

    public Profissional() {
    }

    public Profissional(String nome, String email, String CPF, String senha, String papel, String especialidade, byte[] qualificacao) {
        super(nome, email, CPF, senha, papel);
        this.especialidade = especialidade;
        this.qualificacao = qualificacao;
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
