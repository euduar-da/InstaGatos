package br.com.instagatos.instagatos.service;

import br.com.instagatos.instagatos.domain.Post;
import br.com.instagatos.instagatos.domain.enums.Permissao;
import br.com.instagatos.instagatos.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AlterarPermissaoService {

    private final PostRepository postRepository;


    public Post alterarPermissao(Long postId, Long usuarioId, Permissao novaPermissao) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post não encontrado"));

        if (!post.getAutor().getId().equals(usuarioId)) {
            throw new RuntimeException("Você não tem permissão para alterar este post");
        }

        post.setPermissaoVisualizacao(novaPermissao != null ? novaPermissao : Permissao.PUBLICO);
        return postRepository.save(post);
    }

}
