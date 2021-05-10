package com.example.demo.service;

import com.example.demo.model.AnimalModel.Animal;
import com.example.demo.model.AnimalModel.AnimalRequest;

public interface AnimalService {

    Animal compareAnimalWeight(AnimalRequest request);

    Animal compareByStream(AnimalRequest request);

    void saveAnimal(AnimalRequest request);
}
