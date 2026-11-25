package com.estudos.gestaoDeOficinaMecanica.controller;

import com.estudos.gestaoDeOficinaMecanica.database.model.Especialidade;
import com.estudos.gestaoDeOficinaMecanica.database.model.Mecanico;
import com.estudos.gestaoDeOficinaMecanica.database.model.dto.MecanicoDto;
import com.estudos.gestaoDeOficinaMecanica.service.MecanicoService;
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
class MecanicoControllerTest {

    @Mock
    private MecanicoService mecanicoService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private MecanicoController mecanicoController;

    private UUID mecanicoId;
    private MecanicoDto mecanicoDto;

    @BeforeEach
    void setUp() {
        mecanicoId = UUID.randomUUID();
        mecanicoDto = new MecanicoDto(mecanicoId, "João", Especialidade.Motor);
    }

    // ========== LISTAR ==========

    @Test
    @DisplayName("Deve retornar a view de lista com mecânicos")
    void deveListarMecanicosComSucesso() {
        List<MecanicoDto> lista = List.of(mecanicoDto);
        when(mecanicoService.listarTodosMecanicos()).thenReturn(lista);

        String view = mecanicoController.listar(model);

        assertEquals("mecanicos/lista", view);
        verify(model).addAttribute("mecanicos", lista);
    }

    @Test
    @DisplayName("Deve tratar exceção ao listar mecânicos (try-catch)")
    void deveTratarExcecaoAoListar() {
        when(mecanicoService.listarTodosMecanicos()).thenThrow(new RuntimeException("Erro de banco"));

        String view = mecanicoController.listar(model);

        assertEquals("mecanicos/lista", view);
        verify(model).addAttribute(eq("mensagemErro"), any(String.class));
    }

    // ========== NOVO ==========

    @Test
    @DisplayName("Deve abrir formulário de novo mecânico")
    void deveAbrirFormularioNovo() {
        String view = mecanicoController.novo(model);

        assertEquals("mecanicos/formulario", view);
        verify(model).addAttribute(eq("mecanico"), any(Mecanico.class));
    }

    // ========== SALVAR ==========

    @Test
    @DisplayName("Deve salvar mecânico e redirecionar para lista com sucesso")
    void deveSalvarMecanicoComSucesso() {
        Mecanico mecanico = new Mecanico();
        mecanico.setNome("João");
        mecanico.setEspecialidade(Especialidade.Motor);

        doNothing().when(mecanicoService).salvar(mecanico);

        String view = mecanicoController.salvar(mecanico, redirectAttributes);

        assertEquals("redirect:/mecanicos", view);
        verify(mecanicoService, times(1)).salvar(mecanico);
        verify(redirectAttributes).addFlashAttribute(eq("mensagemSucesso"), any(String.class));
    }

    @Test
    @DisplayName("Deve tratar exceção ao salvar e redirecionar para formulário (try-catch)")
    void deveTratarExcecaoAoSalvar() {
        Mecanico mecanico = new Mecanico();
        doThrow(new RuntimeException("Erro ao salvar")).when(mecanicoService).salvar(mecanico);

        String view = mecanicoController.salvar(mecanico, redirectAttributes);

        assertEquals("redirect:/mecanicos/novo", view);
        verify(redirectAttributes).addFlashAttribute(eq("mensagemErro"), any(String.class));
    }

    // ========== EDITAR ==========

    @Test
    @DisplayName("Deve abrir formulário de edição com dados do mecânico")
    void deveAbrirFormularioEdicao() {
        when(mecanicoService.buscarPorId(mecanicoId)).thenReturn(mecanicoDto);

        String view = mecanicoController.editar(mecanicoId, model, redirectAttributes);

        assertEquals("mecanicos/formulario", view);
        verify(model).addAttribute(eq("mecanico"), any(Mecanico.class));
    }

    @Test
    @DisplayName("Deve tratar exceção ao editar mecânico inexistente (try-catch)")
    void deveTratarExcecaoAoEditarInexistente() {
        when(mecanicoService.buscarPorId(mecanicoId))
                .thenThrow(new RuntimeException("Mecânico não encontrado"));

        String view = mecanicoController.editar(mecanicoId, model, redirectAttributes);

        assertEquals("redirect:/mecanicos", view);
        verify(redirectAttributes).addFlashAttribute(eq("mensagemErro"), any(String.class));
    }

    // ========== EXCLUIR ==========

    @Test
    @DisplayName("Deve excluir mecânico e redirecionar com mensagem de sucesso")
    void deveExcluirMecanicoComSucesso() {
        doNothing().when(mecanicoService).excluir(mecanicoId);

        String view = mecanicoController.excluir(mecanicoId, redirectAttributes);

        assertEquals("redirect:/mecanicos", view);
        verify(mecanicoService, times(1)).excluir(mecanicoId);
        verify(redirectAttributes).addFlashAttribute(eq("mensagemSucesso"), any(String.class));
    }

    @Test
    @DisplayName("Deve tratar exceção ao excluir mecânico inexistente (try-catch)")
    void deveTratarExcecaoAoExcluir() {
        doThrow(new RuntimeException("Mecânico não encontrado"))
                .when(mecanicoService).excluir(mecanicoId);

        String view = mecanicoController.excluir(mecanicoId, redirectAttributes);

        assertEquals("redirect:/mecanicos", view);
        verify(redirectAttributes).addFlashAttribute(eq("mensagemErro"), any(String.class));
    }
}
