package com.springcrud.repository;

import com.springcrud.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Employee, Integer> {

}
