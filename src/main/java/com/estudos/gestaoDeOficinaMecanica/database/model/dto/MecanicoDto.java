package com.estudos.gestaoDeOficinaMecanica.database.model.dto;

import com.estudos.gestaoDeOficinaMecanica.database.model.Especialidade;
import com.estudos.gestaoDeOficinaMecanica.database.model.Mecanico;

import java.util.UUID;

public record MecanicoDto( UUID id,String nome, Especialidade especialidade) {

    public Mecanico maeparMecanico() {

        Mecanico mecanico = new Mecanico();
        mecanico.setId(id);
        mecanico.setNome(nome);
        mecanico.setEspecialidade(especialidade);
        return mecanico;
    }


    public static MecanicoDto fromEntity(Mecanico mecanico) {
        return new MecanicoDto(
                mecanico.getId(),
                mecanico.getNome(),
                mecanico.getEspecialidade()
        );
    }
}
