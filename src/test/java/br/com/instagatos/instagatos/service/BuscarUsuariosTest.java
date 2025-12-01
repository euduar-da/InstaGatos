package br.com.instagatos.instagatos.service;
import br.com.instagatos.instagatos.controller.response.BuscarUsuarioResponse;
import br.com.instagatos.instagatos.domain.Usuario;
import br.com.instagatos.instagatos.repository.AmizadeRepository;
import br.com.instagatos.instagatos.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarUsuariosServiceTest {

    @InjectMocks
    private BuscarUsuariosService testado;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private AmizadeRepository amizadeRepository;

    @Test
    void deveRetornarUsuariosNaoAmigosQueCorrespondemAoFiltro() {
        // Arrange
        Long usuarioId = 1L;
        String termo = "gato";

        List<Long> idsDosAmigos = List.of(3L, 4L);

        Usuario usuarioEncontrado1 = new Usuario();
        usuarioEncontrado1.setId(5L);
        usuarioEncontrado1.setNomeCompleto("Gato Laranja");
        usuarioEncontrado1.setEmail("laranja@gato.com");

        Usuario usuarioEncontrado2 = new Usuario();
        usuarioEncontrado2.setId(6L);
        usuarioEncontrado2.setNomeCompleto("Gato Preto");
        usuarioEncontrado2.setEmail("preto@gato.com");

        List<Usuario> usuariosEncontrados = List.of(usuarioEncontrado1, usuarioEncontrado2);


        when(amizadeRepository.findIdsAmigosDoUsuario(usuarioId)).thenReturn(idsDosAmigos);

        when(usuarioRepository.buscarPorNomeOuEmailExcluindo(termo, usuarioId, idsDosAmigos))
                .thenReturn(usuariosEncontrados);

        // Act
        List<BuscarUsuarioResponse> result = testado.buscarUsuarios(usuarioId, termo);

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(5L);
        assertThat(result.get(0).getNomeCompleto()).isEqualTo("Gato Laranja");
        assertThat(result.get(1).getId()).isEqualTo(6L);
        assertThat(result.get(1).getNomeCompleto()).isEqualTo("Gato Preto");
    }

    @Test
    void deveRetornarListaVaziaQuandoNenhumUsuarioCorresponderAoFiltro() {
        // Arrange
        Long usuarioId = 1L;
        String termo = "cachorro";

        List<Long> idsDosAmigos = List.of(3L, 4L);

        when(amizadeRepository.findIdsAmigosDoUsuario(usuarioId)).thenReturn(idsDosAmigos);

        when(usuarioRepository.buscarPorNomeOuEmailExcluindo(termo, usuarioId, idsDosAmigos))
                .thenReturn(Collections.emptyList());

        List<BuscarUsuarioResponse> result = testado.buscarUsuarios(usuarioId, termo);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}
