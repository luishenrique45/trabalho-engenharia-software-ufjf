package com.estudos.gestaoDeOficinaMecanica.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @Mock
    private RedirectAttributes redirectAttributes;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    @DisplayName("Deve redirecionar para /ordens/nova ao capturar EspecialidadeIncompativelException")
    void deveRedirecionarParaFormularioAoCapturarEspecialidadeIncompativel() {
        EspecialidadeIncompativelException ex = new EspecialidadeIncompativelException(
                "O mecânico selecionado não é especialista neste tipo de reparo.");

        String resultado = handler.handleEspecialidadeIncompativel(ex, redirectAttributes);

        assertEquals("redirect:/ordens/nova", resultado);
        verify(redirectAttributes).addFlashAttribute("mensagemErro",
                "O mecânico selecionado não é especialista neste tipo de reparo.");
    }

    @Test
    @DisplayName("Deve redirecionar para /ordens ao capturar PecaIndisponivelException")
    void deveRedirecionarParaDashboardAoCapturarPecaIndisponivel() {
        PecaIndisponivelException ex = new PecaIndisponivelException(
                "Não é possível iniciar o serviço: peça fora de estoque no fornecedor.");

        String resultado = handler.handlePecaIndisponivel(ex, redirectAttributes);

        assertEquals("redirect:/ordens", resultado);
        verify(redirectAttributes).addFlashAttribute("mensagemErro",
                "Não é possível iniciar o serviço: peça fora de estoque no fornecedor.");
    }
}
