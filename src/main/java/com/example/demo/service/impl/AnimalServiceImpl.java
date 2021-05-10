package com.example.demo.service.impl;

import com.example.demo.entity.AnimalEntity;
import com.example.demo.model.AnimalModel.Animal;
import com.example.demo.model.AnimalModel.AnimalRequest;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    private List<Animal> createAnimals() {
        List<Animal> animals = new ArrayList<>();
        animals.add(Animal.builder().name("Tiger").weight(100.00).build());
        animals.add(Animal.builder().name("Elephant").weight(500.00).build());
        animals.add(Animal.builder().name("Mouse").weight(2.00).build());
        return animals;
    }

    @Override
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

    @Override
    public Animal compareByStream(AnimalRequest request) {
        List<Animal> animalsList = getAnimalByName(request);

        Animal animalByMaxWeight = animalsList.stream().max(Comparator.comparing(Animal::getWeight)).orElseThrow(NoSuchElementException::new);
        return animalByMaxWeight;
    }

    @Override
    public void saveAnimal(AnimalRequest request) {
        String name = request.getName();
        AnimalEntity animalEntity = new AnimalEntity();
        animalEntity.setName(name);
        animalRepository.save(animalEntity);

        // Поиск записи по ИД
        AnimalEntity findedById =  animalRepository.findById(1L).orElse(null);
        findedById.setName(findedById.getName() + ".");
        animalRepository.save(findedById);

        // Поиск по другим полям
        List<AnimalEntity> findedByNameList =  animalRepository.findByName(name);
        findedByNameList.stream().forEach(a -> {

        });


        // Удаление записи
        //animalRepository.delete(null);
        //animalRepository.deleteById(1L);
        //animalRepository.deleteAll();
    }


    private List<Animal> getAnimalByName(AnimalRequest request) {

        List<Animal> animalsList = new ArrayList<>();
        List<Animal> animals = createAnimals();

        /*animals.stream().forEach(aa -> {
            if(aa.getName().equals(request.getAnimalName1()) | aa.getName().equals(request.getAnimalName2())) {
                animalsList.add(aa);
            }
        });*/

        for (Animal a: animals) {
            // if (Objects.equals(a.getName(), request.getAnimalName1()))

            if (a.getName().equals(request.getAnimalName1()) | a.getName().equals(request.getAnimalName2())) {
                animalsList.add(a);
            }
        }
        return animalsList;
    }

}
