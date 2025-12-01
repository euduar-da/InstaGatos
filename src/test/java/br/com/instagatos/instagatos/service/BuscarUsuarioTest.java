package br.com.instagatos.instagatos.service;

import br.com.instagatos.instagatos.controller.response.UsuarioResponse;
import br.com.instagatos.instagatos.domain.Usuario;
import br.com.instagatos.instagatos.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarUsuarioTest {

    @InjectMocks
    private BuscarUsuarioService testado;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private UsuarioRepository usuarioRepository;


    @Test
    void deveRetornarUsuarioAutenticadoMapeadoParaResponse() {
        // Arrange
        Usuario usuarioLogado = new Usuario();
        usuarioLogado.setId(1L);
        usuarioLogado.setNomeCompleto("Gato Logado");
        usuarioLogado.setEmail("logado@gato.com");


        when(usuarioAutenticadoService.get()).thenReturn(usuarioLogado);

        // Act
        UsuarioResponse result = testado.buscar();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNomeCompleto()).isEqualTo("Gato Logado");
    }


    @Test
    void deveRetornarUsuarioQuandoEncontradoPorEmail() {
        // Arrange
        String emailParaBuscar = "teste@email.com";
        Usuario usuarioEncontrado = new Usuario();
        usuarioEncontrado.setEmail(emailParaBuscar);


        when(usuarioRepository.findByEmail(emailParaBuscar)).thenReturn(Optional.of(usuarioEncontrado));

        // Act
        Optional<Usuario> result = testado.buscarPorEmail(emailParaBuscar);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(usuarioEncontrado);
    }

    @Test
    void deveRetornarOptionalVazioQuandoNaoEncontradoPorEmail() {
        // Arrange
        String emailInexistente = "naoexiste@email.com";


        when(usuarioRepository.findByEmail(emailInexistente)).thenReturn(Optional.empty());

        // Act
        Optional<Usuario> result = testado.buscarPorEmail(emailInexistente);

        // Assert
        assertThat(result).isEmpty();
    }
}
