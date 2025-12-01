package br.com.instagatos.instagatos.controller.request;

import br.com.instagatos.instagatos.domain.enums.Permissao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncluirPostRequest {

    private String textoPost;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Permissao permissaoVisualizacao;

    @NotBlank
    private String urlImagemPost;
}
