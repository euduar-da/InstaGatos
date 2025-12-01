package br.com.instagatos.instagatos.controller;

import br.com.instagatos.instagatos.controller.request.SolicitarAmizadeRequest;
import br.com.instagatos.instagatos.controller.response.BuscarAmigosResponse;
import br.com.instagatos.instagatos.controller.response.ListarSolicitacoesResponse;
import br.com.instagatos.instagatos.controller.response.SolicitarAmizadeResponse;
import br.com.instagatos.instagatos.domain.Usuario;
import br.com.instagatos.instagatos.repository.UsuarioRepository;
import br.com.instagatos.instagatos.service.DesfazerAmizadeService;
import br.com.instagatos.instagatos.service.ListarAmigosService;
import br.com.instagatos.instagatos.service.ListarSolicitacoesService;
import br.com.instagatos.instagatos.service.SolicitarAmizadeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/amizades")
public class AmizadeController {

    private final UsuarioRepository usuarioRepository;
    private final ListarSolicitacoesService listarSolicitacoesService;
    private final SolicitarAmizadeService solicitarAmizadeService;
    private final ListarAmigosService listarAmigosService;
    private final DesfazerAmizadeService desfazerAmizadeService;


    @GetMapping("/solicitacoes/recebidas")
    public ResponseEntity<List<ListarSolicitacoesResponse>> listarSolicitacoesRecebidas(@AuthenticationPrincipal Jwt jwt) {
        Long usuarioId = Long.parseLong(jwt.getSubject());
        Usuario usuarioLogado = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        List<ListarSolicitacoesResponse> solicitacoes = listarSolicitacoesService.listarSolicitacoesPendentes(usuarioLogado.getId());

        return ResponseEntity.ok(solicitacoes);
    }


    @PostMapping("/solicitar-amizade")
    public ResponseEntity<SolicitarAmizadeResponse> solicitarAmizade(
            @RequestBody SolicitarAmizadeRequest request,
            @AuthenticationPrincipal Jwt jwt) {

        Long solicitanteId = Long.parseLong(jwt.getSubject());

        SolicitarAmizadeResponse response =
                solicitarAmizadeService.solicitarAmizade(solicitanteId, request.getSolicitadoId());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<BuscarAmigosResponse>> listarAmigos(
            @RequestParam(required = false) String termo,
            @AuthenticationPrincipal Jwt jwt) {

        Long usuarioId = Long.parseLong(jwt.getSubject());
        List<BuscarAmigosResponse> amigos = listarAmigosService.listarAmigos(usuarioId, termo);
        return ResponseEntity.ok(amigos);
    }

    @DeleteMapping("/{amigoId}")
    public ResponseEntity<Void> desfazerAmizade(
            @PathVariable Long amigoId,
            @AuthenticationPrincipal Jwt jwt) {

        Long usuarioId = Long.parseLong(jwt.getSubject());
        desfazerAmizadeService.desfazerAmizade(usuarioId, amigoId);
        return ResponseEntity.noContent().build();
    }
}
