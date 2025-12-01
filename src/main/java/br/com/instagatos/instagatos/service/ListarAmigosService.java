package br.com.instagatos.instagatos.service;

import br.com.instagatos.instagatos.controller.response.BuscarAmigosResponse;
import br.com.instagatos.instagatos.domain.Usuario;
import br.com.instagatos.instagatos.mapper.BuscarAmigosMapper;
import br.com.instagatos.instagatos.repository.AmizadeRepository;
import br.com.instagatos.instagatos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarAmigosService {

    private final AmizadeRepository amizadeRepository;
    private final UsuarioRepository usuarioRepository;

    public ListarAmigosService(AmizadeRepository amizadeRepository, UsuarioRepository usuarioRepository) {
        this.amizadeRepository = amizadeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<BuscarAmigosResponse> listarAmigos(Long usuarioId, String termo) {
        List<Long> amigosIds = amizadeRepository.findIdsAmigosDoUsuario(usuarioId);

        if (amigosIds.isEmpty()) {
            return List.of();
        }

        String termoFormatado = (termo == null || termo.isBlank()) ? "" : termo.toLowerCase();

        List<Usuario> amigos = usuarioRepository.buscarPorIdsENomeOuEmail(amigosIds, termoFormatado);

        return amigos.stream()
                .map(BuscarAmigosMapper::toResponse)
                .toList();
    }


}
