package com.estudos.gestaoDeOficinaMecanica.database.model.dto;

import com.estudos.gestaoDeOficinaMecanica.database.model.Peca;

import java.util.UUID;

public record Pecadto(
        UUID id ,
        String nome,
        int quantidade,
        double preco,
        String fornecedor

) {

    public Peca mapearPeca() {
        Peca peca = new Peca();
        peca.setNome(nome);
        peca.setQuantidadeEstoque(quantidade);
        peca.setPreco(preco);
        peca.setFornecedor(fornecedor);
        return peca;
    }
}
