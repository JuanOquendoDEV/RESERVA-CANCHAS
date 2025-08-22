package com.reservatucancha.reserva_canchas_api.controller;

import com.reservatucancha.reserva_canchas_api.entity.Cancha;
import com.reservatucancha.reserva_canchas_api.service.CanchaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/canchas")
public class CanchaController {

    private final CanchaService canchaService;

    public CanchaController(CanchaService canchaService) {
        this.canchaService = canchaService;
    }

    @GetMapping
    public List<Cancha> getAllCanchas() {
        return canchaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cancha> getCanchaById(@PathVariable Long id) {
        Optional<Cancha> optionalCancha = canchaService.findById(id);
        if (optionalCancha.isPresent()) {
            return ResponseEntity.ok(optionalCancha.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cancha> createCancha(@RequestBody Cancha cancha) {
        Cancha savedCancha = canchaService.save(cancha);
        return ResponseEntity.ok(savedCancha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cancha> updateCancha(@PathVariable Long id, @RequestBody Cancha canchaDetails) {
        Optional<Cancha> optionalCancha = canchaService.findById(id);
        if (optionalCancha.isPresent()) {
            Cancha existingCancha = optionalCancha.get();
            existingCancha.setNombre(canchaDetails.getNombre());
            existingCancha.setTipoCancha(canchaDetails.getTipoCancha());
            existingCancha.setPrecioPorHora(canchaDetails.getPrecioPorHora());
            Cancha updatedCancha = canchaService.save(existingCancha);
            return ResponseEntity.ok(updatedCancha);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCancha(@PathVariable Long id) {
        if (canchaService.findById(id).isPresent()) {
            canchaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
