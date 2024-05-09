package com.api.Films.service;

import com.api.Films.entity.Person;
import com.api.Films.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonRepository personRepository;

    // Método para obtener una persona por su ID
    public Optional<Person> getById(Long id) {
        return personRepository.findById(id);
    }

    // Método para obtener todos los Personajes

    public ArrayList<Person> getPerson() {
        return (ArrayList<Person>) personRepository.findAll();
    }

    // Método para guardar una nueva Persona

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    // Método para buscar una persona por su ID



    // Método para actualizar una persona por su ID

    public Person updateById(Person request, Long id) {
        // Buscamos la persona por su ID
        Optional<Person> optionalPerson = personRepository.findById(id);

        // Verificamos si la persona fue encontrada
        if (optionalPerson.isPresent()) {
            // Si la persona fue encontrada, obtenemos la persona y la asignamos a la variable person
            Person person = optionalPerson.get();
            // Actualizamos los campos de la persona con los valores proporcionados en la solicitud
            person.setName(request.getName());
            person.setSurname(request.getSurname());
            // Guardamos los cambios en la base de datos
            return personRepository.save(person);
        } else {
            // Si la persona no fue encontrada, lanzamos una excepción o manejamos el error según sea necesario
            throw new RuntimeException("La persona con el ID " + id + " no fue encontrada.");
        }
    }

    @Override
    public void createPerson(Person person) {

    }
    /* , primero utilizamos findById(id) para buscar una persona por su ID en el
     repositorio de personas (personRepository). Luego, verificamos si la persona
      fue encontrada utilizando isPresent(). Si la persona está presente,
       utilizamos get() para obtener la persona y la asignamos a la variable
       person. Finalmente, podemos hacer lo que necesitemos con la persona
        encontrada. Si la persona no está presente, imprimimos un mensaje
        indicando que no se encontró ninguna persona con el ID proporcionado.*/

}

