package br.com.instagatos.instagatos.repository;

import br.com.instagatos.instagatos.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
