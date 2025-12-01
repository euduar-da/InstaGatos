package br.com.instagatos.instagatos.controller.response;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DashboardResponse {

    UsuarioResponse dadosUsuarioLogado;
    List<ListarSolicitacoesResponse> solicitacoesAmizade;
}
