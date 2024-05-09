package com.api.Films.entity;

import com.api.Films.entity.enums.TypePersonEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name ="Person")

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private TypePersonEnum type;

    @OneToMany(mappedBy = "director")
    private Set<Films> filmsDirector;

    @ManyToMany(mappedBy = "musican")
    private Set <Films> filmsMusican;

    @ManyToMany(mappedBy = "screenwriter")
    private Set<Films> filmsScreenwriter;

    @ManyToMany(mappedBy = "photgrapher")
    private Set <Films> filmsPhotgrapher;

    @ManyToMany(mappedBy = "actor")
    private Set <Films> filmsActor;
}
