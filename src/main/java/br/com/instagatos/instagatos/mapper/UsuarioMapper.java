package br.com.instagatos.instagatos.mapper;

import br.com.instagatos.instagatos.controller.request.UsuarioRequest;
import br.com.instagatos.instagatos.controller.response.UsuarioResponse;
import br.com.instagatos.instagatos.domain.Usuario;

public class UsuarioMapper {

    private UsuarioMapper() {
        throw new IllegalStateException("Utility class");
    }
    public static Usuario toEntity(UsuarioRequest request) {
        if (request == null) {
            return null;
        }
        Usuario entity = new Usuario();
        entity.setNomeCompleto(request.getNomeCompleto());
        entity.setEmail(request.getEmail());
        entity.setApelido(request.getApelido());
        entity.setDataNascimento(request.getDataNascimento());
        entity.setSenha(request.getSenha());
        entity.setUrlImagem(request.getUrlImagem());
        return entity;
    }

    public static UsuarioResponse toResponse(Usuario entity) {
        if (entity == null) {
            return null;
        }
        return UsuarioResponse.builder()
                .id(entity.getId())
                .nomeCompleto(entity.getNomeCompleto())
                .email(entity.getEmail())
                .apelido(entity.getApelido())
                .dataNascimento(entity.getDataNascimento())
                .urlImagem(entity.getUrlImagem())
                .build();
    }

}
