package com.api.Films.controller;

import com.api.Films.DTO.ReviewRequest;
import com.api.Films.entity.Films;
import com.api.Films.entity.User;
import com.api.Films.service.FilmsService;
import com.api.Films.service.ReviewService;
import com.api.Films.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final UserService userService;
    private final FilmsService filmsService;
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(UserService userService, FilmsService filmsService, ReviewService reviewService) {
        this.userService = userService;
        this.filmsService = filmsService;
        this.reviewService = reviewService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveReview(ReviewRequest reviewRequest, Authentication authentication) {
        // Verificar si el usuario está autenticado
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado");
        }

        // Obtener el nombre de usuario del usuario autenticado
        String username = authentication.getName();

        // Buscar el usuario en la base de datos
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
        User user = userOptional.get();

        // Obtener la película correspondiente a partir del ID proporcionado en la solicitud
        Films films = filmsService.getFilmsById(reviewRequest.getFilmsId());
        if (films == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Película no encontrada");
        }

        // Guardar la crítica de la película
        try {
            reviewService.saveReview(reviewRequest, films, user);
            return ResponseEntity.ok("Crítica guardada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la crítica");
        }
    }
}


    /* ReviewController maneja las solicitudes POST para guardar críticas de películas.
       Se verifica si el usuario está autenticado mediante la información proporcionada por Spring Security.
       Se obtiene el nombre de usuario del usuario autenticado.
       Se busca el usuario en la base de datos utilizando el servicio UserService.
       Se obtiene la película correspondiente a partir del ID proporcionado en la solicitud utilizando el servicio FilmsService.
       Se intenta guardar la crítica de la película utilizando el servicio ReviewService.
       Si la operación de guardado es exitosa, se devuelve una respuesta exitosa con el mensaje "Crítica guardada correctamente".
       Si ocurre algún error durante el proceso de guardado, se captura la excepción y se devuelve una respuesta de error con un mensaje adecuado.
       Este controlador garantiza que las críticas de películas se guarden correctamente en la base de datos, verificando la autenticación
       del usuario y la existencia de la película antes de realizar la operación de guardado.
       */


