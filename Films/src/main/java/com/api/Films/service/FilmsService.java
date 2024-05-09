package com.api.Films.service;

import com.api.Films.entity.Films;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public interface FilmsService {
    ArrayList<Films> getFilms();

    Films saveFilms(Films films);

    Optional<Films> getById(Long id);

    Films getFilmsById(Long id);

    Films updateById(Long id);
}
