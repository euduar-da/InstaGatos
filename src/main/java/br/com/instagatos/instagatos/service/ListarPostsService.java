package br.com.instagatos.instagatos.service;

import br.com.instagatos.instagatos.controller.response.IncluirPostResponse;
import br.com.instagatos.instagatos.mapper.IncluirPostMapper;
import br.com.instagatos.instagatos.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class ListarPostsService {


    private final PostRepository postRepository;


    public Page<IncluirPostResponse> listarFeed(Long usuarioId, Pageable pageable) {


        return postRepository.listarPostsUsuarioEAmigos(usuarioId, pageable)
                .map(IncluirPostMapper::toResponse);
    }

}
