package br.com.instagatos.instagatos.mapper;

import br.com.instagatos.instagatos.controller.response.UsuarioResponse;
import br.com.instagatos.instagatos.domain.Usuario;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EditarPerfilMapperTest {

    @Test
    void deveMapearUsuarioParaUsuarioResponseCorretamente() {

        // Arrange
        Usuario usuarioEntity = new Usuario();
        usuarioEntity.setNomeCompleto("Gato Testador");
        usuarioEntity.setApelido("Gato Sênior");
        usuarioEntity.setUrlImagem("http://url.com/gato.png");

        // Act
        UsuarioResponse result = EditarPerfilMapper.toResponse(usuarioEntity);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getNomeCompleto()).isEqualTo("Gato Testador");
        assertThat(result.getApelido()).isEqualTo("Gato Sênior");
        assertThat(result.getUrlImagem()).isEqualTo("http://url.com/gato.png");
    }

    @Test
    void deveRetornarNuloQuandoEntidadeForNula() {
        // Arrange
        Usuario usuarioEntity = null;

        // Act
        UsuarioResponse result = EditarPerfilMapper.toResponse(usuarioEntity);

        // Assert
        assertNull(result);
    }

    @Test
    void deveMapearCorretamenteQuandoCamposNaoObrigatoriosDaEntidadeSaoNulos() {
        // Arrange
        Usuario usuarioEntity = new Usuario();
        usuarioEntity.setNomeCompleto("Gato Apenas Com Nome");
        usuarioEntity.setApelido(null);
        usuarioEntity.setUrlImagem(null);

        // Act
        UsuarioResponse result = EditarPerfilMapper.toResponse(usuarioEntity);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getNomeCompleto()).isEqualTo("Gato Apenas Com Nome");
        assertThat(result.getApelido()).isNull();
        assertThat(result.getUrlImagem()).isNull();
    }

}
