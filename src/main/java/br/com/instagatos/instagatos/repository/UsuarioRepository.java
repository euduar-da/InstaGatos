package br.com.instagatos.instagatos.repository;

import br.com.instagatos.instagatos.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    @Query("""
    SELECT u FROM Usuario u
    WHERE 
        (LOWER(u.nomeCompleto) LIKE LOWER(CONCAT('%', :termo, '%'))
        OR LOWER(u.email) LIKE LOWER(CONCAT('%', :termo, '%')))
        AND u.id <> :idUsuario
        AND (COALESCE(:idsExcluidos, NULL) IS NULL OR u.id NOT IN :idsExcluidos)
""")
    List<Usuario> buscarPorNomeOuEmailExcluindo(
            @Param("termo") String termo,
            @Param("idUsuario") Long idUsuario,
            @Param("idsExcluidos") List<Long> idsExcluidos);

    @Query("""
    SELECT u FROM Usuario u
    WHERE u.id IN :ids
      AND (
          :termo IS NULL
          OR LOWER(u.nomeCompleto) LIKE LOWER(CONCAT('%', :termo, '%'))
          OR LOWER(u.email) LIKE LOWER(CONCAT('%', :termo, '%'))
      )
""")
    List<Usuario> buscarPorIdsENomeOuEmail(@Param("ids") List<Long> ids, @Param("termo") String termo);

}
