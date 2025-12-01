package br.com.instagatos.instagatos.repository;

import br.com.instagatos.instagatos.domain.Curtida;
import br.com.instagatos.instagatos.domain.Post;
import br.com.instagatos.instagatos.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CurtidaRepository extends JpaRepository<Curtida, Long> {
    Optional<Curtida> findByUsuarioAndPost(Usuario usuario, Post post);

}
