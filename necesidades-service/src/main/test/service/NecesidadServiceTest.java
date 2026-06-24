package com.donaton.necesidades.service;

import com.donaton.necesidades.dto.request.NecesidadRequestDTO;
import com.donaton.necesidades.dto.response.NecesidadResponseDTO;
import com.donaton.necesidades.enums.EstadoNecesidad;
import com.donaton.necesidades.enums.PrioridadNecesidad;
import com.donaton.necesidades.model.Necesidad;
import com.donaton.necesidades.repository.NecesidadRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class NecesidadServiceTest {

    @Mock
    private NecesidadRepository necesidadRepository;

    @InjectMocks
    private NecesidadService necesidadService;

    @Test
    void deberiaGuardarNecesidadCorrectamente() {
        NecesidadRequestDTO request = new NecesidadRequestDTO();
        request.setRecursoNecesitado("Alimentos");
        request.setCantidad(10);
        request.setUbicacion("Santiago");
        request.setDescripcion("Se necesitan alimentos no perecibles");
        request.setPrioridad(PrioridadNecesidad.ALTA);

        Necesidad necesidadGuardada = new Necesidad();
        necesidadGuardada.setId(1L);
        necesidadGuardada.setRecursoNecesitado("Alimentos");
        necesidadGuardada.setCantidad(10);
        necesidadGuardada.setUbicacion("Santiago");
        necesidadGuardada.setDescripcion("Se necesitan alimentos no perecibles");
        necesidadGuardada.setPrioridad(PrioridadNecesidad.ALTA);
        necesidadGuardada.setEstado(EstadoNecesidad.PENDIENTE);

        Mockito.when(necesidadRepository.save(any(Necesidad.class)))
                .thenReturn(necesidadGuardada);

        NecesidadResponseDTO resultado = necesidadService.guardar(request);

        assertNotNull(resultado);
        assertEquals("Alimentos", resultado.getRecursoNecesitado());
        assertEquals(10, resultado.getCantidad());
        assertEquals("Santiago", resultado.getUbicacion());
        assertEquals(EstadoNecesidad.PENDIENTE, resultado.getEstado());
    }
}