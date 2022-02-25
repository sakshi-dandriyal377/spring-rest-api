package com.springcrud.controller;

import java.util.List;

import com.springcrud.exception.ResourceNotFoundException;
import com.springcrud.model.Employee;
import com.springcrud.repository.EmpRepository;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class EmpController {

    @Autowired
    private EmpRepository empRepository;

    @GetMapping
    @RequestMapping("/api/getemp")
    public List<Employee> getAllEmployees() {
        return empRepository.findAll();
    }

    @PostMapping
    @RequestMapping("/api/addemp")
    public Employee addEmp(@RequestBody Employee emp) {
        return empRepository.save(emp);
    }

    @GetMapping("/api/getemp/{id}")
    public ResponseEntity<Employee> getEmpById(@PathVariable int id) {
        Employee emp = empRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist"));
        return ResponseEntity.ok(emp);
    }

    @PutMapping("/api/addemp/{id}")
    public ResponseEntity<Employee> updateEmp(@PathVariable int id, @RequestBody Employee emp) {
        Employee updateEmployee = empRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist"));

        updateEmployee.setName(emp.getName());
        updateEmployee.setSalary(emp.getSalary());
        empRepository.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);
    }

    @DeleteMapping("/api/emp/{id}")
    public ResponseEntity<HttpStatus> deleteEmp(@PathVariable int id) {
        Employee emp = empRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist"));
        empRepository.delete(emp);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
