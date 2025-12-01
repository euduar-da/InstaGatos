package br.com.instagatos.instagatos.service;

import br.com.instagatos.instagatos.controller.response.BuscarUsuarioResponse;
import br.com.instagatos.instagatos.domain.Usuario;
import br.com.instagatos.instagatos.repository.AmizadeRepository;
import br.com.instagatos.instagatos.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BuscarUsuariosService {
    private final UsuarioRepository usuarioRepository;
    private final AmizadeRepository amizadeRepository;


    public List<BuscarUsuarioResponse> buscarUsuarios(Long usuarioId, String termo) {
        List<Long> amigosIds = amizadeRepository.findIdsAmigosDoUsuario(usuarioId);


        List<Usuario> usuarios = usuarioRepository.buscarPorNomeOuEmailExcluindo(termo, usuarioId, amigosIds);

        return usuarios.stream()
                .map(u -> new BuscarUsuarioResponse(u.getId(), u.getNomeCompleto(), u.getEmail()))
                .toList();
    }
}
