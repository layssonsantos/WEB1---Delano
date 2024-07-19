package br.ufscar.dc.dsw.domain;

import java.time.LocalDateTime;

public class Consulta {

    private Long id;
    private LocalDateTime dataHora;
    private Long CPFCliente;
    private Long CPFProfissional;

    // Construtor padrão
    public Consulta() {
    }

    // Construtor com todos os campos
    public Consulta(Long id, LocalDateTime dataHora, Long CPFCliente, Long CPFProfissional) {
        this.id = id;
        this.dataHora = dataHora;
        this.CPFCliente = CPFCliente;
        this.CPFProfissional = CPFProfissional;
    }

    // Construtor sem o campo id
    public Consulta(LocalDateTime dataHora, Long CPFCliente, Long CPFProfissional) {
        this.dataHora = dataHora;
        this.CPFCliente = CPFCliente;
        this.CPFProfissional = CPFProfissional;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Long getCPFCliente() {
        return CPFCliente;
    }

    public void setCPFCliente(Long CPFCliente) {
        this.CPFCliente = CPFCliente;
    }

    public Long getCPFProfissional() {
        return CPFProfissional;
    }

    public void setCPFProfissional(Long CPFProfissional) {
        this.CPFProfissional = CPFProfissional;
    }
}
