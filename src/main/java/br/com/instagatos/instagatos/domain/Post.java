package br.com.instagatos.instagatos.domain;

import br.com.instagatos.instagatos.domain.enums.Permissao;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;


@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String textoPost;

    @Enumerated(EnumType.STRING)
    private Permissao permissaoVisualizacao;

    private String urlImagemPost;

    private LocalDateTime dataPostagem;


    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario autor;

    @OneToMany(mappedBy = "post")
    private List<Curtida> curtidas;

    @OneToMany(mappedBy = "post")
    private List<Comentario> comentarios;
}
