package br.com.instagatos.instagatos.controller.response;

import br.com.instagatos.instagatos.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SolicitarAmizadeResponse {

    private Long id;
    private Long solicitanteId;
    private Long solicitadoId;
    private Status status;
}
