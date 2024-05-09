package com.api.Films.service;

import com.api.Films.entity.Films;
import com.api.Films.repository.FilmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class FilmsServiceImpl implements FilmsService{
    @Autowired
    FilmsRepository filmsRepository;

    // Método para obtener todos los films
    public ArrayList<Films> getFilms() {
        return (ArrayList<Films>) filmsRepository.findAll();
    }

    // Método para guardar un nuevo film
    public Films saveFilms(Films films) {
        return filmsRepository.save(films);
    }

    // Método para buscar un film por su ID
    public Optional<Films> getById(Long id) {
        return filmsRepository.findById(id);
    }

    @Override
    public Films getFilmsById(Long id) {
        return filmsRepository.getFilmsById(id);
    }

    @Override
    public Films updateById(Long id) {
        return filmsRepository.getReferenceById(id);
    }

    // Método para actualizar un film por su ID
    public Films updateById(Films request, Long id) {
        Optional<Films> optionalFilms = filmsRepository.findById(id);

        // Verificamos si el film fue encontrado
        if (optionalFilms.isPresent()) {
            // Si el film fue encontrado, obtenemos el film y la asignamos a la variable film
            Films films = optionalFilms.get();
            // Actualizamos los campos de la persona con los valores proporcionados en la solicitud
            films.setTitle(request.getTitle()); // : Actualiza el título del objeto Films (title)
            films.setYear(request.getYear()); // : Actualiza el año del objeto Films (year)
            films.setSypnosis(request.getSypnosis()); // : Actualiza la sinopsis del objeto Films (sypnosis)

            return filmsRepository.save(films);
        } else {
            // Si el film no fue encontrado, lanzamos una excepción o manejamos un error según sea necesario
            throw new RuntimeException("La pelicula con el Id " + id + " no fue encontrada.");
        }
    }

    /* primero utilizamos findById(id) para buscar un film por su ID
     en el repositorio de personas (filmsRepository). Luego, verificamos si el
      film fue encontrado utilizando isPresent(). Si el film está presente,
       utilizamos get() para obtener el film y la asignamos a la variable
        films. Finalmente, podemos hacer lo que necesitemos con el film
        encontrado. Si el film no está presente, imprimimos un mensaje indicando
        que no se encontró ningún film con el ID proporcionado.*/
}

