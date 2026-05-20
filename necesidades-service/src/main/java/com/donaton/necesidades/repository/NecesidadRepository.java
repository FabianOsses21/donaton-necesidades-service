package com.donaton.necesidades.repository;

import com.donaton.necesidades.enums.EstadoNecesidad;
import com.donaton.necesidades.model.Necesidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NecesidadRepository extends JpaRepository<Necesidad, Long> {

    List<Necesidad> findByEstado(EstadoNecesidad estado);

    List<Necesidad> findByUbicacionContainingIgnoreCase(String ubicacion);
}
