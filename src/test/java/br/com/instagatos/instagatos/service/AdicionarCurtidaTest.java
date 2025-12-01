package br.com.instagatos.instagatos.service;

import br.com.instagatos.instagatos.domain.Curtida;
import br.com.instagatos.instagatos.domain.Post;
import br.com.instagatos.instagatos.domain.Usuario;
import br.com.instagatos.instagatos.repository.CurtidaRepository;
import br.com.instagatos.instagatos.repository.PostRepository;
import br.com.instagatos.instagatos.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdicionarCurtidaTest {

    @InjectMocks
    private AdicionarCurtidaService testado;

    @Mock
    private CurtidaRepository curtidaRepository;
    @Mock
    private PostRepository postRepository;
    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void deveCurtirPostComSucesso() {
        // Arrange
        Long usuarioId = 1L;
        Long postId = 10L;

        Usuario usuario = new Usuario();
        Post post = new Post();

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(curtidaRepository.findByUsuarioAndPost(usuario, post)).thenReturn(Optional.empty());

        // Act
        testado.curtirPost(usuarioId, postId);

        // Assert

        verify(curtidaRepository).save(any(Curtida.class));
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        // Arrange
        Long usuarioIdInexistente = 99L;
        Long postId = 10L;

        when(usuarioRepository.findById(usuarioIdInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            testado.curtirPost(usuarioIdInexistente, postId);
        });

        verify(curtidaRepository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoPostNaoEncontrado() {
        // Arrange
        Long usuarioId = 1L;
        Long postIdInexistente = 99L;

        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(postRepository.findById(postIdInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            testado.curtirPost(usuarioId, postIdInexistente);
        });

        verify(curtidaRepository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoPostJaCurtido() {
        // Arrange
        Long usuarioId = 1L;
        Long postId = 10L;

        Usuario usuario = new Usuario();
        Post post = new Post();
        Curtida curtidaExistente = new Curtida();

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(curtidaRepository.findByUsuarioAndPost(usuario, post)).thenReturn(Optional.of(curtidaExistente));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            testado.curtirPost(usuarioId, postId);
        });

        verify(curtidaRepository, never()).save(any());
    }
}
