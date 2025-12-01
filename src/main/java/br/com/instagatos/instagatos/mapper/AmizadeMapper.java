package br.com.instagatos.instagatos.mapper;

import br.com.instagatos.instagatos.controller.response.SolicitarAmizadeResponse;
import br.com.instagatos.instagatos.domain.Amizade;

public class AmizadeMapper {

    private AmizadeMapper() {
        throw new IllegalStateException("Utility class");
    }
    public static SolicitarAmizadeResponse toResponse(Amizade amizade) {
        if (amizade == null) {
            return null;
        }

        return new SolicitarAmizadeResponse(
                amizade.getId(),
                amizade.getSolicitante() != null ? amizade.getSolicitante().getId() : null,
                amizade.getSolicitado() != null ? amizade.getSolicitado().getId() : null,
                amizade.getStatus()
        );
    }
}
