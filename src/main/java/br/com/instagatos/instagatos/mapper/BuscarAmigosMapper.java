package br.com.instagatos.instagatos.mapper;

import br.com.instagatos.instagatos.controller.response.BuscarAmigosResponse;
import br.com.instagatos.instagatos.domain.Usuario;

public class BuscarAmigosMapper {
    public static BuscarAmigosResponse toResponse(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new BuscarAmigosResponse(usuario.getId(), usuario.getNomeCompleto(), usuario.getEmail());
    }
}
