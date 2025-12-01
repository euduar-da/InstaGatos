package br.com.instagatos.instagatos.service;

import br.com.instagatos.instagatos.controller.request.UsuarioRequest;
import br.com.instagatos.instagatos.controller.response.UsuarioResponse;
import br.com.instagatos.instagatos.domain.Usuario;
import br.com.instagatos.instagatos.mapper.UsuarioMapper;
import br.com.instagatos.instagatos.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class IncluirUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public IncluirUsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponse incluir(UsuarioRequest request) {
        Usuario usuario = UsuarioMapper.toEntity(request);
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));


        usuarioRepository.save(usuario);

        return UsuarioMapper.toResponse(usuario);
    }

}
