package br.com.instagatos.instagatos.controller;


import br.com.instagatos.instagatos.controller.request.LoginRequest;
import br.com.instagatos.instagatos.controller.response.LoginResponse;
import br.com.instagatos.instagatos.domain.Usuario;
import br.com.instagatos.instagatos.service.BuscarUsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Optional;


@RestController
@RequestMapping("/login")
public class LoginController {

    private final BuscarUsuarioService buscarUsuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    public LoginController(BuscarUsuarioService buscarUsuarioService, PasswordEncoder passwordEncoder, JwtEncoder jwtEncoder) {
        this.buscarUsuarioService = buscarUsuarioService;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<Usuario> optUser = buscarUsuarioService.buscarPorEmail(loginRequest.getEmail());

        if (optUser.isEmpty() || !isLoginCorreto(loginRequest.getSenha(), optUser.get().getSenha())) {
            throw new BadCredentialsException("Credenciais inválidas!");
        }

        Usuario usuario = optUser.get();

        long expiresIn = 60000L;

        JwtClaimsSet jwt = JwtClaimsSet.builder()
                .issuer("seguranca-api")
                .subject(usuario.getId().toString())
                .expiresAt(Instant.now().plusSeconds(expiresIn))
                .issuedAt(Instant.now())
                .claim("email", usuario.getEmail())

                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(jwt)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(token, expiresIn));
    }

    private boolean isLoginCorreto(String password, String savedPassowrd) {
        return passwordEncoder.matches(password, savedPassowrd);
    }
}