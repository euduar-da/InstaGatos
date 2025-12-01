package br.com.instagatos.instagatos.controller.request;

import br.com.instagatos.instagatos.domain.enums.Permissao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlterarPermissaoRequest {
    private Permissao permissao;
}
