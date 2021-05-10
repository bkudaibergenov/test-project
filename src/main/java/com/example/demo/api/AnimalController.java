package com.example.demo.api;


import com.example.demo.model.AnimalModel.Animal;
import com.example.demo.model.AnimalModel.AnimalRequest;
import com.example.demo.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping("/compareAnimalWeight")
    public Animal compareAnimalWeight(@RequestBody AnimalRequest request) { return animalService.compareAnimalWeight(request); }

    @PostMapping("/compareByStream")
    public Animal compareByStream(@RequestBody AnimalRequest request) {return animalService.compareByStream(request);}
}
