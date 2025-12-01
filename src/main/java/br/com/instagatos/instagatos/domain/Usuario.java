package br.com.instagatos.instagatos.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String nomeCompleto;
    private String email;
    private String apelido; //opcional
    private LocalDate dataNascimento;
    private String senha;
    private String urlImagem; //opcional


    @OneToMany(mappedBy = "autor")
    private List<Post> posts;

    @OneToMany(mappedBy = "usuario")
    private List<Curtida> curtidas;

    @OneToMany(mappedBy = "autor")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "solicitante")
    private List<Amizade> amizadesEnviadas;

    @OneToMany(mappedBy = "solicitado")
    private List<Amizade> amizadesRecebidas;

}
