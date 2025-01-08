package com.tuanzeebee.demo.rest;

import com.tuanzeebee.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    //define @PostConstructor to load the student data ...only once!

    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();

        theStudents.add(new Student("Poornima","Patel"));
        theStudents.add(new Student("Mario","Rossi"));
        theStudents.add(new Student("Marry","Smith"));
    }

    //define endpoint for /student - return list of students

    @GetMapping("/students")
    public List<Student> getStudents(){
        
        return theStudents;
    }

    //define endpoint or /student/{studentId} -return student at index

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        //just index into the list ...keep it simple for now

        // check the studentId again list size
        if (studentId >= theStudents.size()||(studentId<0)){
            throw new StudentNotFoundException("Student id not found- "+studentId);
        }
        return theStudents.get(studentId);
    }

}
