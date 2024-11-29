package br.ueg.acervodigital.entities;

import br.ueg.acervodigitalarquitetura.domain.GenericModel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@ToString
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class User implements GenericModel<Long> {
    public final static String SEQUENCE_NAME = "user_sequence";

    @Id
    @SequenceGenerator(
            name=SEQUENCE_NAME,
            sequenceName = SEQUENCE_NAME + "_bd",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = SEQUENCE_NAME
    )
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name",  nullable = false)
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "function")
    private String function;

    @Column(name = "enabled")
    private boolean enabled;

}
