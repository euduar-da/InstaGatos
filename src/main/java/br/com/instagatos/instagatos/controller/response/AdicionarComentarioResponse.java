package br.com.instagatos.instagatos.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdicionarComentarioResponse {
    private Long id;
    private String texto;
}
