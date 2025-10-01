package com.reservatucancha.reserva_canchas_api.controller;

import com.reservatucancha.reserva_canchas_api.entity.Usuario;
import com.reservatucancha.reserva_canchas_api.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://Localhost:3000")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> optionalUsuario = usuarioService.findById(id);
        if (optionalUsuario.isPresent()) {
            return ResponseEntity.ok(optionalUsuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario savedUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok(savedUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        Optional<Usuario> optionalUsuario = usuarioService.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario existingUsuario = optionalUsuario.get();
            existingUsuario.setNombre(usuarioDetails.getNombre());
            existingUsuario.setApellido(usuarioDetails.getApellido());
            existingUsuario.setEmail(usuarioDetails.getEmail());
            existingUsuario.setPassword(usuarioDetails.getPassword());
            existingUsuario.setIdentificacion(usuarioDetails.getIdentificacion());
            existingUsuario.setTelefono(usuarioDetails.getTelefono());
            Usuario updatedUsuario = usuarioService.save(existingUsuario);
            return ResponseEntity.ok(updatedUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        if (usuarioService.findById(id).isPresent()) {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
