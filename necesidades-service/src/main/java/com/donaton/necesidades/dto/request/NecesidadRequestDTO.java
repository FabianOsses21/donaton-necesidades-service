package com.donaton.necesidades.dto.request;

import com.donaton.necesidades.enums.PrioridadNecesidad;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NecesidadRequestDTO {

    @NotBlank(message = "El recurso necesitado es obligatorio")
    private String recursoNecesitado;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a cero")
    private Integer cantidad;

    @NotBlank(message = "La ubicacion es obligatoria")
    private String ubicacion;

    private String descripcion;

    @NotNull(message = "La prioridad es obligatoria")
    private PrioridadNecesidad prioridad;
}
