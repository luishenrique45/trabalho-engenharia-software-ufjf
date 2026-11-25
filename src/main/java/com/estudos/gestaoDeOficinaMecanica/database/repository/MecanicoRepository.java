package com.estudos.gestaoDeOficinaMecanica.database.repository;

import com.estudos.gestaoDeOficinaMecanica.database.model.Mecanico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MecanicoRepository extends JpaRepository<Mecanico,UUID> {
}
