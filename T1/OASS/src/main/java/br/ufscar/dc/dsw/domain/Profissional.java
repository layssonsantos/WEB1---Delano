package br.ufscar.dc.dsw.domain;

public class Profissional {
    private String nome;
    private String email;
    private String senha;
    private long CPF;
    private String especialidade;

    public Profissional(String nome, String email, String senha, long CPF, String especialidade) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.CPF = CPF;
        this.especialidade = especialidade;
    }

    public Profissional(long CPF) {
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
    
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
}
