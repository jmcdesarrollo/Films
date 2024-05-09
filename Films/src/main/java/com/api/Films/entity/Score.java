package com.api.Films.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name ="Score")

public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer value;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Films films;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
