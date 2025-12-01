package br.com.instagatos.instagatos.service;

import br.com.instagatos.instagatos.controller.response.ListarSolicitacoesResponse;
import br.com.instagatos.instagatos.repository.AmizadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ListarSolicitacoesService {

    private final AmizadeRepository amizadeRepository;

    public List<ListarSolicitacoesResponse> listarSolicitacoesPendentes(Long idUsuarioLogado) {
        return amizadeRepository.findSolicitacoesPendentes(idUsuarioLogado);
    }

}
