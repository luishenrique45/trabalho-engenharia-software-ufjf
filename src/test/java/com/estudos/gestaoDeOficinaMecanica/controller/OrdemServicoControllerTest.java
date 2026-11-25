package com.estudos.gestaoDeOficinaMecanica.controller;

import com.estudos.gestaoDeOficinaMecanica.database.model.Especialidade;
import com.estudos.gestaoDeOficinaMecanica.database.model.Mecanico;
import com.estudos.gestaoDeOficinaMecanica.database.model.OrdemdeServico;
import com.estudos.gestaoDeOficinaMecanica.database.model.Peca;
import com.estudos.gestaoDeOficinaMecanica.database.repository.PecaRepository;
import com.estudos.gestaoDeOficinaMecanica.service.MecanicoService;
import com.estudos.gestaoDeOficinaMecanica.service.OrdemServicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrdemServicoControllerTest {

    @Mock
    private OrdemServicoService ordemService;

    @Mock
    private MecanicoService mecanicoService;

    @Mock
    private PecaRepository pecaRepository;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private OrdemServicoController ordemServicoController;

    // ========== DASHBOARD ==========

    @Test
    @DisplayName("Deve exibir dashboard com todas as ordens")
    void deveExibirDashboard() {
        OrdemdeServico os = new OrdemdeServico();
        os.setDescricao("Troca de óleo");
        when(ordemService.listarTodas()).thenReturn(List.of(os));

        String view = ordemServicoController.dashboard(model);

        assertEquals("ordens/dashboard", view);
        verify(model).addAttribute("ordens", List.of(os));
    }

    @Test
    @DisplayName("Deve tratar exceção ao carregar dashboard (try-catch)")
    void deveTratarExcecaoNoDashboard() {
        when(ordemService.listarTodas()).thenThrow(new RuntimeException("Erro de conexão"));

        String view = ordemServicoController.dashboard(model);

        assertEquals("ordens/dashboard", view);
        verify(model).addAttribute(eq("mensagemErro"), any(String.class));
    }

    // ========== FORMULÁRIO NOVA OS ==========

    @Test
    @DisplayName("Deve abrir formulário de nova OS com mecânicos e peças disponíveis")
    void deveAbrirFormularioNovaOrdem() {
        when(mecanicoService.listarTodosMecanicos()).thenReturn(List.of());
        when(pecaRepository.findAll()).thenReturn(List.of());

        String view = ordemServicoController.formularioNovaOrdem(model);

        assertEquals("ordens/formulario", view);
        verify(model).addAttribute(eq("ordem"), any(OrdemdeServico.class));
        verify(model).addAttribute("mecanicos", List.of());
        verify(model).addAttribute("pecas", List.of());
    }

    // ========== SALVAR OS ==========

    @Test
    @DisplayName("Deve salvar OS com sucesso e redirecionar para dashboard")
    void deveSalvarOrdemComSucesso() {
        OrdemdeServico ordem = new OrdemdeServico();
        ordem.setDescricao("Troca de óleo");
        Mecanico mec = new Mecanico();
        mec.setEspecialidade(Especialidade.Motor);
        ordem.setMecanico(mec);
        ordem.setTipoServico(Especialidade.Motor);

        when(ordemService.salvar(ordem)).thenReturn(ordem);

        String view = ordemServicoController.salvar(ordem, redirectAttributes);

        assertEquals("redirect:/ordens", view);
        verify(redirectAttributes).addFlashAttribute(eq("mensagemSucesso"), any(String.class));
    }

    @Test
    @DisplayName("Deve tratar EspecialidadeIncompativelException ao salvar OS (try-catch)")
    void deveTratarExcecaoDeEspecialidadeAoSalvar() {
        OrdemdeServico ordem = new OrdemdeServico();
        when(ordemService.salvar(ordem)).thenThrow(
                new com.estudos.gestaoDeOficinaMecanica.exception.EspecialidadeIncompativelException(
                        "O mecânico selecionado não é especialista neste tipo de reparo."));

        String view = ordemServicoController.salvar(ordem, redirectAttributes);

        assertEquals("redirect:/ordens/nova", view);
        verify(redirectAttributes).addFlashAttribute("mensagemErro",
                "O mecânico selecionado não é especialista neste tipo de reparo.");
    }

    // ========== INICIAR SERVIÇO ==========

    @Test
    @DisplayName("Deve iniciar serviço com sucesso")
    void deveIniciarServicoComSucesso() {
        UUID ordemId = UUID.randomUUID();
        doNothing().when(ordemService).iniciarServico(ordemId);

        String view = ordemServicoController.iniciarServico(ordemId, redirectAttributes);

        assertEquals("redirect:/ordens", view);
        verify(redirectAttributes).addFlashAttribute(eq("mensagemSucesso"), any(String.class));
    }

    @Test
    @DisplayName("Deve tratar PecaIndisponivelException ao iniciar serviço (try-catch)")
    void deveTratarExcecaoDePecaAoIniciar() {
        UUID ordemId = UUID.randomUUID();
        doThrow(new com.estudos.gestaoDeOficinaMecanica.exception.PecaIndisponivelException(
                "Não é possível iniciar o serviço: peça fora de estoque no fornecedor."))
                .when(ordemService).iniciarServico(ordemId);

        String view = ordemServicoController.iniciarServico(ordemId, redirectAttributes);

        assertEquals("redirect:/ordens", view);
        verify(redirectAttributes).addFlashAttribute("mensagemErro",
                "Não é possível iniciar o serviço: peça fora de estoque no fornecedor.");
    }
}
