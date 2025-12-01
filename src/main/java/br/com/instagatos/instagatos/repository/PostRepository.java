package br.com.instagatos.instagatos.repository;

import br.com.instagatos.instagatos.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("""
SELECT p FROM Post p
WHERE 
    p.autor.id = :usuarioId
    OR (
        p.autor.id IN (
            SELECT a.solicitado.id FROM Amizade a 
            WHERE a.solicitante.id = :usuarioId AND a.status = 'ACEITO'
            UNION
            SELECT a.solicitante.id FROM Amizade a 
            WHERE a.solicitado.id = :usuarioId AND a.status = 'ACEITO'
        )
    )
ORDER BY p.dataPostagem DESC
""")
    Page<Post> listarPostsUsuarioEAmigos(@Param("usuarioId") Long usuarioId, Pageable pageable);


}
