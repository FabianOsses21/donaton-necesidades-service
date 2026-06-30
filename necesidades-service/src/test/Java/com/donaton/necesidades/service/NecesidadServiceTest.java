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

import java.util.List;
import java.util.Optional;

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
        NecesidadRequestDTO request = crearRequest();

        Necesidad necesidadGuardada = crearNecesidad();

        Mockito.when(necesidadRepository.save(any(Necesidad.class)))
                .thenReturn(necesidadGuardada);

        NecesidadResponseDTO resultado = necesidadService.guardar(request);

        assertNotNull(resultado);
        assertEquals("Alimentos", resultado.getRecursoNecesitado());
        assertEquals(10, resultado.getCantidad());
        assertEquals("Santiago", resultado.getUbicacion());
        assertEquals(EstadoNecesidad.PENDIENTE, resultado.getEstado());
    }

    @Test
    void deberiaListarNecesidades() {
        Mockito.when(necesidadRepository.findAll())
                .thenReturn(List.of(crearNecesidad()));

        List<NecesidadResponseDTO> resultado = necesidadService.listar();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Alimentos", resultado.get(0).getRecursoNecesitado());
    }

    @Test
    void deberiaBuscarNecesidadPorId() {
        Mockito.when(necesidadRepository.findById(1L))
                .thenReturn(Optional.of(crearNecesidad()));

        NecesidadResponseDTO resultado = necesidadService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Alimentos", resultado.getRecursoNecesitado());
    }

    @Test
    void deberiaActualizarNecesidad() {
        Necesidad necesidadExistente = crearNecesidad();
        NecesidadRequestDTO request = crearRequest();
        request.setRecursoNecesitado("Ropa");
        request.setCantidad(5);
        request.setUbicacion("Valparaíso");

        Mockito.when(necesidadRepository.findById(1L))
                .thenReturn(Optional.of(necesidadExistente));

        Mockito.when(necesidadRepository.save(any(Necesidad.class)))
                .thenReturn(necesidadExistente);

        NecesidadResponseDTO resultado = necesidadService.actualizar(1L, request);

        assertNotNull(resultado);
        assertEquals("Ropa", resultado.getRecursoNecesitado());
        assertEquals(5, resultado.getCantidad());
        assertEquals("Valparaíso", resultado.getUbicacion());
    }

    @Test
    void deberiaCambiarEstadoNecesidad() {
        Necesidad necesidad = crearNecesidad();

        Mockito.when(necesidadRepository.findById(1L))
                .thenReturn(Optional.of(necesidad));

        Mockito.when(necesidadRepository.save(any(Necesidad.class)))
                .thenReturn(necesidad);

        NecesidadResponseDTO resultado =
        necesidadService.cambiarEstado(1L, EstadoNecesidad.CUBIERTA);

        assertEquals(EstadoNecesidad.CUBIERTA, resultado.getEstado());
    }

    @Test
    void deberiaEliminarNecesidad() {
        Necesidad necesidad = crearNecesidad();

        Mockito.when(necesidadRepository.findById(1L))
                .thenReturn(Optional.of(necesidad));

        necesidadService.eliminar(1L);

        Mockito.verify(necesidadRepository).delete(necesidad);
    }

    @Test
    void deberiaLanzarErrorCuandoNoExisteNecesidad() {
        Mockito.when(necesidadRepository.findById(99L))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> necesidadService.buscarPorId(99L)
        );

        assertEquals("Necesidad no encontrada", exception.getMessage());
    }

    private NecesidadRequestDTO crearRequest() {
        NecesidadRequestDTO request = new NecesidadRequestDTO();
        request.setRecursoNecesitado("Alimentos");
        request.setCantidad(10);
        request.setUbicacion("Santiago");
        request.setDescripcion("Se necesitan alimentos no perecibles");
        request.setPrioridad(PrioridadNecesidad.ALTA);
        return request;
    }

    private Necesidad crearNecesidad() {
        Necesidad necesidad = new Necesidad();
        necesidad.setId(1L);
        necesidad.setRecursoNecesitado("Alimentos");
        necesidad.setCantidad(10);
        necesidad.setUbicacion("Santiago");
        necesidad.setDescripcion("Se necesitan alimentos no perecibles");
        necesidad.setPrioridad(PrioridadNecesidad.ALTA);
        necesidad.setEstado(EstadoNecesidad.PENDIENTE);
        return necesidad;
    }
}