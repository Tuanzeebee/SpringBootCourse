package com.luv2code.curddemo.dao;

import com.luv2code.curddemo.entity.Student;
import java.util.List;

public interface StudentDao {
    void save(Student theStudent);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findLastName(String theLastName);

    void update(Student theStudent);

    void delete(Integer id);
    int deleteAll();
}
