package br.com.instagatos.instagatos.service;

import br.com.instagatos.instagatos.controller.response.IncluirPostResponse;
import br.com.instagatos.instagatos.mapper.IncluirPostMapper;
import br.com.instagatos.instagatos.repository.AmizadeRepository;
import br.com.instagatos.instagatos.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ListarPostsService {


    private final PostRepository postRepository;
    private final AmizadeRepository amizadeRepository;


    public Page<IncluirPostResponse> listarFeed(Long usuarioId, Pageable pageable) {

        List<Long> amigosIds = amizadeRepository.findIdsAmigosDoUsuario(usuarioId);

        return postRepository.listarPostsUsuarioEAmigos(usuarioId, pageable)
                .map(IncluirPostMapper::toResponse);
    }

}
