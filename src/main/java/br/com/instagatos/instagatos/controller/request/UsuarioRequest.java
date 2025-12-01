package br.com.instagatos.instagatos.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioRequest {

    @NotBlank
    private String nomeCompleto;

    @NotBlank
    @Email
    private String email;

    private String apelido; //opcional

    @NotNull
    private LocalDate dataNascimento;

    @NotBlank
    private String senha;

    private String urlImagem; //opcional

}
