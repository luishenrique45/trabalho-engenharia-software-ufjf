package com.estudos.gestaoDeOficinaMecanica.service;

import com.estudos.gestaoDeOficinaMecanica.database.model.Especialidade;
import com.estudos.gestaoDeOficinaMecanica.database.model.Mecanico;
import com.estudos.gestaoDeOficinaMecanica.database.model.dto.MecanicoDto;
import com.estudos.gestaoDeOficinaMecanica.database.repository.MecanicoRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MecanicoServiceTest {

    @Mock
    private MecanicoRepository repository;

    @InjectMocks
    private MecanicoService mecanicoService;

    private Mecanico mecanico;
    private UUID mecanicoId;

    @BeforeEach
    void setUp() {
        mecanicoId = UUID.randomUUID();
        mecanico = new Mecanico();
        mecanico.setId(mecanicoId);
        mecanico.setNome("João");
        mecanico.setEspecialidade(Especialidade.Motor);
    }

    // ========== LISTAR ==========

    @Test
    @DisplayName("Deve listar todos os mecânicos com sucesso")
    void deveListarTodosMecanicos() {
        Mecanico mecanico2 = new Mecanico();
        mecanico2.setId(UUID.randomUUID());
        mecanico2.setNome("Carlos");
        mecanico2.setEspecialidade(Especialidade.Eletrica);

        when(repository.findAll()).thenReturn(List.of(mecanico, mecanico2));

        List<MecanicoDto> resultado = mecanicoService.listarTodosMecanicos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("João", resultado.get(0).nome());
        assertEquals("Carlos", resultado.get(1).nome());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não houver mecânicos")
    void deveRetornarListaVazia() {
        when(repository.findAll()).thenReturn(List.of());

        List<MecanicoDto> resultado = mecanicoService.listarTodosMecanicos();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(repository, times(1)).findAll();
    }

    // ========== BUSCAR POR ID ==========

    @Test
    @DisplayName("Deve buscar mecânico por ID com sucesso")
    void deveBuscarMecanicoPorId() {
        when(repository.findById(mecanicoId)).thenReturn(Optional.of(mecanico));

        MecanicoDto resultado = mecanicoService.buscarPorId(mecanicoId);

        assertNotNull(resultado);
        assertEquals(mecanicoId, resultado.id());
        assertEquals("João", resultado.nome());
        assertEquals(Especialidade.Motor, resultado.especialidade());
        verify(repository, times(1)).findById(mecanicoId);
    }

    @Test
    @DisplayName("Deve lançar RuntimeException ao buscar mecânico inexistente")
    void deveLancarExcecaoAoBuscarMecanicoInexistente() {
        UUID idInexistente = UUID.randomUUID();
        when(repository.findById(idInexistente)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> mecanicoService.buscarPorId(idInexistente));

        assertTrue(exception.getMessage().contains("Mecânico não encontrado com id:"));
        verify(repository, times(1)).findById(idInexistente);
    }

    // ========== SALVAR ==========

    @Test
    @DisplayName("Deve salvar mecânico com sucesso")
    void deveSalvarMecanico() {
        when(repository.save(mecanico)).thenReturn(mecanico);

        assertDoesNotThrow(() -> mecanicoService.salvar(mecanico));

        verify(repository, times(1)).save(mecanico);
    }

    // ========== EXCLUIR ==========

    @Test
    @DisplayName("Deve excluir mecânico com sucesso quando ele existir")
    void deveExcluirMecanicoComSucesso() {
        when(repository.existsById(mecanicoId)).thenReturn(true);
        doNothing().when(repository).deleteById(mecanicoId);

        assertDoesNotThrow(() -> mecanicoService.excluir(mecanicoId));

        verify(repository, times(1)).existsById(mecanicoId);
        verify(repository, times(1)).deleteById(mecanicoId);
    }

    @Test
    @DisplayName("Deve lançar RuntimeException ao excluir mecânico inexistente")
    void deveLancarExcecaoAoExcluirMecanicoInexistente() {
        UUID idInexistente = UUID.randomUUID();
        when(repository.existsById(idInexistente)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> mecanicoService.excluir(idInexistente));

        assertTrue(exception.getMessage().contains("Mecânico não encontrado com id:"));
        verify(repository, times(1)).existsById(idInexistente);
        verify(repository, never()).deleteById(idInexistente);
    }
}
