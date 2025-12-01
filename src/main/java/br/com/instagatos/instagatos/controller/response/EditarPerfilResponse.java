package br.com.instagatos.instagatos.controller.response;

import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EditarPerfilResponse {

    private Long id;
    private String nomeCompleto;
    private String apelido;
    private String urlImagem;

}
