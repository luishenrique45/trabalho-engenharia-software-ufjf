package com.estudos.gestaoDeOficinaMecanica.service;

import com.estudos.gestaoDeOficinaMecanica.database.model.Especialidade;
import com.estudos.gestaoDeOficinaMecanica.database.model.Mecanico;
import com.estudos.gestaoDeOficinaMecanica.database.model.OrdemdeServico;
import com.estudos.gestaoDeOficinaMecanica.database.model.Peca;
import com.estudos.gestaoDeOficinaMecanica.database.repository.OrdemDeServicoRepository;
import com.estudos.gestaoDeOficinaMecanica.exception.EspecialidadeIncompativelException;
import com.estudos.gestaoDeOficinaMecanica.exception.PecaIndisponivelException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrdemServicoServiceTest {

    @Mock
    private OrdemDeServicoRepository ordemRepository;

    @InjectMocks
    private OrdemServicoService ordemServicoService;

    private Mecanico mecanicoMotor;
    private Mecanico mecanicoSuspensao;
    private Peca pecaComEstoque;
    private Peca pecaSemEstoque;

    @BeforeEach
    void setUp() {
        mecanicoMotor = new Mecanico();
        mecanicoMotor.setId(UUID.randomUUID());
        mecanicoMotor.setNome("João");
        mecanicoMotor.setEspecialidade(Especialidade.Motor);

        mecanicoSuspensao = new Mecanico();
        mecanicoSuspensao.setId(UUID.randomUUID());
        mecanicoSuspensao.setNome("Carlos");
        mecanicoSuspensao.setEspecialidade(Especialidade.Supensâo);

        pecaComEstoque = new Peca();
        pecaComEstoque.setId(UUID.randomUUID());
        pecaComEstoque.setNome("Filtro de óleo");
        pecaComEstoque.setQuantidadeEstoque(10);
        pecaComEstoque.setPreco(45.0);
        pecaComEstoque.setFornecedor("AutoPeças Silva");

        pecaSemEstoque = new Peca();
        pecaSemEstoque.setId(UUID.randomUUID());
        pecaSemEstoque.setNome("Correia dentada");
        pecaSemEstoque.setQuantidadeEstoque(0);
        pecaSemEstoque.setPreco(120.0);
        pecaSemEstoque.setFornecedor("AutoPeças Silva");
    }

    // ========== LISTAR ==========

    @Test
    @DisplayName("Deve listar todas as ordens de serviço")
    void deveListarTodasOrdens() {
        OrdemdeServico os1 = new OrdemdeServico();
        os1.setDescricao("Troca de óleo");
        OrdemdeServico os2 = new OrdemdeServico();
        os2.setDescricao("Alinhamento");

        when(ordemRepository.findAll()).thenReturn(List.of(os1, os2));

        List<OrdemdeServico> resultado = ordemServicoService.listarTodas();

        assertEquals(2, resultado.size());
        verify(ordemRepository, times(1)).findAll();
    }

    // ========== BUSCAR POR ID ==========

    @Test
    @DisplayName("Deve buscar OS por ID com sucesso")
    void deveBuscarOrdemPorId() {
        UUID ordemId = UUID.randomUUID();
        OrdemdeServico ordem = new OrdemdeServico();
        ordem.setId(ordemId);
        ordem.setDescricao("Troca de óleo");

        when(ordemRepository.findById(ordemId)).thenReturn(Optional.of(ordem));

        OrdemdeServico resultado = ordemServicoService.buscarPorId(ordemId);

        assertNotNull(resultado);
        assertEquals(ordemId, resultado.getId());
        verify(ordemRepository, times(1)).findById(ordemId);
    }

    @Test
    @DisplayName("Deve lançar RuntimeException ao buscar OS inexistente")
    void deveLancarExcecaoAoBuscarOrdemInexistente() {
        UUID idInexistente = UUID.randomUUID();
        when(ordemRepository.findById(idInexistente)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> ordemServicoService.buscarPorId(idInexistente));

        assertTrue(exception.getMessage().contains("Ordem de Serviço não encontrada"));
    }

    // ========== REGRA 1: VALIDAÇÃO DE ESPECIALIDADE ==========

    @Test
    @DisplayName("Deve salvar OS quando especialidade do mecânico é compatível")
    void deveSalvarOrdemComEspecialidadeCompativel() {
        OrdemdeServico ordem = new OrdemdeServico();
        ordem.setDescricao("Reparo no motor");
        ordem.setTipoServico(Especialidade.Motor);
        ordem.setMecanico(mecanicoMotor); // Mecânico especialista em Motor

        when(ordemRepository.save(any(OrdemdeServico.class))).thenReturn(ordem);

        OrdemdeServico resultado = ordemServicoService.salvar(ordem);

        assertNotNull(resultado);
        assertEquals("Reparo no motor", resultado.getDescricao());
        verify(ordemRepository, times(1)).save(ordem);
    }

    @Test
    @DisplayName("Deve lançar EspecialidadeIncompativelException quando mecânico tem especialidade diferente da OS")
    void deveLancarExcecaoQuandoEspecialidadeIncompativel() {
        OrdemdeServico ordem = new OrdemdeServico();
        ordem.setDescricao("Reparo no motor");
        ordem.setTipoServico(Especialidade.Motor);
        ordem.setMecanico(mecanicoSuspensao); // Mecânico de Suspensão tentando reparar Motor

        EspecialidadeIncompativelException exception = assertThrows(
                EspecialidadeIncompativelException.class,
                () -> ordemServicoService.salvar(ordem));

        assertEquals("O mecânico selecionado não é especialista neste tipo de reparo.", exception.getMessage());
        verify(ordemRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve salvar OS sem mecânico atribuído (mecânico nulo)")
    void deveSalvarOrdemSemMecanico() {
        OrdemdeServico ordem = new OrdemdeServico();
        ordem.setDescricao("Troca de pneu");
        ordem.setTipoServico(Especialidade.Supensâo);
        ordem.setMecanico(null);

        when(ordemRepository.save(any(OrdemdeServico.class))).thenReturn(ordem);

        OrdemdeServico resultado = ordemServicoService.salvar(ordem);

        assertNotNull(resultado);
        verify(ordemRepository, times(1)).save(ordem);
    }

    // ========== REGRA 2: CONTROLE DE ESTOQUE ==========

    @Test
    @DisplayName("Deve iniciar serviço quando todas as peças têm estoque")
    void deveIniciarServicoComPecasEmEstoque() {
        UUID ordemId = UUID.randomUUID();
        OrdemdeServico ordem = new OrdemdeServico();
        ordem.setId(ordemId);
        ordem.setDescricao("Troca de óleo");
        ordem.setTipoServico(Especialidade.Motor);
        ordem.setMecanico(mecanicoMotor);
        ordem.setPeca(List.of(pecaComEstoque));
        ordem.setStatus("Aberta");

        when(ordemRepository.findById(ordemId)).thenReturn(Optional.of(ordem));
        when(ordemRepository.save(any(OrdemdeServico.class))).thenReturn(ordem);

        assertDoesNotThrow(() -> ordemServicoService.iniciarServico(ordemId));

        assertEquals("Em Andamento", ordem.getStatus());
        verify(ordemRepository, times(1)).save(ordem);
    }

    @Test
    @DisplayName("Deve lançar PecaIndisponivelException ao iniciar serviço com peça sem estoque")
    void deveLancarExcecaoQuandoPecaSemEstoque() {
        UUID ordemId = UUID.randomUUID();
        OrdemdeServico ordem = new OrdemdeServico();
        ordem.setId(ordemId);
        ordem.setDescricao("Troca de correia");
        ordem.setTipoServico(Especialidade.Motor);
        ordem.setMecanico(mecanicoMotor);
        ordem.setPeca(List.of(pecaSemEstoque)); // Peça com estoque = 0
        ordem.setStatus("Aberta");

        when(ordemRepository.findById(ordemId)).thenReturn(Optional.of(ordem));

        PecaIndisponivelException exception = assertThrows(
                PecaIndisponivelException.class,
                () -> ordemServicoService.iniciarServico(ordemId));

        assertEquals("Não é possível iniciar o serviço: peça fora de estoque no fornecedor.", exception.getMessage());
        verify(ordemRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar PecaIndisponivelException quando pelo menos uma peça está sem estoque")
    void deveLancarExcecaoQuandoUmaPecaSemEstoque() {
        UUID ordemId = UUID.randomUUID();
        OrdemdeServico ordem = new OrdemdeServico();
        ordem.setId(ordemId);
        ordem.setDescricao("Manutenção completa");
        ordem.setTipoServico(Especialidade.Motor);
        ordem.setMecanico(mecanicoMotor);
        ordem.setPeca(List.of(pecaComEstoque, pecaSemEstoque)); // Uma com estoque, outra sem
        ordem.setStatus("Aberta");

        when(ordemRepository.findById(ordemId)).thenReturn(Optional.of(ordem));

        assertThrows(PecaIndisponivelException.class,
                () -> ordemServicoService.iniciarServico(ordemId));

        verify(ordemRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve iniciar serviço quando OS não tem peças vinculadas")
    void deveIniciarServicoSemPecas() {
        UUID ordemId = UUID.randomUUID();
        OrdemdeServico ordem = new OrdemdeServico();
        ordem.setId(ordemId);
        ordem.setDescricao("Diagnóstico");
        ordem.setTipoServico(Especialidade.Eletrica);
        Mecanico mecanicoEletrica = new Mecanico();
        mecanicoEletrica.setEspecialidade(Especialidade.Eletrica);
        ordem.setMecanico(mecanicoEletrica);
        ordem.setPeca(List.of()); // Lista vazia
        ordem.setStatus("Aberta");

        when(ordemRepository.findById(ordemId)).thenReturn(Optional.of(ordem));
        when(ordemRepository.save(any(OrdemdeServico.class))).thenReturn(ordem);

        assertDoesNotThrow(() -> ordemServicoService.iniciarServico(ordemId));

        assertEquals("Em Andamento", ordem.getStatus());
        verify(ordemRepository, times(1)).save(ordem);
    }

    // ========== COMBINAÇÃO DAS DUAS REGRAS ==========

    @Test
    @DisplayName("Deve lançar EspecialidadeIncompativelException mesmo com peças em estoque, se a especialidade for incompatível")
    void deveValidarEspecialidadeAntesDePecas() {
        OrdemdeServico ordem = new OrdemdeServico();
        ordem.setDescricao("Reparo elétrico");
        ordem.setTipoServico(Especialidade.Eletrica);
        ordem.setMecanico(mecanicoMotor); // Motor != Eletrica
        ordem.setPeca(List.of(pecaComEstoque));
        ordem.setStatus("Em Andamento");

        assertThrows(EspecialidadeIncompativelException.class,
                () -> ordemServicoService.salvar(ordem));

        verify(ordemRepository, never()).save(any());
    }
}
