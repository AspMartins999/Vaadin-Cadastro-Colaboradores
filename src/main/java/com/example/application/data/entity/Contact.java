package com.example.application.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Contact extends AbstractEntity {

    @NotEmpty
    private String nome = "";

    @NotEmpty
    private String sobrenome = "";

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonIgnoreProperties({"employees"})
    private Departamento departamento;

    @NotNull
    @ManyToOne
    private Status status;

    @Email
    @NotEmpty
    private String email = "";
    
    private String cpf = "";

    @Override
    public String toString() {
        return nome + " " + sobrenome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String firstName) {
        this.nome = firstName;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String lastName) {
        this.sobrenome = lastName;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
