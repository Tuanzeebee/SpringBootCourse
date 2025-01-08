package com.tuanzeebee.springboot.curddemo.dao;

import com.tuanzeebee.springboot.curddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);
}
