package com.donaton.necesidades.service;

import com.donaton.necesidades.dto.request.NecesidadRequestDTO;
import com.donaton.necesidades.dto.response.NecesidadResponseDTO;
import com.donaton.necesidades.enums.EstadoNecesidad;
import com.donaton.necesidades.mapper.NecesidadMapper;
import com.donaton.necesidades.model.Necesidad;
import com.donaton.necesidades.repository.NecesidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NecesidadService {

    private final NecesidadRepository necesidadRepository;

    public List<NecesidadResponseDTO> listar() {
        return necesidadRepository.findAll()
                .stream()
                .map(NecesidadMapper::toResponseDTO)
                .toList();
    }

    public NecesidadResponseDTO buscarPorId(Long id) {
        return NecesidadMapper.toResponseDTO(obtenerEntidadPorId(id));
    }

    public NecesidadResponseDTO guardar(NecesidadRequestDTO dto) {
        Necesidad necesidad = NecesidadMapper.toEntity(dto);
        necesidad.setFechaReporte(LocalDateTime.now());
        necesidad.setEstado(EstadoNecesidad.PENDIENTE);

        return NecesidadMapper.toResponseDTO(necesidadRepository.save(necesidad));
    }

    public NecesidadResponseDTO actualizar(Long id, NecesidadRequestDTO dto) {
        Necesidad necesidad = obtenerEntidadPorId(id);

        necesidad.setRecursoNecesitado(dto.getRecursoNecesitado());
        necesidad.setCantidad(dto.getCantidad());
        necesidad.setUbicacion(dto.getUbicacion());
        necesidad.setDescripcion(dto.getDescripcion());
        necesidad.setPrioridad(dto.getPrioridad());

        return NecesidadMapper.toResponseDTO(necesidadRepository.save(necesidad));
    }

    public void eliminar(Long id) {
        Necesidad necesidad = obtenerEntidadPorId(id);
        necesidadRepository.delete(necesidad);
    }

    public NecesidadResponseDTO cambiarEstado(Long id, EstadoNecesidad estado) {
        Necesidad necesidad = obtenerEntidadPorId(id);
        necesidad.setEstado(estado);

        return NecesidadMapper.toResponseDTO(necesidadRepository.save(necesidad));
    }

    public List<NecesidadResponseDTO> buscarPorEstado(EstadoNecesidad estado) {
        return necesidadRepository.findByEstado(estado)
                .stream()
                .map(NecesidadMapper::toResponseDTO)
                .toList();
    }

    public List<NecesidadResponseDTO> buscarPorUbicacion(String ubicacion) {
        return necesidadRepository.findByUbicacionContainingIgnoreCase(ubicacion)
                .stream()
                .map(NecesidadMapper::toResponseDTO)
                .toList();
    }

    private Necesidad obtenerEntidadPorId(Long id) {
        return necesidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Necesidad no encontrada"));
    }
}
