package com.reservatucancha.reserva_canchas_api.controller;

import com.reservatucancha.reserva_canchas_api.dto.LoginDto;
import com.reservatucancha.reserva_canchas_api.dto.RegisterDto;
import com.reservatucancha.reserva_canchas_api.dto.TokenValidationDto;
import com.reservatucancha.reserva_canchas_api.entity.Usuario;
import com.reservatucancha.reserva_canchas_api.jwt.JwtTokenProvider;
import com.reservatucancha.reserva_canchas_api.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@CrossOrigin(origins = "http://Localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (usuarioRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            return new ResponseEntity<>("El email ya está registrado", HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(registerDto.getNombre());
        usuario.setApellido(registerDto.getApellido());
        usuario.setEmail(registerDto.getEmail());
        usuario.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        usuario.setIdentificacion(registerDto.getIdentificacion());
        usuario.setTelefono(registerDto.getTelefono());

        usuarioRepository.save(usuario);
        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/verify-token")
    public ResponseEntity<TokenValidationDto> verifyToken(@RequestHeader("Authorization") String authHeader) {
        try {
            // Verificar que el header tiene el formato correcto
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.ok(new TokenValidationDto(false, "Token no proporcionado o formato incorrecto"));
            }

            // Extraer el token del header (remover "Bearer ")
            String token = authHeader.substring(7);

            // Validar el token
            if (!tokenProvider.validateToken(token)) {
                return ResponseEntity.ok(new TokenValidationDto(false, "Token inválido o malformado"));
            }

            // Verificar si el token ha expirado
            if (tokenProvider.isTokenExpired(token)) {
                return ResponseEntity.ok(new TokenValidationDto(false, "Token expirado"));
            }

            // Si llega aquí, el token es válido
            String username = tokenProvider.getUsernameFromJwt(token);
            TokenValidationDto response = new TokenValidationDto(
                true,
                username,
                tokenProvider.getIssuedAtFromToken(token),
                tokenProvider.getExpirationDateFromToken(token),
                "Token válido"
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.ok(new TokenValidationDto(false, "Error al procesar el token: " + e.getMessage()));
        }
    }
}