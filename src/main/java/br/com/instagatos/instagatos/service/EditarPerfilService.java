package br.com.instagatos.instagatos.service;

import br.com.instagatos.instagatos.controller.request.EditarPerfilRequest;
import br.com.instagatos.instagatos.controller.response.UsuarioResponse;
import br.com.instagatos.instagatos.domain.Usuario;
import br.com.instagatos.instagatos.mapper.UsuarioMapper;
import br.com.instagatos.instagatos.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EditarPerfilService {

    private final UsuarioRepository usuarioRepository;


    public UsuarioResponse editarMeuPerfil(Usuario usuarioLogado, EditarPerfilRequest request) {


        if (request.getNomeCompleto() != null && !request.getNomeCompleto().isBlank()) {
            usuarioLogado.setNomeCompleto(request.getNomeCompleto());
        }

        if (request.getApelido() != null) {
            usuarioLogado.setApelido(request.getApelido());
        }

        if (request.getUrlImagem() != null) {
            usuarioLogado.setUrlImagem(request.getUrlImagem());
        }

        usuarioRepository.save(usuarioLogado);

        return UsuarioMapper.toResponse(usuarioLogado);
    }
}
