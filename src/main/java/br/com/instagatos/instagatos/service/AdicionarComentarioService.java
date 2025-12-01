package br.com.instagatos.instagatos.service;

import br.com.instagatos.instagatos.domain.Comentario;
import br.com.instagatos.instagatos.domain.Post;
import br.com.instagatos.instagatos.domain.Usuario;
import br.com.instagatos.instagatos.repository.ComentarioRepository;
import br.com.instagatos.instagatos.repository.PostRepository;
import br.com.instagatos.instagatos.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AdicionarComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final PostRepository postRepository;
    private final UsuarioRepository usuarioRepository;


    public Comentario adicionarComentario(Long usuarioId, Long postId, String texto) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post não encontrado"));

        Comentario comentario = new Comentario();
        comentario.setAutor(usuario);
        comentario.setPost(post);
        comentario.setTextoComentario(texto);
        return comentarioRepository.save(comentario);
    }


}
