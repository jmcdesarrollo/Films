package com.api.Films.controller;

import com.api.Films.DTO.ScoreRequest;
import com.api.Films.entity.Films;
import com.api.Films.entity.User;
import com.api.Films.service.FilmsService;
import com.api.Films.service.ScoreService;
import com.api.Films.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/scores")
public class ScoreController {
    private final ScoreService scoreService;
    private final UserService userService;
    private final FilmsService filmsService;

    public ScoreController(ScoreService scoreService, UserService userService, FilmsService filmsService) {
        this.scoreService = scoreService;
        this.userService = userService;
        this.filmsService = filmsService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveScore(ScoreRequest scoreRequest, Authentication authentication) {
        // Obtener el nombre de usuario autenticado
        String username = authentication.getName();

        // Buscar el usuario por nombre de usuario
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
        User user = userOptional.get();

        // Obtener la película por su ID
        Films films = filmsService.getFilmsById(scoreRequest.getFilmsId());
        if (films == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Película no encontrada");
        }

        // Guardar la puntuación
        scoreService.saveScore(user, films, scoreRequest.getScoreValue());

        return ResponseEntity.ok("Puntuación guardada correctamente");
    }
}

