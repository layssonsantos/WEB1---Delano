package br.ufscar.dc.dsw.domain;

import java.util.Date;
import java.util.List;

import br.ufscar.dc.dsw.validation.UniqueCPF;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "clientes")
public class Cliente extends AbstractEntity<Long> {

    @NotBlank(message = "{NotBlank.aluno.email}")
    @Email(message = "{Email.aluno.email}")
    @Size(max = 60)
    @Column(nullable = false, unique = true, length = 60)
    private String email;

    @UniqueCPF (message = "{Unique.aluno.CPF}")
    @NotBlank(message = "{NotBlank.aluno.cpf}")
    @Size(min = 14, max = 14, message = "{Size.aluno.cpf}")
    @Column(nullable = false, unique = true, length = 14)
    private String CPF;

    @NotBlank(message = "{NotBlank.aluno.nome}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String nome;

    @NotBlank
    @Size(min = 15, max = 15, message = "{Size.aluno.telefone}")
    @Column(nullable = false, length = 60)
    private String telefone;

    @NotBlank(message = "{NotBlank.aluno.sexo}")
    @Size(max = 10)
    @Column(nullable = false, length = 10)
    private String sexo;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @OneToMany(mappedBy = "cliente")
    private List<Consulta> consultas;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cPF) {
        CPF = cPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
}
