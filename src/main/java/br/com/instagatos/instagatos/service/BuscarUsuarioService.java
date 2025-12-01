package br.com.instagatos.instagatos.service;

import br.com.instagatos.instagatos.controller.response.UsuarioResponse;
import br.com.instagatos.instagatos.domain.Usuario;
import br.com.instagatos.instagatos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.instagatos.instagatos.mapper.UsuarioMapper.toResponse;

@Service
public class BuscarUsuarioService {

    private final UsuarioAutenticadoService usuarioAutenticadoService;
    private final UsuarioRepository usuarioRepository;

    public BuscarUsuarioService(UsuarioAutenticadoService usuarioAutenticadoService, UsuarioRepository usuarioRepository) {
        this.usuarioAutenticadoService = usuarioAutenticadoService;
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponse buscar() {
        Usuario usuarioAutenticado = usuarioAutenticadoService.get();
        return toResponse(usuarioAutenticado);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
