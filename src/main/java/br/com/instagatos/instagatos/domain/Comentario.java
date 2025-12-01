package br.com.instagatos.instagatos.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
public class Comentario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "texto_comentario")
    private String textoComentario;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
