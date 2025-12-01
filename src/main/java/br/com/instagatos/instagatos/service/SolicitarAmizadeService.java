package br.com.instagatos.instagatos.service;

import br.com.instagatos.instagatos.controller.response.SolicitarAmizadeResponse;
import br.com.instagatos.instagatos.domain.Amizade;
import br.com.instagatos.instagatos.domain.Usuario;
import br.com.instagatos.instagatos.domain.enums.Status;
import br.com.instagatos.instagatos.repository.AmizadeRepository;
import br.com.instagatos.instagatos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.instagatos.instagatos.mapper.AmizadeMapper.toResponse;

@Service
public class SolicitarAmizadeService {

    private final UsuarioRepository usuarioRepository;
    private final AmizadeRepository amizadeRepository;

    public SolicitarAmizadeService(UsuarioRepository usuarioRepository, AmizadeRepository amizadeRepository) {
        this.usuarioRepository = usuarioRepository;
        this.amizadeRepository = amizadeRepository;
    }

    public SolicitarAmizadeResponse solicitarAmizade(Long solicitanteId, Long solicitadoId) {
        if (solicitanteId.equals(solicitadoId)) {
            throw new IllegalArgumentException("Você não pode enviar solicitação de amizade para si mesmo.");
        }

        Optional<Amizade> existente = amizadeRepository.findEntreUsuarios(solicitanteId, solicitadoId);
        if (existente.isPresent()) {
            throw new IllegalArgumentException("Já existe uma solicitação ou amizade entre esses usuários.");
        }

        Usuario solicitante = usuarioRepository.findById(solicitanteId)
                .orElseThrow(() -> new RuntimeException("Usuário solicitante não encontrado."));
        Usuario solicitado = usuarioRepository.findById(solicitadoId)
                .orElseThrow(() -> new RuntimeException("Usuário solicitado não encontrado."));

        Amizade amizade = new Amizade();
        amizade.setSolicitante(solicitante);
        amizade.setSolicitado(solicitado);
        amizade.setStatus(Status.PENDENTE);

        Amizade salva = amizadeRepository.save(amizade);

        return toResponse(salva);
    }


}
