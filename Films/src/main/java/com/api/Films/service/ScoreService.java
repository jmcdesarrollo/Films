package com.api.Films.service;

import com.api.Films.entity.Films;
import com.api.Films.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface ScoreService {
    void saveScore(User user, Films films, int scoreValue);
    boolean isScoreDone (Films films);
}
