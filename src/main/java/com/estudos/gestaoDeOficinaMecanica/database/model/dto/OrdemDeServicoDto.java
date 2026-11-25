package com.estudos.gestaoDeOficinaMecanica.database.model.dto;

import com.estudos.gestaoDeOficinaMecanica.database.model.Especialidade;
import com.estudos.gestaoDeOficinaMecanica.database.model.Mecanico;
import com.estudos.gestaoDeOficinaMecanica.database.model.OrdemdeServico;
import com.estudos.gestaoDeOficinaMecanica.database.model.Peca;

import java.util.List;
import java.util.UUID;

public record OrdemDeServicoDto(UUID id, String descricao, Especialidade tipoDeServico, Mecanico mecanico,
                                List<Peca> pecas) {

    public OrdemdeServico MapearOrdem(){
        OrdemdeServico ordem = new OrdemdeServico();
        ordem.setDescricao(descricao);
        ordem.setMecanico(mecanico);
        ordem.setTipoServico(tipoDeServico);
        ordem.setPeca(pecas);
       return ordem;
    }

}
