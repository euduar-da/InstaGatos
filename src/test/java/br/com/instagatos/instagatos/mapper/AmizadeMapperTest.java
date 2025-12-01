package br.com.instagatos.instagatos.mapper;

import br.com.instagatos.instagatos.controller.response.SolicitarAmizadeResponse;
import br.com.instagatos.instagatos.domain.Amizade;
import br.com.instagatos.instagatos.domain.Usuario;
import br.com.instagatos.instagatos.domain.enums.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmizadeMapperTest {
    @Test
    void deveMapearAmizadeParaSolicitarAmizadeResponse() {

        Usuario solicitante = new Usuario();
        solicitante.setId(1L);

        Usuario solicitado = new Usuario();
        solicitado.setId(2L);

        Amizade amizade = new Amizade();
        amizade.setId(100L);
        amizade.setSolicitante(solicitante);
        amizade.setSolicitado(solicitado);
        amizade.setStatus(Status.PENDENTE);

        // Action
        SolicitarAmizadeResponse result = AmizadeMapper.toResponse(amizade);

        assertNotNull(result);
        assertEquals(amizade.getId(), result.getId());
        assertEquals(amizade.getSolicitante().getId(), result.getSolicitanteId());
        assertEquals(amizade.getSolicitado().getId(), result.getSolicitadoId());
        assertEquals(amizade.getStatus(), result.getStatus());
    }

    @Test
    void deveMapearAmizadeNulaParaAmizadeResponseNula() {
        // Action
        SolicitarAmizadeResponse result = AmizadeMapper.toResponse(null);

        // Assertions
        assertNull(result);
    }


    @Test
    void deveRetornarResponseComIdsDeUsuarioNulosQuandoAmizadeTemUsuariosNulos() {
        // Arrange
        Amizade amizadeIncompleta = new Amizade();
        amizadeIncompleta.setId(101L);
        amizadeIncompleta.setStatus(Status.PENDENTE);

        // Act
        SolicitarAmizadeResponse result = AmizadeMapper.toResponse(amizadeIncompleta);

        // Assert
        assertNotNull(result);
        assertEquals(101L, result.getId());
        assertEquals(Status.PENDENTE, result.getStatus());
        assertNull(result.getSolicitanteId());
        assertNull(result.getSolicitadoId());

    }
}
