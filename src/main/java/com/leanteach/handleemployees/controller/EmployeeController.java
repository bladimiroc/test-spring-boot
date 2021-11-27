package com.leanteach.handleemployees.controller;

import com.leanteach.handleemployees.model.Employee;
import com.leanteach.handleemployees.service.EmployeeService;
import com.leanteach.handleemployees.service.vm.EmployeeVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee")
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @PostMapping("/employee/search")
    public List<Employee> searchEmployee(@RequestBody EmployeeVM employeeVM) {
        return employeeService.searchEmployee(employeeVM);
    }

    @PostMapping("/employee")
    public ResponseEntity<Map<String, String>> saveEmployee(@RequestBody EmployeeVM employee) {
        try {
            employeeService.createEmployee(employee);
            Map<String, String> answer = new HashMap<>();
            answer.put("message", "Employee was added successfully");
            return new ResponseEntity<>(answer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/employee")
    public ResponseEntity<Map<String, String>> updateEmployee(@RequestBody EmployeeVM employeeVM) {
        try {
            employeeService.updateEmployee(employeeVM);
            Map<String, String> answer = new HashMap<>();
            answer.put("message", "Employee was updated successfully");
            return new ResponseEntity<>(answer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "employeeId") int employeeId) {
        boolean answer = employeeService.delete(employeeId);
        if (answer) {
            Map<String, String> message = new HashMap<>();
            message.put("message", "Employee was deleted successfully");
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
