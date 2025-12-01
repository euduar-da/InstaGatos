package br.com.instagatos.instagatos.mapper;

import br.com.instagatos.instagatos.controller.request.IncluirPostRequest;
import br.com.instagatos.instagatos.controller.response.IncluirPostResponse;
import br.com.instagatos.instagatos.domain.Post;
import br.com.instagatos.instagatos.domain.Usuario;
import br.com.instagatos.instagatos.domain.enums.Permissao;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class IncluirPostMapperTest {



    @Test
    void deveMapearRequestParaEntidadeCorretamente() {
        // Arrange
        IncluirPostRequest request = new IncluirPostRequest();
        request.setTextoPost("Texto do post de teste");
        request.setUrlImagemPost("http://url.com/imagem.png");
        request.setPermissaoVisualizacao(Permissao.PUBLICO);

        Usuario autor = new Usuario();
        autor.setId(1L);

        // Act
        Post entity = IncluirPostMapper.toEntity(request, autor);

        // Assert
        assertThat(entity).isNotNull();
        assertThat(entity.getTextoPost()).isEqualTo("Texto do post de teste");
        assertThat(entity.getUrlImagemPost()).isEqualTo("http://url.com/imagem.png");
        assertThat(entity.getPermissaoVisualizacao()).isEqualTo(Permissao.PUBLICO);
        assertThat(entity.getAutor()).isEqualTo(autor);
        assertThat(entity.getDataPostagem()).isNotNull();
    }

    @Test
    void deveMapearEntidadeParaResponseCorretamente() {
        // Arrange
        Usuario autorEntity = new Usuario();
        autorEntity.setId(1L);
        autorEntity.setNomeCompleto("Gato Autor");

        Post postEntity = new Post();
        postEntity.setId(100L);
        postEntity.setTextoPost("Texto do post");
        postEntity.setUrlImagemPost("http://url.com/imagem.png");
        postEntity.setPermissaoVisualizacao(Permissao.PUBLICO);
        postEntity.setDataPostagem(LocalDateTime.now());
        postEntity.setAutor(autorEntity);

        // Act
        IncluirPostResponse result = IncluirPostMapper.toResponse(postEntity);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(100L);
        assertThat(result.getTextoPost()).isEqualTo("Texto do post");
        assertThat(result.getUrlImagemPost()).isEqualTo("http://url.com/imagem.png");
        assertThat(result.getPermissaoVisualizacao()).isEqualTo(Permissao.PUBLICO);
        assertThat(result.getDataPostagem()).isEqualTo(postEntity.getDataPostagem());

        assertThat(result.getAutor()).isNotNull();
        assertThat(result.getAutor().getId()).isEqualTo(autorEntity.getId());
        assertThat(result.getAutor().getNomeCompleto()).isEqualTo(autorEntity.getNomeCompleto());
    }

    @Test
    void deveRetornarNuloQuandoEntidadePostForNula() {
        // Act
        IncluirPostResponse result = IncluirPostMapper.toResponse(null);

        // Assert
        assertNull(result);
    }
}
