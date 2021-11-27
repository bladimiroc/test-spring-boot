package com.leanteach.handleemployees.service;

import com.leanteach.handleemployees.model.Person;
import com.leanteach.handleemployees.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements BaseInterface<Person> {

    @Autowired
    PersonRepository personRepository;

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public Person getById(int id) {
        return personRepository.getById(id);
    }

    @Override
    public Person add(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person) {
        return personRepository.save(person);
    }

    @Override
    public boolean delete(int id) {
        boolean answer;
        try {
            personRepository.deleteById(id);
            answer = true;
        } catch (Exception e) {
            answer = false;
        }
        return answer;
    }
}
