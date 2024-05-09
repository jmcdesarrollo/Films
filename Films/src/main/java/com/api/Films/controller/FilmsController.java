package com.api.Films.controller;

import com.api.Films.DTO.FilmsDto;
import com.api.Films.entity.Films;
import com.api.Films.repository.FilmsRepository;
import com.api.Films.service.FilmsService;
import com.api.Films.service.ReviewService;
import com.api.Films.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.Arrays;


@Controller // Anotación que indica que esta clase es un controlador de Spring MVC
@RequestMapping("/films") // Anotación que mapea todas las solicitudes HTTP que comienzan con "/films" a este controlador
public class FilmsController {

    // Campos para los servicios necesarios
    private final FilmsService filmsService; // Servicio para operaciones relacionadas con películas
    private final ReviewService reviewService; // Servicio para operaciones relacionadas con revisiones de películas

    private final ScoreService scoreService; // Servicio para operaciones relacionadas con puntuaciones de películas
    private final FilmsRepository filmsRepository; // Repositorio para operaciónes relacionadas con guardar los films

    // Constructor que utiliza la inyección de dependencias de Spring para inicializar los servicios necesarios
    @Autowired
    public FilmsController(FilmsService filmsService,
                           ReviewService reviewService,
                           ScoreService scoreService,
                           FilmsRepository filmsRepository) {
        this.filmsService = filmsService; // Inicializa el servicio de películas
        this.reviewService = reviewService; // Inicializa el servicio de revisión
        this.scoreService = scoreService; // Inicializa el servicio de puntuación
        this.filmsRepository = filmsRepository; // Inicializa el repository para guardar los films
    }

    @GetMapping("/new-film")
    public String registration(Model model) {
        //  Obtener la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //  Verificar si la autenticación no es nula y si está autenticada
        if (authentication != null && authentication.isAuthenticated()) {
            //  Obtener el primer rol del usuario, o establecer uno predeterminado si no tiene ninguno
            String role = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("ROLE_USER"); // Definir un rol predeterminado si no se encuentra ninguno

            //  Verificar si el usuario tiene el rol de administrador
            boolean isAdmin = role.equals("ROLE_ADMIN");
            model.addAttribute("admin", isAdmin); //  Agregar el atributo "admin" al modelo
        } else {
            //  Manejar el caso de que no haya autenticación o no esté autenticado
            model.addAttribute("admin", false); // Si no está autenticado, se establece como falso
        }

        //  Devolver la vista correspondiente
        return "films/new-film";

        /* La función registration se encarga de verificar si el usuario autenticado tiene el rol de administrador.
         Para ello, obtiene la autenticación del contexto de seguridad y verifica el rol del usuario. Luego, agrega
          un atributo al modelo indicando si el usuario es administrador o no. Finalmente, devuelve la vista
           correspondiente para que se muestre al usuario.*/
    }

    @PostMapping("save")
    public String saveFilms(FilmsDto filmsDto, @RequestParam("poster") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            // Validar la entrada de datos
            if (filmsDto.getTitle() == null || filmsDto.getTitle().isEmpty()) {
                throw new IllegalArgumentException("El título de la película es obligatorio.");
            }
            if (filmsDto.getYear() <= 0) {
                throw new IllegalArgumentException("El año de la película debe ser un valor positivo.");
            }
            // Más validaciones de datos aquí...

            // Procesar el archivo de póster
            if (file == null || file.isEmpty()) {
                throw new IllegalArgumentException("El archivo de póster es obligatorio.");
            }
            if (!Arrays.asList("image/jpeg", "image/png").contains(file.getContentType())) {
                throw new IllegalArgumentException("El formato del archivo de póster no es compatible. Debe ser JPEG o PNG.");
            }
            // Más validaciones del archivo aquí...

            // Guardar la película en la base de datos
            Films films = new Films();
            films.setTitle(filmsDto.getTitle());
            films.setYear(filmsDto.getYear());
            films.setDuration(filmsDto.getDuration());
            // Más configuración de la película aquí...
            filmsRepository.save(films);

            // Redirigir con un mensaje de éxito
            redirectAttributes.addFlashAttribute("successMessage", "Película guardada correctamente.");
            return "redirect:/films";
        } catch (IllegalArgumentException e) {
            // Capturar y manejar errores de validación de entrada
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/films/new";
        } catch (Exception e) {
            // Capturar y manejar otros errores
            redirectAttributes.addFlashAttribute("errorMessage", "Se produjo un error al guardar la película.");
            return "redirect:/films/new";
        }
        /* Este método saveFilms maneja la solicitud POST para guardar una nueva película en la base de datos.
        1.Validación de Entrada de Datos: Se realizan varias validaciones para asegurarse de que los datos proporcionados para la película sean válidos.
        Esto incluye verificar si el título de la película está presente y si el año es un valor positivo.
        2.Procesamiento del Archivo de Póster: Se verifica si se proporciona un archivo de póster y si está en un formato compatible (JPEG o PNG).
        3.Guardado en la Base de Datos: Se crea un objeto Films con los datos proporcionados y se guarda en la base de datos utilizando el repositorio filmsRepository.
        4.Redirección con Mensajes de Éxito o Error: Después de guardar la película, se redirige al usuario a la página de películas con un mensaje de éxito si la
        operación fue exitosa. Si hay algún error durante el proceso de guardado o validación, se redirige de nuevo a la página de creación de películas con un
        mensaje de error adecuado.
        Este método maneja errores de validación de entrada y otros errores generales, proporcionando mensajes descriptivos para guiar al usuario a través del
        proceso de creación de películas.
        */
    }


}

