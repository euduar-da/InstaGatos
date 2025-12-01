package br.com.instagatos.instagatos.domain;

import br.com.instagatos.instagatos.domain.enums.Status;
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
public class Amizade {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "solicitante_id")
    private Usuario solicitante;

    @ManyToOne
    @JoinColumn(name = "solicitado_id")
    private Usuario solicitado;

    @Enumerated(EnumType.STRING)
    private Status status;


}
