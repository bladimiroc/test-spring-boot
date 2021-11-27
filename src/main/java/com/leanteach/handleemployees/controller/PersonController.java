package com.leanteach.handleemployees.controller;

import com.leanteach.handleemployees.model.Person;
import com.leanteach.handleemployees.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/person")
    private List<Person> getAllPeople() {
        return personService.getAll();
    }

    @PostMapping("/person")
    private int savePerson(@RequestBody Person person) {
        person = personService.add(person);
        return person.getId();
    }
}
