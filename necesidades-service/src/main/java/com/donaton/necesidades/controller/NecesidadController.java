package com.donaton.necesidades.controller;

import com.donaton.necesidades.dto.request.NecesidadRequestDTO;
import com.donaton.necesidades.dto.response.NecesidadResponseDTO;
import com.donaton.necesidades.enums.EstadoNecesidad;
import com.donaton.necesidades.service.NecesidadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/necesidades")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NecesidadController {

    private final NecesidadService necesidadService;

    @GetMapping
    public ResponseEntity<List<NecesidadResponseDTO>> listar() {
        return ResponseEntity.ok(necesidadService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NecesidadResponseDTO> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(necesidadService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<NecesidadResponseDTO> guardar(@Valid @RequestBody NecesidadRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(necesidadService.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NecesidadResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody NecesidadRequestDTO dto) {
        return ResponseEntity.ok(necesidadService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        necesidadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

   @PatchMapping("/{id}/estado/{estado}")
        public ResponseEntity<NecesidadResponseDTO> cambiarEstado(
                @PathVariable("id") Long id,
                @PathVariable("estado") EstadoNecesidad estado) {
    return ResponseEntity.ok(necesidadService.cambiarEstado(id, estado));
}

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<NecesidadResponseDTO>> buscarPorEstado(@PathVariable EstadoNecesidad estado) {
        return ResponseEntity.ok(necesidadService.buscarPorEstado(estado));
    }

    @GetMapping("/ubicacion")
    public ResponseEntity<List<NecesidadResponseDTO>> buscarPorUbicacion(@RequestParam String valor) {
        return ResponseEntity.ok(necesidadService.buscarPorUbicacion(valor));
    }
}
