package br.com.instagatos.instagatos.service;

import br.com.instagatos.instagatos.controller.response.DashboardResponse;
import br.com.instagatos.instagatos.controller.response.ListarSolicitacoesResponse;
import br.com.instagatos.instagatos.controller.response.UsuarioResponse;
import br.com.instagatos.instagatos.domain.Usuario;
import br.com.instagatos.instagatos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class DashboardService {

    private final UsuarioRepository usuarioRepository;
    private final ListarSolicitacoesService listarSolicitacoesService;

    public DashboardService(UsuarioRepository usuarioRepository, ListarSolicitacoesService listarSolicitacoesService) {
        this.usuarioRepository = usuarioRepository;
        this.listarSolicitacoesService = listarSolicitacoesService;
    }

    public DashboardResponse montarDashboard(Long idUsuarioLogado) {

        Usuario usuario = usuarioRepository.findById(idUsuarioLogado)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado. ID: " + idUsuarioLogado));

        UsuarioResponse usuarioDTO = new UsuarioResponse(
                usuario.getId(),
                usuario.getNomeCompleto(),
                usuario.getEmail(),
                usuario.getApelido(),
                usuario.getDataNascimento(),
                usuario.getUrlImagem()
        );

        List<ListarSolicitacoesResponse> solicitacoes = listarSolicitacoesService.listarSolicitacoesPendentes(idUsuarioLogado);

        return new DashboardResponse(usuarioDTO, solicitacoes);
    }


}
