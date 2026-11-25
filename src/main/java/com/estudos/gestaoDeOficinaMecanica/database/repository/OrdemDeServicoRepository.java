package com.estudos.gestaoDeOficinaMecanica.database.repository;

import com.estudos.gestaoDeOficinaMecanica.database.model.Mecanico;
import com.estudos.gestaoDeOficinaMecanica.database.model.OrdemdeServico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrdemDeServicoRepository extends JpaRepository<OrdemdeServico, UUID> {
}
