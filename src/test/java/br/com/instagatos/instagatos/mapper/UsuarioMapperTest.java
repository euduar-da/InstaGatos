package br.com.instagatos.instagatos.mapper;

import br.com.instagatos.instagatos.controller.request.UsuarioRequest;
import br.com.instagatos.instagatos.controller.response.UsuarioResponse;
import br.com.instagatos.instagatos.domain.Usuario;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UsuarioMapperTest {


    @Test
    void deveMapearRequestParaEntidadeCorretamente() {
        // Arrange
        UsuarioRequest request = new UsuarioRequest();
        request.setNomeCompleto("Gato Teste");
        request.setEmail("gato@teste.com");
        request.setApelido("gatoteste");
        request.setDataNascimento(LocalDate.of(2020, 1, 1));
        request.setSenha("senha123");
        request.setUrlImagem("http://url.com/gato.png");

        // Act
        Usuario entity = UsuarioMapper.toEntity(request);

        // Assert
        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isNull();
        assertThat(entity.getNomeCompleto()).isEqualTo(request.getNomeCompleto());
        assertThat(entity.getEmail()).isEqualTo(request.getEmail());
        assertThat(entity.getApelido()).isEqualTo(request.getApelido());
        assertThat(entity.getDataNascimento()).isEqualTo(request.getDataNascimento());
        assertThat(entity.getSenha()).isEqualTo(request.getSenha());
        assertThat(entity.getUrlImagem()).isEqualTo(request.getUrlImagem());
    }

    @Test
    void toEntityDeveRetornarNuloQuandoRequestForNulo() {
        // Act
        Usuario entity = UsuarioMapper.toEntity(null);

        // Assert
        assertNull(entity);
    }


    @Test
    void deveMapearEntidadeParaResponseCorretamente() {
        // Arrange
        Usuario entity = new Usuario();
        entity.setId(1L);
        entity.setNomeCompleto("Gato Entidade");
        entity.setEmail("entidade@gato.com");
        entity.setApelido("entigato");
        entity.setDataNascimento(LocalDate.of(2019, 5, 10));
        entity.setUrlImagem("http://url.com/entidade.png");

        // Act
        UsuarioResponse response = UsuarioMapper.toResponse(entity);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(entity.getId());
        assertThat(response.getNomeCompleto()).isEqualTo(entity.getNomeCompleto());
        assertThat(response.getEmail()).isEqualTo(entity.getEmail());
        assertThat(response.getApelido()).isEqualTo(entity.getApelido());
        assertThat(response.getDataNascimento()).isEqualTo(entity.getDataNascimento());
        assertThat(response.getUrlImagem()).isEqualTo(entity.getUrlImagem());
    }

    @Test
    void toResponseDeveRetornarNuloQuandoEntidadeForNula() {
        // Act
        UsuarioResponse response = UsuarioMapper.toResponse(null);

        // Assert
        assertNull(response);
    }
}
