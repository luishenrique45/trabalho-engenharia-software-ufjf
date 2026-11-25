package com.estudos.gestaoDeOficinaMecanica.service;

import com.estudos.gestaoDeOficinaMecanica.database.model.Mecanico;
import com.estudos.gestaoDeOficinaMecanica.database.model.dto.MecanicoDto;
import com.estudos.gestaoDeOficinaMecanica.database.repository.MecanicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MecanicoService {
    MecanicoRepository repository;

    public MecanicoService(MecanicoRepository repository) {
        this.repository = repository;
    }

    public List<MecanicoDto> listarTodosMecanicos(){
        return repository.findAll().stream().map(MecanicoDto::fromEntity).toList();
    }

    public MecanicoDto buscarPorId(UUID id){
        Mecanico mecanico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mecânico não encontrado com id: " + id));
        return MecanicoDto.fromEntity(mecanico);
    }

    public void salvar(Mecanico mecanico){
        repository.save(mecanico);
    }

    public void excluir(UUID id){
        if (!repository.existsById(id)) {
            throw new RuntimeException("Mecânico não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
