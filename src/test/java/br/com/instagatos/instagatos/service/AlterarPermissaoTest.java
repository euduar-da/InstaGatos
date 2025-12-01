package br.com.instagatos.instagatos.service;

import br.com.instagatos.instagatos.domain.Post;
import br.com.instagatos.instagatos.domain.Usuario;
import br.com.instagatos.instagatos.domain.enums.Permissao;
import br.com.instagatos.instagatos.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlterarPermissaoTest {

    @InjectMocks
    private AlterarPermissaoService testado;

    @Mock
    private PostRepository postRepository;

    private Usuario autor;
    private Post post;
    private Long postId;
    private Long autorId;
    private Long outroUsuarioId;

    @BeforeEach
    void setUp() {
        postId = 10L;
        autorId = 1L;
        outroUsuarioId = 2L;

        autor = new Usuario();
        autor.setId(autorId);

        post = new Post();
        post.setId(postId);
        post.setAutor(autor);
        post.setPermissaoVisualizacao(Permissao.PUBLICO);
    }

    @Test
    void deveAlterarPermissaoComSucesso() {
        // Arrange
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(postRepository.save(any(Post.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Post resultado = testado.alterarPermissao(postId, autorId, Permissao.PRIVADO);

        // Assert
        verify(postRepository).save(post);
        assertThat(resultado.getPermissaoVisualizacao()).isEqualTo(Permissao.PRIVADO);
    }

    @Test
    void deveLancarExcecaoQuandoPostNaoEncontrado() {
        // Arrange
        Long postIdInexistente = 99L;
        when(postRepository.findById(postIdInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            testado.alterarPermissao(postIdInexistente, autorId, Permissao.PRIVADO);
        });

        assertEquals("Post não encontrado", exception.getMessage());
        verify(postRepository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoForAutor() {
        // Arrange
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            testado.alterarPermissao(postId, outroUsuarioId, Permissao.PRIVADO);
        });

        assertEquals("Você não tem permissão para alterar este post", exception.getMessage());
        verify(postRepository, never()).save(any());
    }

    @Test
    void deveDefinirPermissaoComoPublicoQuandoNovaPermissaoForNula() {
        // Arrange
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(postRepository.save(any(Post.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Post resultado = testado.alterarPermissao(postId, autorId, null);

        // Assert
        verify(postRepository).save(post);
        assertThat(resultado.getPermissaoVisualizacao()).isEqualTo(Permissao.PUBLICO);
    }


}
