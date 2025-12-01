package br.com.instagatos.instagatos.controller.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListarSolicitacoesResponse {
    Long idSolicitante;
    String nomeSolicitante;
    String urlImagem;
}
