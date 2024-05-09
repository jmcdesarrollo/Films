package com.api.Films.service;

import com.api.Films.entity.Person;


import java.util.ArrayList;
import java.util.Optional;


public interface PersonService {
    ArrayList<Person> getPerson();
    Person savePerson(Person person);
    Optional<Person> getById(Long id);
    Person updateById(Person request, Long id);

    void createPerson(Person person);
}
