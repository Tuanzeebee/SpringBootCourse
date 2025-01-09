package com.tuanzeebee.springboot.curddemo.service;

import com.tuanzeebee.springboot.curddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);
}
