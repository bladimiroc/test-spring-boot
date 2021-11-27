package com.leanteach.handleemployees.service;

import com.leanteach.handleemployees.model.Employee;
import com.leanteach.handleemployees.model.Position;
import com.leanteach.handleemployees.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionService implements BaseInterface<Position> {

    @Autowired
    PositionRepository positionRepository;

    @Override
    public List<Position> getAll() {
        return positionRepository.findAll();
    }

    @Override
    public Position getById(int id) {
        return positionRepository.getById(id);
    }

    @Override
    public Position add(Position position) {
        return positionRepository.save(position);
    }

    @Override
    public Position update(Position position) {
        return positionRepository.save(position);
    }

    @Override
    public boolean delete(int id) {
        boolean answer;
        try {
            positionRepository.deleteById(id);
            answer = true;
        } catch (Exception e) {
            answer = false;
        }
        return answer;
    }

    public List<Position> getPositionSortBySalary() {
        Comparator<Employee> sortSalary = (h1, h2) -> h1.getSalary().compareTo(h2.getSalary());
        List<Position> positionList = positionRepository.findAll();  //.stream().sorted(sortSalary).collect(Collectors.toList());
        /*Collections.sort(positionList, new Comparator<Position>() {
            @Override
            public int compare(Position position, Position t1) {
                return position.getEmployees().g;
            }
        });*/
        return positionList;
    }
}
