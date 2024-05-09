package com.api.Films.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name ="Films")

public class Films {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer year;
    private double duration;
    private String sypnosis;

    // la variable  poster  contendrá el valor de la propiedad  ruta.imagenes  definida en el archivo  application.properties
    @Value("${ruta.images") // accedo a la propiedas a través de spring con @Value y agrego la ruta de donde irá la imagen
    private String rutaImages;

    @Transient
    private MultipartFile poster;

    private boolean migrate;
    private LocalDate dateMigrate;

    @OneToMany(mappedBy = "films")
    private Set<Score> score;

    @OneToMany(mappedBy = "films")
    private Set<Review> reviews;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Person director;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Person> musican;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Person> screenwriter;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Person> photgrapher;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Person> actor;
}





