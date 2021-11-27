package com.leanteach.handleemployees.service;

import com.leanteach.handleemployees.model.Employee;
import com.leanteach.handleemployees.repository.EmployeeRepository;
import com.leanteach.handleemployees.repository.PersonRepository;
import com.leanteach.handleemployees.repository.PositionRepository;
import com.leanteach.handleemployees.service.vm.EmployeeVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements BaseInterface<Employee> {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PositionRepository positionRepository;

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(int id) {
        return employeeRepository.getById(id);
    }

    @Override
    public Employee add(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public boolean delete(int id) {
        boolean answer;
        try {
            employeeRepository.deleteById(id);
            answer = true;
        } catch (Exception e) {
            answer = false;
        }
        return answer;
    }

    public Employee createEmployee(EmployeeVM employeeVM) {
        Employee employee = new Employee();
        employee.setSalary(employeeVM.getSalary());
        if (employeeVM.getPersonId() > 0) {
            employee.setPerson(personRepository.getById(employeeVM.getPersonId()));
        }
        if (employeeVM.getPositionId() > 0) {
            employee.setPosition(positionRepository.getById(employeeVM.getPositionId()));
        }
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployee(EmployeeVM employeeVM) {
        Employee employee = employeeRepository.getById(employeeVM.getId());
        employee.setId(employeeVM.getId());
        employee.setSalary(employeeVM.getSalary());
        if (employeeVM.getPersonId() > 0) {
            employee.setPerson(personRepository.getById(employeeVM.getPersonId()));
        }
        if (employeeVM.getPositionId() > 0) {
            employee.setPosition(positionRepository.getById(employeeVM.getPositionId()));
        }
        employeeRepository.save(employee);
        return employee;
    }

    public List<Employee> searchEmployee(EmployeeVM employeeVM) {
        List<Employee> employees = employeeRepository.findAll();
        if (employeeVM != null && employeeVM.getName() != null &&
                employeeVM.getName() != "") {
            employees = employees.stream().filter(
                    employee -> employee.getPerson().getName().contains(employeeVM.getName())
            ).collect(Collectors.toList());
        }
        if (employeeVM != null && employeeVM.getPositionName() != null &&
            employeeVM.getPositionName() != "") {
            employees = employees.stream().filter(
                    employee -> employee.getPosition().getName().contains(employeeVM.getPositionName())
            ).collect(Collectors.toList());
        }
        return  employees;
    }
}
