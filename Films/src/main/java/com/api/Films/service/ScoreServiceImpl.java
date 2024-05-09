package com.api.Films.service;

import com.api.Films.entity.Films;
import com.api.Films.entity.Score;
import com.api.Films.entity.User;
import com.api.Films.repository.ScoreRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;

    @Autowired
    HttpSession session;


    @Autowired
    UserService userService;

    public ScoreServiceImpl(ScoreRepository scoreRepository) { this.scoreRepository = scoreRepository; }

    @Override
    public void saveScore(User user, Films films, int scoreValue) {
        List<Score> existingScore = scoreRepository.findByUserAndFilms(user, films);
        if (!existingScore.isEmpty()) {
            throw new IllegalStateException("El usuario ya ha puntuado este film.");
        }

        Score score = new Score();
        score.setUser(user);
        score.setFilms(films);
        score.setValue(scoreValue);

        scoreRepository.save(score);
    }

    @Override
    public boolean isScoreDone(Films films) {
        String username = (String) session.getAttribute("username");
        Optional<User> user = userService.findByUsername(username);
        List<Score> score = scoreRepository.findByUserAndFilms(user.get(),films);
        return !score.isEmpty();
    }
}
