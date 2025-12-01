package br.com.instagatos.instagatos.controller;

import br.com.instagatos.instagatos.controller.response.DashboardResponse;
import br.com.instagatos.instagatos.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@AllArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardResponse> getDashboard(@AuthenticationPrincipal Jwt jwt) {

        Long idUsuarioLogado = Long.parseLong(jwt.getSubject());

        DashboardResponse response = dashboardService.montarDashboard(idUsuarioLogado);

        return ResponseEntity.ok(response);
    }
}
