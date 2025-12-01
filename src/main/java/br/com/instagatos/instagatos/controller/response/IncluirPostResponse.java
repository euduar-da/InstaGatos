package br.com.instagatos.instagatos.controller.response;

import br.com.instagatos.instagatos.domain.enums.Permissao;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class IncluirPostResponse {
    private Long id;
    private String textoPost;
    private Permissao permissaoVisualizacao;
    private String urlImagemPost;
    private LocalDateTime dataPostagem;
    private UsuarioResponse autor;
}
