package com.example.demo.model.PersonModel;

import lombok.Data;

import java.util.List;

@Data
public class Person {

    private String name;
    private int age;
    private List<City> cities;
    private City city;
}
