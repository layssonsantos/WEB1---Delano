package br.ufscar.dc.dsw.domain;

import java.time.LocalDate;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private Long CPF;
    private String papel;
    private Long telefone;
    private String sexo;
    private LocalDate dataDeNascimento;
    private String especialidade;

    // Construtores
    public Usuario(Long CPF) {
        this.CPF = CPF;
    }

    public Usuario(String nome, String email, String senha, Long CPF, String papel) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.CPF = CPF;
        this.papel = papel;
    }

    public Usuario(String nome, String email, String senha, Long CPF, String papel, Long telefone, String sexo, LocalDate dataDeNascimento) {
        this(nome, email, senha, CPF, papel);
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataDeNascimento = dataDeNascimento;
    }

    public Usuario(String nome, String email, String senha, Long CPF, String papel, String especialidade) {
        this(nome, email, senha, CPF, papel);
        this.especialidade = especialidade;
    }

    public Usuario(String nome, String email, String senha, Long CPF, String papel, Long telefone, String sexo, LocalDate dataDeNascimento, String especialidade) {
        this(nome, email, senha, CPF, papel);
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataDeNascimento = dataDeNascimento;
        this.especialidade = especialidade;
    }

    // Getters e setters
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getCPF() {
        return CPF;
    }

    public void setCPF(Long CPF) {
        this.CPF = CPF;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
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

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
