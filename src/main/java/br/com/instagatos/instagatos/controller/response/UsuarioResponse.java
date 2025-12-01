package br.com.instagatos.instagatos.controller.response;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioResponse {

    private Long id;
    private String nomeCompleto;
    private String email;
    private String apelido; //opcional
    private LocalDate dataNascimento;
    private String urlImagem; //opcional

}
