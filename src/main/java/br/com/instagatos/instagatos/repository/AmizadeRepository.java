package br.com.instagatos.instagatos.repository;

import br.com.instagatos.instagatos.controller.response.ListarSolicitacoesResponse;
import br.com.instagatos.instagatos.domain.Amizade;
import br.com.instagatos.instagatos.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;


public interface AmizadeRepository extends JpaRepository<Amizade, Long> {

    @Query("""
        SELECT new br.com.instagatos.instagatos.controller.response.ListarSolicitacoesResponse(
            a.solicitante.id,
            a.solicitante.nomeCompleto,
            a.solicitante.urlImagem
        )
        FROM Amizade a
        WHERE a.solicitado.id = :usuarioId AND a.status = 'PENDENTE'
    """)
    List<ListarSolicitacoesResponse> findSolicitacoesPendentes(Long usuarioId);

    @Query("""
        SELECT CASE 
                 WHEN a.solicitante.id = :usuarioId THEN a.solicitado.id
                 ELSE a.solicitante.id
               END
        FROM Amizade a
        WHERE (a.solicitante.id = :usuarioId OR a.solicitado.id = :usuarioId)
          AND a.status = 'ACEITO'
    """)
    List<Long> findIdsAmigosDoUsuario(@Param("usuarioId") Long usuarioId);

    @Query("""
        SELECT a FROM Amizade a
        WHERE 
            (a.solicitante.id = :usuarioId1 AND a.solicitado.id = :usuarioId2)
            OR (a.solicitante.id = :usuarioId2 AND a.solicitado.id = :usuarioId1)
    """)
    Optional<Amizade> findEntreUsuarios(@Param("usuarioId1") Long usuarioId1,
                                        @Param("usuarioId2") Long usuarioId2);
}