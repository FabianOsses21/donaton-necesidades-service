package com.donaton.necesidades.dto.response;

import com.donaton.necesidades.enums.EstadoNecesidad;
import com.donaton.necesidades.enums.PrioridadNecesidad;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NecesidadResponseDTO {

    private Long id;
    private String recursoNecesitado;
    private Integer cantidad;
    private String ubicacion;
    private String descripcion;
    private PrioridadNecesidad prioridad;
    private EstadoNecesidad estado;
    private LocalDateTime fechaReporte;
}
