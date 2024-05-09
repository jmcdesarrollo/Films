package com.api.Films.controller;

import com.api.Films.DTO.PersonRequest;
import com.api.Films.entity.Person;
import com.api.Films.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/new-person")
    public String registration(Model model) {
        // Obtener la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Verificar si el usuario tiene el rol de administrador
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        // Agregar el atributo "admin" al modelo
        model.addAttribute("admin", isAdmin);

        return "person/new-person";
    }
    /* El método registration del controlador PersonController maneja las solicitudes GET para la creación de nuevas personas en la aplicación.
       Dentro del método, se obtiene la autenticación actual del contexto de seguridad utilizando SecurityContextHolder.getContext().getAuthentication().
       Esta autenticación contiene información sobre el usuario actual que ha iniciado sesión en la aplicación.
       Luego, se accede a la lista de autoridades (roles) asociados al usuario autenticado mediante authentication.getAuthorities().
       Estas autoridades representan los roles que el usuario tiene en la aplicación.
       Dado que getAuthorities() devuelve una colección de tipo Collection<? extends GrantedAuthority>, se convierte esta colección en un array de
       GrantedAuthority para facilitar el acceso a los elementos individuales.
       A continuación, se crea una lista de GrantedAuthority a partir del array recién creado utilizando Arrays.asList(authorityArray).
       Esto se hace para poder acceder y manipular los elementos de manera más conveniente.
       Se verifica si la lista de autoridades no está vacía y se obtiene el primer rol del usuario utilizando grantedAuthorities.get(0).getAuthority().
       Esto se hace para determinar si el usuario tiene el rol de administrador.
       El resultado de la verificación se almacena en una variable booleana isAdmin, que indica si el usuario es un administrador o no.
       Finalmente, se agrega un atributo "admin" al modelo, cuyo valor es el resultado de la verificación anterior. Este atributo se utilizará
       en la vista para realizar lógica condicional basada en el rol del usuario.
       La vista "person/new-person" se devuelve junto con el modelo actualizado, lo que permite que la interfaz de usuario se ajuste según el rol del usuario.
       */

    @PostMapping("/save-person")
    public ResponseEntity<String> registration(@Validated PersonRequest personRequest, BindingResult bindingResult) {
        // Verificar si hay errores de validación en la entrada
        if (bindingResult.hasErrors()) {
            // Manejar los errores de validación y devolver un mensaje de error adecuado
            return ResponseEntity.badRequest().body("Los datos proporcionados son inválidos. Por favor, revise los campos.");
        }

        // Crear una nueva persona a partir de los datos de la solicitud
        Person person = new Person();
        person.setName(personRequest.getName());
        person.setSurname(personRequest.getSurname());
        person.setType(personRequest.getType());

        try {
            // Intentar crear la persona utilizando el servicio correspondiente
            personService.createPerson(person);
            // Devolver una respuesta exitosa si la creación fue exitosa
            return ResponseEntity.ok("La persona se ha creado exitosamente.");
        } catch (Exception e) {
            /* Capturar cualquier excepción que ocurra durante la creación de la persona
             y devolver un mensaje de error apropiado
             */
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error al crear la persona. Por favor, inténtelo de nuevo más tarde.");
        }

        /* El código proporciona un controlador de Spring MVC para manejar las solicitudes relacionadas con la creación de una nueva persona.
           Se define un método registration con la anotación @PostMapping("/save-person"), que recibe un objeto PersonRequest como parámetro.
           Dentro del método, se crea una nueva instancia de la clase Person utilizando los datos proporcionados en el objeto PersonRequest.
           Luego, se llama al método createPerson del servicio personService para guardar la nueva persona en la base de datos.
           Finalmente, se redirige al usuario a la página de inicio del administrador.
           En resumen, el código maneja la creación de una nueva persona en el sistema a través de una solicitud POST y la guarda en la base de datos.
           */
    }





}
