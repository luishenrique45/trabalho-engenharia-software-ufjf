package com.estudos.gestaoDeOficinaMecanica.database.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Table
@Entity
public class OrdemdeServico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "descricao", length = 300, nullable = false)
    private String descricao;

    @Column(name = "especialidade", nullable = false)
    @Enumerated(EnumType.STRING)
    private Especialidade tipoServico;

    @ManyToOne
    @JoinColumn(name = "mecanico")
    private Mecanico mecanico;

    @OneToMany
    @JoinColumn(name = "peca")
    private List<Peca> peca;

    @Column(name = "status")
    private String status = "Aberta";

    public OrdemdeServico() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Especialidade getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(Especialidade tipoServico) {
        this.tipoServico = tipoServico;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    public List<Peca> getPeca() {
        return peca;
    }

    public void setPeca(List<Peca> peca) {
        this.peca = peca;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
