package com.api.Films.repository;

import com.api.Films.entity.Films;
import com.api.Films.entity.Score;
import com.api.Films.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByUserAndFilms(User user, Films films);

    List<Score> findByFilms(Films films);
}
