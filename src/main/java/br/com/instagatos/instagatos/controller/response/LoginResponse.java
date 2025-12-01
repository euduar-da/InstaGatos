package br.com.instagatos.instagatos.controller.response;

public record LoginResponse(String accessToken, Long expiresIn) {
}
