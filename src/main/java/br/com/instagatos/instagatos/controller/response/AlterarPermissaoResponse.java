package br.com.instagatos.instagatos.controller.response;

import br.com.instagatos.instagatos.domain.enums.Permissao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlterarPermissaoResponse {
    private Long postId;
    private Permissao permissao;
}
