package br.com.instagatos.instagatos.controller;

import br.com.instagatos.instagatos.controller.request.AdicionarComentarioRequest;
import br.com.instagatos.instagatos.controller.request.AlterarPermissaoRequest;
import br.com.instagatos.instagatos.controller.request.IncluirPostRequest;
import br.com.instagatos.instagatos.controller.response.AdicionarComentarioResponse;
import br.com.instagatos.instagatos.controller.response.AlterarPermissaoResponse;
import br.com.instagatos.instagatos.controller.response.IncluirPostResponse;
import br.com.instagatos.instagatos.domain.Comentario;
import br.com.instagatos.instagatos.domain.Post;
import br.com.instagatos.instagatos.domain.Usuario;
import br.com.instagatos.instagatos.repository.UsuarioRepository;
import br.com.instagatos.instagatos.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private final IncluirPostService incluirPostService;
    private final UsuarioRepository usuarioRepository;
    private final ListarPostsService listarPostsService;
    private final AdicionarCurtidaService adicionarCurtidaService;
    private final RemoverCurtidaService removerCurtidaService;
    private final AdicionarComentarioService adicionarComentarioService;
    private final AlterarPermissaoService alterarPermissaoService;


    @PostMapping
    public ResponseEntity<IncluirPostResponse> criarPost(@RequestBody IncluirPostRequest request, @AuthenticationPrincipal Jwt jwt) {
        Long usuarioId = Long.parseLong(jwt.getSubject());

        Usuario usuarioLogado = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        IncluirPostResponse response = incluirPostService.criar(usuarioLogado, request);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<IncluirPostResponse>> listarPosts(@AuthenticationPrincipal Jwt jwt, @PageableDefault(size = 10, sort = "dataPostagem", direction = Sort.Direction.DESC) Pageable pageable) {
        Long usuarioId = Long.parseLong(jwt.getSubject());
        usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        Page<IncluirPostResponse> posts = listarPostsService.listarFeed(usuarioId, pageable);
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/{postId}/curtir")
    public ResponseEntity<Void> curtirPost(@PathVariable Long postId, @AuthenticationPrincipal Jwt jwt) {
        Long usuarioId = Long.parseLong(jwt.getSubject());
        adicionarCurtidaService.curtirPost(usuarioId, postId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}/curtir")
    public ResponseEntity<Void> removerCurtidaPost(@PathVariable Long postId, @AuthenticationPrincipal Jwt jwt) {
        Long usuarioId = Long.parseLong(jwt.getSubject());
        removerCurtidaService.descurtirPost(usuarioId, postId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{postId}/comentar")
    public ResponseEntity<AdicionarComentarioResponse> comentarPost(@PathVariable Long postId, @RequestBody AdicionarComentarioRequest request, @AuthenticationPrincipal Jwt jwt) {
        Long usuarioId = Long.parseLong(jwt.getSubject());
        Comentario comentario = adicionarComentarioService.adicionarComentario(usuarioId, postId, request.getTextoComentario());
        AdicionarComentarioResponse response = new AdicionarComentarioResponse(comentario.getId(), comentario.getTextoComentario());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/permissao")
    public ResponseEntity<AlterarPermissaoResponse> alterarPermissao(
            @PathVariable Long id,
            @RequestBody AlterarPermissaoRequest request,
            @AuthenticationPrincipal Jwt jwt) {

        Long usuarioId = Long.parseLong(jwt.getSubject());
        Post postAtualizado = alterarPermissaoService.alterarPermissao(id, usuarioId, request.getPermissao());

        AlterarPermissaoResponse response = new AlterarPermissaoResponse(
                postAtualizado.getId(),
                postAtualizado.getPermissaoVisualizacao()
        );

        return ResponseEntity.ok(response);
    }

}