package br.com.instagatos.instagatos.mapper;

import br.com.instagatos.instagatos.controller.request.IncluirPostRequest;
import br.com.instagatos.instagatos.controller.response.IncluirPostResponse;
import br.com.instagatos.instagatos.controller.response.UsuarioResponse;
import br.com.instagatos.instagatos.domain.Post;
import br.com.instagatos.instagatos.domain.Usuario;

import java.time.LocalDateTime;

public class IncluirPostMapper {


    public static Post toEntity(IncluirPostRequest request, Usuario autor) {
        Post entity = new Post();
        entity.setTextoPost(request.getTextoPost());
        entity.setPermissaoVisualizacao(request.getPermissaoVisualizacao());
        entity.setUrlImagemPost(request.getUrlImagemPost());

        entity.setAutor(autor);
        entity.setDataPostagem(LocalDateTime.now());
        return entity;
    }

    public static IncluirPostResponse toResponse(Post entity) {
        if (entity == null) {
            return null;
        }
        Usuario autorEntity = entity.getAutor();


        UsuarioResponse autorResponse = UsuarioMapper.toResponse(autorEntity);

        return IncluirPostResponse.builder()
                .id(entity.getId())
                .textoPost(entity.getTextoPost())
                .permissaoVisualizacao(entity.getPermissaoVisualizacao())
                .dataPostagem(entity.getDataPostagem())
                .urlImagemPost(entity.getUrlImagemPost())
                .autor(autorResponse)
                .build();
    }

}
