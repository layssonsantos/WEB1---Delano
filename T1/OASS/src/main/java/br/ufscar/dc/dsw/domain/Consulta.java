package br.ufscar.dc.dsw.domain;

import java.sql.Date;
import java.sql.Time;

public class Consulta {
    private Long id;
    private Date data;
    private Time hora;
    private Long CPFCliente;
    private Long CPFProfissional;

    public Consulta(Long id, Date data, Time hora, Long CPFCliente, Long CPFProfissional) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.CPFCliente = CPFCliente;
        this.CPFProfissional = CPFProfissional;
    }

    public Consulta(Date data, Time hora, Long CPFCliente, Long CPFProfissional) {
        this.data = data;
        this.hora = hora;
        this.CPFCliente = CPFCliente;
        this.CPFProfissional = CPFProfissional;
    }

    public Consulta(Long id) {
        this.id = id;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }
    
    public Time getHora() { return hora; }
    public void setHora(Time hora) { this.hora = hora; }
    
    public Long getCPFCliente() { return CPFCliente; }
    public void setCPFCliente(Long CPFCliente) { this.CPFCliente = CPFCliente; }
    
    public Long getCPFProfissional() { return CPFProfissional; }
    public void setCPFProfissional(Long CPFProfissional) { this.CPFProfissional = CPFProfissional; }
}
