package com.estudos.gestaoDeOficinaMecanica.database.repository;

import com.estudos.gestaoDeOficinaMecanica.database.model.Peca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PecaRepository extends JpaRepository<Peca, UUID> {
}
