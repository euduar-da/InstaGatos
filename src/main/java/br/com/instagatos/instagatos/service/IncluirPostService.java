package br.com.instagatos.instagatos.service;

import br.com.instagatos.instagatos.controller.request.IncluirPostRequest;
import br.com.instagatos.instagatos.controller.response.IncluirPostResponse;
import br.com.instagatos.instagatos.domain.Post;
import br.com.instagatos.instagatos.domain.Usuario;
import br.com.instagatos.instagatos.mapper.IncluirPostMapper;
import br.com.instagatos.instagatos.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class IncluirPostService {

    private final PostRepository postRepository;

    public IncluirPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public IncluirPostResponse criar(Usuario autor, IncluirPostRequest request) {
        Post post = IncluirPostMapper.toEntity(request, autor);

        postRepository.save(post);
        return IncluirPostMapper.toResponse(post);
    }
}
