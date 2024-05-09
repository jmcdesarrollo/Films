package com.api.Films.service;

import com.api.Films.DTO.ReviewRequest;
import com.api.Films.entity.Films;
import com.api.Films.entity.Review;
import com.api.Films.entity.User;
import com.api.Films.repository.ReviewRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void saveReview(ReviewRequest reviewRequest, Films films, User user) {
        // Validar que los parámetros no sean nulos
        Objects.requireNonNull(reviewRequest, "ReviewRequest no puede ser nulo");
        Objects.requireNonNull(films, "Film no puede ser nulo");
        Objects.requireNonNull(user, "User no puede ser nulo");

        // Verificar que el título y la revisión no estén vacíos
        if (StringUtils.isBlank(reviewRequest.getTitle())) {
            throw new IllegalArgumentException("El título de la revisión no puede estar vacío");
        }
        if (StringUtils.isBlank(reviewRequest.getReview())) {
            throw new IllegalArgumentException("El contenido de la revisión no puede estar vacío");
        }

        // Construir la revisión
        Review review = new Review();
        review.setUser(user);
        review.setFilms(films);
        review.setTitle(reviewRequest.getTitle());
        review.setTextReview(reviewRequest.getReview());

        // Guardar la revisión en la base de datos
        reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsByFilm(Films films) {
        // Validar que el film no sea nulo
        Objects.requireNonNull(films, "Film no puede ser nulo");

        // Obtener las revisiones asociadas a la película
        return reviewRepository.findByFilms(films);
    }
}

