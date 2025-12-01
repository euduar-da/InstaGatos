package br.com.instagatos.instagatos.mapper;

import br.com.instagatos.instagatos.controller.response.BuscarAmigosResponse;
import br.com.instagatos.instagatos.domain.Usuario;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BuscarAmigosMapperTest {
    @Test
    void deveMapearUsuarioParaBuscarAmigosResponseCorretamente() {
        // Arrange
        Usuario usuarioEntity = new Usuario();
        usuarioEntity.setId(1L);
        usuarioEntity.setNomeCompleto("Gato Amigo");
        usuarioEntity.setEmail("amigo@gato.com");

        // Act
        BuscarAmigosResponse result = BuscarAmigosMapper.toResponse(usuarioEntity);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(usuarioEntity.getId());
        assertThat(result.getNomeCompleto()).isEqualTo(usuarioEntity.getNomeCompleto());
        assertThat(result.getEmail()).isEqualTo(usuarioEntity.getEmail());
    }

    @Test
    void deveRetornarNuloQuandoUsuarioForNulo() {
        // Arrange
        Usuario usuarioEntity = null;

        // Act
        BuscarAmigosResponse result = BuscarAmigosMapper.toResponse(usuarioEntity);

        // Assert
        assertNull(result);
    }
}
