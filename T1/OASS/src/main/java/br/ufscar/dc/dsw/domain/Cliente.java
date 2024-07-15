package br.ufscar.dc.dsw.domain;

public class Cliente {
    private String nome;
    private String email;
    private String senha;
    private long CPF;
    private long telefone;
    private String sexo;
    private java.sql.Date dataDeNascimento;

    public Cliente(String nome, String email, String senha, long CPF, long telefone, String sexo, java.sql.Date dataDeNascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.CPF = CPF;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataDeNascimento = dataDeNascimento;
    }

    public Cliente(long CPF) {
        this.CPF = CPF;
    }

    // Getters and Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    
    public long getCPF() { return CPF; }
    public void setCPF(long CPF) { this.CPF = CPF; }
    
    public long getTelefone() { return telefone; }
    public void setTelefone(long telefone) { this.telefone = telefone; }
    
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    
    public java.sql.Date getDataDeNascimento() { return dataDeNascimento; }
    public void setDataDeNascimento(java.sql.Date dataDeNascimento) { this.dataDeNascimento = dataDeNascimento; }
}
