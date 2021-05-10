package com.example.demo.api;

import com.example.demo.model.PersonModel.Person;
import com.example.demo.model.PersonModel.PersonRequest;
import com.example.demo.service.PersonService;
import com.example.demo.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/getName")
    public String getName() {
        return "Hello name";
    }

    @PostMapping("/getPerson")
    public Person getPerson(@RequestBody @Valid PersonRequest request) {
        return personService.getPerson(request);
    }

    @PostMapping("/findPerson")
    public Person findPerson(@RequestBody @Valid PersonRequest request) {
        return personService.findPerson(request);
    }

    @PostMapping("/findPersonByName")
    public List<Person> findPersonByName(@RequestBody @Valid PersonRequest request) {
        return personService.findPersonByName(request);
    }
}
