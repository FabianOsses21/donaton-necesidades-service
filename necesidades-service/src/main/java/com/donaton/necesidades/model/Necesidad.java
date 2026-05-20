package com.donaton.necesidades.model;

import com.donaton.necesidades.enums.EstadoNecesidad;
import com.donaton.necesidades.enums.PrioridadNecesidad;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "necesidades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Necesidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recursoNecesitado;

    private Integer cantidad;

    private String ubicacion;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    private PrioridadNecesidad prioridad;

    @Enumerated(EnumType.STRING)
    private EstadoNecesidad estado;

    private LocalDateTime fechaReporte;
}
