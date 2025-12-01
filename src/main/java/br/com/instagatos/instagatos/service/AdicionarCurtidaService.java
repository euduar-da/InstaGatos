package br.com.instagatos.instagatos.service;

import br.com.instagatos.instagatos.domain.Curtida;
import br.com.instagatos.instagatos.domain.Post;
import br.com.instagatos.instagatos.domain.Usuario;
import br.com.instagatos.instagatos.repository.CurtidaRepository;
import br.com.instagatos.instagatos.repository.PostRepository;
import br.com.instagatos.instagatos.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class AdicionarCurtidaService {

    private final CurtidaRepository curtidaRepository;
    private final PostRepository postRepository;
    private final UsuarioRepository usuarioRepository;


    public void curtirPost(Long usuarioId, Long postId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post não encontrado"));

        if (curtidaRepository.findByUsuarioAndPost(usuario, post).isPresent()) {
            throw new RuntimeException("Já curtiu esse post");
        }

        Curtida curtida = new Curtida(post, usuario);
        curtida.setUsuario(usuario);
        curtida.setPost(post);
        curtidaRepository.save(curtida);
    }
}

