package com.example.demo.api;


import com.example.demo.model.AnimalModel.Animal;
import com.example.demo.model.AnimalModel.AnimalRequest;
import com.example.demo.service.AnimalService;
import com.example.demo.service.CatService;
import com.example.demo.service.impl.AnimalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Qualifier("lionService")
    @Autowired
    private CatService lionService;

    @Qualifier("tigerService")
    @Autowired
    private CatService tigerService;


    @PostMapping("/compareAnimalWeight")
    public Animal compareAnimalWeight(@RequestBody AnimalRequest request) { return animalService.compareAnimalWeight(request); }

    @PostMapping("/compareByStream")
    public Animal compareByStream(@RequestBody AnimalRequest request) {return animalService.compareByStream(request);}

    @PostMapping("/saveAnimal")
    public void saveAnimal(@RequestBody AnimalRequest request) {
        animalService.saveAnimal(request);
    }
}
