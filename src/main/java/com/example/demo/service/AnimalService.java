package com.example.demo.service;

import com.example.demo.model.AnimalModel.Animal;
import com.example.demo.model.AnimalModel.AnimalRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service

public class AnimalService {

    private List<Animal> createAnimals() {
        Animal tiger = new Animal();
        Animal elephant = new Animal();
        Animal mouse = new Animal();

        tiger.setName("Tiger");
        tiger.setWeight(100.00);

        elephant.setName("Elephant");
        elephant.setWeight(500.00);

        mouse.setName("Mouse");
        mouse.setWeight(2.00);

        List<Animal> animals = new ArrayList<>();
        animals.add(tiger);
        animals.add(elephant);
        animals.add(mouse);

        return animals;
    }

    public Animal compareAnimalWeight(AnimalRequest request) {

        List<Animal> animalsList = getAnimalByName(request);

        double max = animalsList.get(0).getWeight();
        int index = 0;

        for (int i=0; i < animalsList.size(); i++) {
            if(max <= animalsList.get(i).getWeight()) {
                max = animalsList.get(i).getWeight();
                index = i;
            }
        }
        System.out.println(animalsList.get(index));
        return animalsList.get(index);

    }

    public Animal compareByStream(AnimalRequest request) {
        List<Animal> animalsList = getAnimalByName(request);

        Animal animalByMaxWeight = animalsList.stream().max(Comparator.comparing(Animal::getWeight)).orElseThrow(NoSuchElementException::new);
        return animalByMaxWeight;
    }


    private List<Animal> getAnimalByName(AnimalRequest request) {

        List<Animal> animalsList = new ArrayList<>();

        for (Animal a: createAnimals()) {
            if(a.getName().equals(request.getAnimalName1()) | a.getName().equals(request.getAnimalName2())) {
                animalsList.add(a);
            }
        }
        return animalsList;
    }

}
