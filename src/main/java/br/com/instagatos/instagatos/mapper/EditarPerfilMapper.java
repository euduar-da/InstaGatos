package br.com.instagatos.instagatos.mapper;

import br.com.instagatos.instagatos.controller.response.UsuarioResponse;
import br.com.instagatos.instagatos.domain.Usuario;

public class EditarPerfilMapper {

    private EditarPerfilMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static UsuarioResponse toResponse(Usuario entity) {
        if (entity == null) {
            return null;
        }
        return UsuarioResponse.builder()
                .nomeCompleto(entity.getNomeCompleto())
                .apelido(entity.getApelido())
                .urlImagem(entity.getUrlImagem())
                .build();
    }
}
