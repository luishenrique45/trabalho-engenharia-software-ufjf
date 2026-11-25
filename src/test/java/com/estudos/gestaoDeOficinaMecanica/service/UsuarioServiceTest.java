package com.estudos.gestaoDeOficinaMecanica.service;

import com.estudos.gestaoDeOficinaMecanica.database.model.Mecanico;
import com.estudos.gestaoDeOficinaMecanica.database.model.Usuario;
import com.estudos.gestaoDeOficinaMecanica.database.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
    private UUID usuarioId;

    @BeforeEach
    void setUp() {
        usuarioId = UUID.randomUUID();
        usuario = new Usuario();
        usuario.setId(usuarioId);
        usuario.setLogin("joao.mecanico");
        usuario.setSenha("senha123");
        usuario.setPapel("MECANICO");
    }

    // ========== SPRING SECURITY (loadUserByUsername) ==========

    @Test
    @DisplayName("Deve carregar usuário por username com sucesso (Spring Security)")
    void deveCarregarUsuarioPorUsername() {
        when(usuarioRepository.findByLogin("joao.mecanico")).thenReturn(Optional.of(usuario));

        UserDetails userDetails = usuarioService.loadUserByUsername("joao.mecanico");

        assertNotNull(userDetails);
        assertEquals("joao.mecanico", userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_MECANICO")));
        verify(usuarioRepository, times(1)).findByLogin("joao.mecanico");
    }

    @Test
    @DisplayName("Deve lançar UsernameNotFoundException quando usuário não existir no login")
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        when(usuarioRepository.findByLogin("inexistente")).thenReturn(Optional.empty());

        UsernameNotFoundException ex = assertThrows(UsernameNotFoundException.class,
                () -> usuarioService.loadUserByUsername("inexistente"));

        assertTrue(ex.getMessage().contains("Usuário não encontrado"));
    }

    // ========== LISTAR TODOS ==========

    @Test
    @DisplayName("Deve listar todos os usuários com sucesso")
    void deveListarTodosUsuarios() {
        Usuario u2 = new Usuario();
        u2.setId(UUID.randomUUID());
        u2.setLogin("admin");
        u2.setPapel("ADMIN");

        when(usuarioRepository.findAll()).thenReturn(List.of(usuario, u2));

        List<Usuario> resultado = usuarioService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("joao.mecanico", resultado.get(0).getLogin());
        assertEquals("admin", resultado.get(1).getLogin());
        verify(usuarioRepository, times(1)).findAll();
    }

    // ========== BUSCAR POR ID ==========

    @Test
    @DisplayName("Deve buscar usuário por ID com sucesso")
    void deveBuscarUsuarioPorId() {
        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.buscarPorId(usuarioId);

        assertNotNull(resultado);
        assertEquals(usuarioId, resultado.getId());
        assertEquals("joao.mecanico", resultado.getLogin());
    }

    @Test
    @DisplayName("Deve lançar RuntimeException ao buscar usuário por ID inexistente")
    void deveLancarExcecaoAoBuscarIdInexistente() {
        UUID idInexistente = UUID.randomUUID();
        when(usuarioRepository.findById(idInexistente)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> usuarioService.buscarPorId(idInexistente));

        assertTrue(ex.getMessage().contains("Usuário não encontrado com id:"));
    }

    // ========== SALVAR ==========

    @Test
    @DisplayName("Deve salvar usuário novo com sucesso criptografando a senha")
    void deveSalvarUsuarioComSenhaCriptografada() {
        when(usuarioRepository.existsByLogin("joao.mecanico")).thenReturn(false);
        when(passwordEncoder.encode("senha123")).thenReturn("$2a$10$hashCriptografado");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        assertDoesNotThrow(() -> usuarioService.salvar(usuario));

        assertEquals("$2a$10$hashCriptografado", usuario.getSenha());
        verify(passwordEncoder, times(1)).encode("senha123");
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    @DisplayName("Deve lançar RuntimeException ao tentar salvar novo usuário com login já existente")
    void deveLancarExcecaoQuandoLoginDuplicado() {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setId(null); // Novo cadastro
        novoUsuario.setLogin("joao.mecanico");
        novoUsuario.setSenha("123456");

        Usuario existente = new Usuario();
        existente.setId(UUID.randomUUID());
        existente.setLogin("joao.mecanico");

        when(usuarioRepository.existsByLogin("joao.mecanico")).thenReturn(true);
        when(usuarioRepository.findByLogin("joao.mecanico")).thenReturn(Optional.of(existente));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> usuarioService.salvar(novoUsuario));

        assertEquals("Já existe um usuário com esse login.", ex.getMessage());
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve permitir salvar ao editar próprio usuário mantendo mesmo login")
    void devePermitirEditarMesmoLogin() {
        when(usuarioRepository.existsByLogin("joao.mecanico")).thenReturn(true);
        when(usuarioRepository.findByLogin("joao.mecanico")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.encode(anyString())).thenReturn("$2a$10$novaSenhaHash");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        assertDoesNotThrow(() -> usuarioService.salvar(usuario));

        verify(usuarioRepository, times(1)).save(usuario);
    }

    // ========== EXCLUIR ==========

    @Test
    @DisplayName("Deve excluir usuário existente com sucesso")
    void deveExcluirUsuarioComSucesso() {
        when(usuarioRepository.existsById(usuarioId)).thenReturn(true);
        doNothing().when(usuarioRepository).deleteById(usuarioId);

        assertDoesNotThrow(() -> usuarioService.excluir(usuarioId));

        verify(usuarioRepository, times(1)).existsById(usuarioId);
        verify(usuarioRepository, times(1)).deleteById(usuarioId);
    }

    @Test
    @DisplayName("Deve lançar RuntimeException ao tentar excluir usuário inexistente")
    void deveLancarExcecaoAoExcluirInexistente() {
        UUID idInexistente = UUID.randomUUID();
        when(usuarioRepository.existsById(idInexistente)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> usuarioService.excluir(idInexistente));

        assertTrue(ex.getMessage().contains("Usuário não encontrado com id:"));
        verify(usuarioRepository, never()).deleteById(idInexistente);
    }
}
