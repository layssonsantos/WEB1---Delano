package br.ufscar.dc.dsw.domain;

public class Profissional {
    private Long CPF;
    private String especialidade;

    public Profissional(Long CPF, String especialidade) {
        this.CPF = CPF;
        this.especialidade = especialidade;
    }

    public Long getCPF() {
        return CPF;
    }

    public void setCPF(Long CPF) {
        this.CPF = CPF;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
