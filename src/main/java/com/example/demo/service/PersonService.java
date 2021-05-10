package com.example.demo.service;

import com.example.demo.model.PersonModel.Person;
import com.example.demo.model.PersonModel.PersonRequest;

import java.util.List;

public interface PersonService {

    Person getPerson(PersonRequest request);
    Person findPerson(PersonRequest request);
    List<Person> findPersonByName(PersonRequest request);
}
