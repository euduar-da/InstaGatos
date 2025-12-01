package br.com.instagatos.instagatos.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static jakarta.persistence.GenerationType.IDENTITY;


@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
public class Curtida {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Curtida(Post post, Usuario usuario) {
        this.post = post;
        this.usuario = usuario;
    }

    public Curtida() {

    }
}
