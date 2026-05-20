package com.donaton.necesidades.mapper;

import com.donaton.necesidades.dto.request.NecesidadRequestDTO;
import com.donaton.necesidades.dto.response.NecesidadResponseDTO;
import com.donaton.necesidades.model.Necesidad;

public class NecesidadMapper {

    private NecesidadMapper() {
    }

    public static Necesidad toEntity(NecesidadRequestDTO dto) {
        return Necesidad.builder()
                .recursoNecesitado(dto.getRecursoNecesitado())
                .cantidad(dto.getCantidad())
                .ubicacion(dto.getUbicacion())
                .descripcion(dto.getDescripcion())
                .prioridad(dto.getPrioridad())
                .build();
    }

    public static NecesidadResponseDTO toResponseDTO(Necesidad necesidad) {
        return NecesidadResponseDTO.builder()
                .id(necesidad.getId())
                .recursoNecesitado(necesidad.getRecursoNecesitado())
                .cantidad(necesidad.getCantidad())
                .ubicacion(necesidad.getUbicacion())
                .descripcion(necesidad.getDescripcion())
                .prioridad(necesidad.getPrioridad())
                .estado(necesidad.getEstado())
                .fechaReporte(necesidad.getFechaReporte())
                .build();
    }
}
