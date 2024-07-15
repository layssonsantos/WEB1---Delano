package br.ufscar.dc.dsw.domain;

import java.util.Date;

public class Cliente {
    private Long CPF;
    private Long telefone;
    private String sexo;
    private Date dataDeNascimento;

    public Cliente(Long CPF, Long telefone, String sexo, Date dataDeNascimento) {
        this.CPF = CPF;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataDeNascimento = dataDeNascimento;
    }

    public Cliente(Long CPF) {
        this.CPF = CPF;
    }

    public Long getCPF() {
        return CPF;
    }

    public void setCPF(Long CPF) {
        this.CPF = CPF;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }
}
