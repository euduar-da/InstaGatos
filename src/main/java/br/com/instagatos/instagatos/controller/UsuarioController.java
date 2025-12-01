package br.com.instagatos.instagatos.controller;

import br.com.instagatos.instagatos.controller.request.BuscarUsuarioRequest;
import br.com.instagatos.instagatos.controller.request.EditarPerfilRequest;
import br.com.instagatos.instagatos.controller.request.UsuarioRequest;
import br.com.instagatos.instagatos.controller.response.BuscarUsuarioResponse;
import br.com.instagatos.instagatos.controller.response.UsuarioResponse;
import br.com.instagatos.instagatos.domain.Usuario;
import br.com.instagatos.instagatos.repository.UsuarioRepository;
import br.com.instagatos.instagatos.service.BuscarUsuariosService;
import br.com.instagatos.instagatos.service.EditarPerfilService;
import br.com.instagatos.instagatos.service.IncluirUsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final IncluirUsuarioService incluirUsuarioService;
    private final EditarPerfilService editarPerfilService;
    private final BuscarUsuariosService buscarUsuariosService;

    public UsuarioController(UsuarioRepository usuarioRepository, IncluirUsuarioService incluirUsuarioService, EditarPerfilService editarPerfilService, BuscarUsuariosService buscarUsuariosService) {
        this.usuarioRepository = usuarioRepository;
        this.incluirUsuarioService = incluirUsuarioService;
        this.editarPerfilService = editarPerfilService;
        this.buscarUsuariosService = buscarUsuariosService;
    }

    @PostMapping
    public UsuarioResponse incluir(@Valid @RequestBody UsuarioRequest request) {
        return incluirUsuarioService.incluir(request);
    }


    @PatchMapping("/meu-perfil")
    public ResponseEntity<UsuarioResponse> editarMeuPerfil(@AuthenticationPrincipal Jwt jwt, @Valid @RequestBody EditarPerfilRequest request) {

        Long usuarioId = Long.parseLong(jwt.getSubject());
        Usuario usuarioLogado = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        UsuarioResponse response = editarPerfilService.editarMeuPerfil(usuarioLogado, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/buscar")
    public ResponseEntity<List<BuscarUsuarioResponse>> buscarUsuarios(
            @RequestBody BuscarUsuarioRequest request,
            @AuthenticationPrincipal Jwt jwt) {

        Long usuarioId = Long.parseLong(jwt.getSubject());
        List<BuscarUsuarioResponse> resultados = buscarUsuariosService.buscarUsuarios(usuarioId, request.getTermo());
        return ResponseEntity.ok(resultados);
    }
}
