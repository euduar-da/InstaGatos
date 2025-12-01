package br.com.instagatos.instagatos.controller.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditarPerfilRequest {

    @Size(max = 255)
    private String nomeCompleto;
    @Size(max = 50)
    private String apelido;
    @Size(max = 512)
    private String urlImagem;
}
