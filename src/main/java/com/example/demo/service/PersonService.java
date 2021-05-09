package com.example.demo.service;

import com.example.demo.model.PersonModel.City;
import com.example.demo.model.PersonModel.Person;
import com.example.demo.model.PersonModel.PersonRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    public Person getPerson(PersonRequest request) {
        Person person = new Person();
        person.setName("Иван");
        person.setAge(20 + request.getPlus());
        person.setCities(getCities());

        return person;
    }

    private List<City> getCities() {
        City city1 = new City();
        city1.setName("Астана");

        City city2 = new City();
        city2.setName("Алматы");

        List<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);

        return cities;
    }

    public Person findPerson(PersonRequest request) {
        Person person1 = new Person();
        person1.setName("Иван");

        Person person2 = new Person();
        person2.setName("Марат");

        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);

        for (Person p : personList) {
            if (p.getName().equals(request.getName())) {
                return p;
            }
        }

        return null;
    }


    public List<Person> findPersonByName(PersonRequest request) {
        Person person1 = new Person();
        Person person2 = new Person();
        City city1 = new City();
        City city2 = new City();

        person1.setName("Иван");
        city1.setName("Berlin");
        person1.setCity(city1);

        person2.setName("Марат");
        city2.setName("Ankara");
        person2.setCity(city2);

        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);

        List<Person> personsListByCity = new ArrayList<>();


        for (Person p: personList) {
            if (p.getCity().getName().equals(request.getCityName())) {
                personsListByCity.add(p);
            }
        }
        return personsListByCity;
    }
}
