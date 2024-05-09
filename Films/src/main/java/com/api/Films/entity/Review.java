package com.api.Films.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name ="Review")

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String textReview;
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Films films;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
