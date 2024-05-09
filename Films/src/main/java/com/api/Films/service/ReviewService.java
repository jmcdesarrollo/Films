package com.api.Films.service;

import com.api.Films.DTO.ReviewRequest;
import com.api.Films.entity.Films;
import com.api.Films.entity.Review;
import com.api.Films.entity.User;

import java.util.List;

public interface ReviewService {

    /**
     * Guarda una revisión asociada a una película y un usuario.
     * @param reviewRequest La solicitud de revisión.
     * @param films La película asociada a la revisión.
     * @param user El usuario que realiza la revisión.
     */
    void saveReview(ReviewRequest reviewRequest, Films films, User user);

    /**
     * Obtiene todas las revisiones asociadas a una película.
     * @param films La película de la que se desean obtener las revisiones.
     * @return Una lista de revisiones asociadas a la película.
     */
    List<Review> getReviewsByFilm(Films films);
}
/* Define una interfaz llamada ReviewService que proporciona métodos para guardar revisiones y obtener revisiones por
película. La interfaz incluye los siguientes métodos:

saveReview: Este método toma una solicitud de revisión, una película y un usuario, y guarda la revisión asociada en el
repositorio de revisiones.

getReviewsByFilm: Este método toma una película como parámetro y devuelve una lista de revisiones asociadas a esa
película desde el repositorio de revisiones.*/
