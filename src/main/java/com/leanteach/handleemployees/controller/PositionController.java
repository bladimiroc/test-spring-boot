package com.leanteach.handleemployees.controller;

import com.leanteach.handleemployees.model.Position;
import com.leanteach.handleemployees.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PositionController {

    @Autowired
    PositionService positionService;

    @GetMapping("/position")
    public List<Position> getAll() {
        return positionService.getAll();
    }

    @GetMapping("/position/sort")
    public List<Position> getAllSorted() {
        return positionService.getPositionSortBySalary();
    }

    @PostMapping("/position")
    public int save(@RequestBody Position position) {
        position = positionService.add(position);
        return position.getId();
    }
}
