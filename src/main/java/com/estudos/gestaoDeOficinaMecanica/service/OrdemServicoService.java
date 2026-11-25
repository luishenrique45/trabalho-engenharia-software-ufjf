package com.estudos.gestaoDeOficinaMecanica.service;

import com.estudos.gestaoDeOficinaMecanica.database.model.OrdemdeServico;
import com.estudos.gestaoDeOficinaMecanica.database.model.Peca;
import com.estudos.gestaoDeOficinaMecanica.database.repository.OrdemDeServicoRepository;
import com.estudos.gestaoDeOficinaMecanica.exception.EspecialidadeIncompativelException;
import com.estudos.gestaoDeOficinaMecanica.exception.PecaIndisponivelException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrdemServicoService {

    private final OrdemDeServicoRepository ordemRepository;

    public OrdemServicoService(OrdemDeServicoRepository ordemRepository) {
        this.ordemRepository = ordemRepository;
    }

    public List<OrdemdeServico> listarTodas() {
        return ordemRepository.findAll();
    }

    public OrdemdeServico buscarPorId(UUID id) {
        return ordemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada com id: " + id));
    }

    // Salvar / Abrir Ordem de Serviço
    public OrdemdeServico salvar(OrdemdeServico ordem) {
        // Regra 1: Especialidade do mecânico deve ser igual ao tipoServico da OS
        validarEspecialidade(ordem);

        // Se já estiver "Em Andamento", valida estoque
        if ("Em Andamento".equalsIgnoreCase(ordem.getStatus())) {
            validarEstoquePecas(ordem);
        }

        return ordemRepository.save(ordem);
    }

    // Alterar status para "Em Andamento"
    public void iniciarServico(UUID id) {
        OrdemdeServico ordem = buscarPorId(id);

        // Regra 2: Peças devem ter estoque > 0 para iniciar
        validarEstoquePecas(ordem);

        ordem.setStatus("Em Andamento");
        ordemRepository.save(ordem);
    }

    // Regra 1: Validação de Especialidade
    private void validarEspecialidade(OrdemdeServico ordem) {
        if (ordem.getMecanico() != null && ordem.getMecanico().getEspecialidade() != null) {
            if (!ordem.getMecanico().getEspecialidade().equals(ordem.getTipoServico())) {
                throw new EspecialidadeIncompativelException(
                        "O mecânico selecionado não é especialista neste tipo de reparo.");
            }
        }
    }

    // Regra 2: Controle de Fornecedores/Estoque
    private void validarEstoquePecas(OrdemdeServico ordem) {
        if (ordem.getPeca() != null && !ordem.getPeca().isEmpty()) {
            for (Peca peca : ordem.getPeca()) {
                if (peca.getQuantidadeEstoque() <= 0) {
                    throw new PecaIndisponivelException(
                            "Não é possível iniciar o serviço: peça fora de estoque no fornecedor.");
                }
            }
        }
    }
}
