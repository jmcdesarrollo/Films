package com.api.Films.repository;

import com.api.Films.entity.Films;
import com.api.Films.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByFilms(Films films);
}
