package br.com.instagatos.instagatos.service;

import br.com.instagatos.instagatos.domain.Comentario;
import br.com.instagatos.instagatos.domain.Post;
import br.com.instagatos.instagatos.domain.Usuario;
import br.com.instagatos.instagatos.repository.ComentarioRepository;
import br.com.instagatos.instagatos.repository.PostRepository;
import br.com.instagatos.instagatos.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdicionarComentariosTest {


    @InjectMocks
    private AdicionarComentarioService testado;

    @Mock
    private ComentarioRepository comentarioRepository;
    @Mock
    private PostRepository postRepository;
    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void deveAdicionarComentarioComSucesso() {
        // Arrange
        Long usuarioId = 1L;
        Long postId = 10L;
        String texto = "Que foto legal!";

        Usuario usuario = new Usuario();
        Post post = new Post();


        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(comentarioRepository.save(any(Comentario.class))).then(invocation -> invocation.getArgument(0));
        // Act
        Comentario result = testado.adicionarComentario(usuarioId, postId, texto);

        // Assert

        verify(comentarioRepository).save(any(Comentario.class));

        assertThat(result.getAutor()).isEqualTo(usuario);
        assertThat(result.getPost()).isEqualTo(post);
        assertThat(result.getTextoComentario()).isEqualTo(texto);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoForEncontrado() {
        // Arrange
        Long usuarioIdInexistente = 99L;
        Long postId = 10L;
        String texto = "Comentário";

        when(usuarioRepository.findById(usuarioIdInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            testado.adicionarComentario(usuarioIdInexistente, postId, texto);
        });

        verify(comentarioRepository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoPostNaoForEncontrado() {
        // Arrange
        Long usuarioId = 1L;
        Long postIdInexistente = 99L;
        String texto = "Comentário";

        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(postRepository.findById(postIdInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            testado.adicionarComentario(usuarioId, postIdInexistente, texto);
        });

        verify(comentarioRepository, never()).save(any());
    }
}
