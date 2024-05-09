package com.api.Films.repository;

import com.api.Films.entity.Films;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmsRepository extends JpaRepository<Films, Long> {
    Optional<Films> findById(Long id);

    Films getFilmsById(Long id);
}
/* Con esta definición, puedes utilizar métodos como findById para buscar películas por su ID y save para guardar nuevas
películas o actualizar las existentes.
 */
