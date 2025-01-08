package com.luv2code.curddemo.dao;

import com.luv2code.curddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao{

    //define field for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection

    @Autowired
    public StudentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findAll() {
        //create query

        //return query results
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by lastName desc",Student.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findLastName(String theLastName) {

        TypedQuery theQuery= entityManager.createQuery(
                        "FROM Student WHERE lastName=:theData",Student.class);
        theQuery.setParameter("theData",theLastName);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Student theStudent = entityManager.find(Student.class,id);
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted= entityManager.createQuery(" DELETE FROM Student").executeUpdate();

        return numRowsDeleted;
    }
}
