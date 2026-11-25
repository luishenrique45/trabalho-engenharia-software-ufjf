package com.estudos.gestaoDeOficinaMecanica.controller;

import com.estudos.gestaoDeOficinaMecanica.database.model.Usuario;
import com.estudos.gestaoDeOficinaMecanica.service.MecanicoService;
import com.estudos.gestaoDeOficinaMecanica.service.UsuarioService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private MecanicoService mecanicoService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private UsuarioController usuarioController;

    private Usuario usuario;
    private UUID usuarioId;

    @BeforeEach
    void setUp() {
        usuarioId = UUID.randomUUID();
        usuario = new Usuario();
        usuario.setId(usuarioId);
        usuario.setLogin("carlos.mecanico");
        usuario.setSenha("123456");
        usuario.setPapel("MECANICO");
    }

    // ========== INDEX & LOGIN ==========

    @Test
    @DisplayName("Deve redirecionar a raiz (/) para /ordens")
    void deveRedirecionarRaizParaOrdens() {
        String view = usuarioController.index();
        assertEquals("redirect:/ordens", view);
    }

    @Test
    @DisplayName("Deve retornar a view de login")
    void deveRetornarViewLogin() {
        String view = usuarioController.login();
        assertEquals("login", view);
    }

    // ========== LISTAR USUÁRIOS ==========

    @Test
    @DisplayName("Deve listar usuários com sucesso")
    void deveListarUsuariosComSucesso() {
        when(usuarioService.listarTodos()).thenReturn(List.of(usuario));

        String view = usuarioController.listar(model);

        assertEquals("usuarios/lista", view);
        verify(model).addAttribute("usuarios", List.of(usuario));
    }

    @Test
    @DisplayName("Deve capturar exceção no try-catch ao listar usuários")
    void deveTratarExcecaoAoListarUsuarios() {
        when(usuarioService.listarTodos()).thenThrow(new RuntimeException("Falha na consulta"));

        String view = usuarioController.listar(model);

        assertEquals("usuarios/lista", view);
        verify(model).addAttribute(eq("mensagemErro"), any(String.class));
    }

    // ========== NOVO USUÁRIO ==========

    @Test
    @DisplayName("Deve abrir formulário de novo usuário com lista de mecânicos")
    void deveAbrirFormularioNovoUsuario() {
        when(mecanicoService.listarTodosMecanicos()).thenReturn(List.of());

        String view = usuarioController.novo(model);

        assertEquals("usuarios/formulario", view);
        verify(model).addAttribute(eq("usuario"), any(Usuario.class));
        verify(model).addAttribute("mecanicos", List.of());
    }

    // ========== SALVAR USUÁRIO ==========

    @Test
    @DisplayName("Deve salvar usuário com sucesso e redirecionar para a lista")
    void deveSalvarUsuarioComSucesso() {
        doNothing().when(usuarioService).salvar(usuario);

        String view = usuarioController.salvar(usuario, redirectAttributes);

        assertEquals("redirect:/usuarios", view);
        verify(usuarioService, times(1)).salvar(usuario);
        verify(redirectAttributes).addFlashAttribute(eq("mensagemSucesso"), any(String.class));
    }

    @Test
    @DisplayName("Deve capturar exceção no try-catch ao salvar usuário (ex: login duplicado) e redirecionar para o form")
    void deveTratarExcecaoAoSalvarUsuario() {
        doThrow(new RuntimeException("Já existe um usuário com esse login."))
                .when(usuarioService).salvar(usuario);

        String view = usuarioController.salvar(usuario, redirectAttributes);

        assertEquals("redirect:/usuarios/novo", view);
        verify(redirectAttributes).addFlashAttribute("mensagemErro", "Erro ao salvar usuário: Já existe um usuário com esse login.");
    }

    // ========== EXCLUIR USUÁRIO ==========

    @Test
    @DisplayName("Deve excluir usuário com sucesso e redirecionar para a lista")
    void deveExcluirUsuarioComSucesso() {
        doNothing().when(usuarioService).excluir(usuarioId);

        String view = usuarioController.excluir(usuarioId, redirectAttributes);

        assertEquals("redirect:/usuarios", view);
        verify(usuarioService, times(1)).excluir(usuarioId);
        verify(redirectAttributes).addFlashAttribute(eq("mensagemSucesso"), any(String.class));
    }

    @Test
    @DisplayName("Deve capturar exceção no try-catch ao excluir usuário e redirecionar com mensagem de erro")
    void deveTratarExcecaoAoExcluirUsuario() {
        doThrow(new RuntimeException("Usuário não encontrado"))
                .when(usuarioService).excluir(usuarioId);

        String view = usuarioController.excluir(usuarioId, redirectAttributes);

        assertEquals("redirect:/usuarios", view);
        verify(redirectAttributes).addFlashAttribute(eq("mensagemErro"), any(String.class));
    }
}
