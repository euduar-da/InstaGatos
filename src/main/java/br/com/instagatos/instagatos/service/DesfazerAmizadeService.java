package br.com.instagatos.instagatos.service;

import br.com.instagatos.instagatos.domain.Amizade;
import br.com.instagatos.instagatos.repository.AmizadeRepository;
import org.springframework.stereotype.Service;

@Service
public class DesfazerAmizadeService {
    private final AmizadeRepository amizadeRepository;

    public DesfazerAmizadeService(AmizadeRepository amizadeRepository) {
        this.amizadeRepository = amizadeRepository;
    }

    public void desfazerAmizade(Long usuarioId, Long amigoId) {
        Amizade amizade = amizadeRepository.findEntreUsuarios(usuarioId, amigoId)
                .orElseThrow(() -> new RuntimeException("Amizade não encontrada."));

        amizadeRepository.delete(amizade);
    }
}
