package com.estudos.gestaoDeOficinaMecanica.database.model;

import jakarta.persistence.*;

import java.util.UUID;

@Table
@Entity
public class Mecanico {

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "especialidade", nullable = false)
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    public Mecanico() {
    }

    public Mecanico(UUID id, String nome, Especialidade especialidade) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
}
