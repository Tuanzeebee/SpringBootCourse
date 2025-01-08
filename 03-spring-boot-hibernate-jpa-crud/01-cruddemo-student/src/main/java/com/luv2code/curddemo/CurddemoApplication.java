package com.luv2code.curddemo;

import com.luv2code.curddemo.dao.StudentDao;
import com.luv2code.curddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CurddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurddemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(StudentDao studentDao){
		return runner -> {
			//createStudent(studentDao);
			createMultipleStudents(studentDao);
			//readStudent(studentDao);
			//queryForStudents(studentDao);
			//queryForStudentsByLastName(studentDao);
			//updateStudents(studentDao);
			//deleteStudens(studentDao);
			//deleteAllStudens(studentDao);
		};
	}

	private void deleteAllStudens(StudentDao studentDao) {
		System.out.println("Deleting All Students");
		int numRowsDeleted = studentDao.deleteAll();
		System.out.println("Deleted row count: "+numRowsDeleted);
	}

	private void deleteStudens(StudentDao studentDao) {
		int studentId=3;

		System.out.println("Deleting student id: "+studentId);

		studentDao.delete(studentId);
	}

	private void updateStudents(StudentDao studentDao) {
		int studentId = 1;
		System.out.println("Getting student with id: "+studentId);

		Student myStudent = studentDao.findById(studentId);

		System.out.println("Updating student...");

		myStudent.setFirstName("John");
		studentDao.update(myStudent);

		System.out.println("Update student"+myStudent);
	}

	private void queryForStudentsByLastName(StudentDao studentDao) {
		List<Student> theStudents = studentDao.findLastName(" Duck");

		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDao studentDao) {
		List<Student> theStudents = studentDao.findAll();

		//Display students
		for (Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}

	}

	private void readStudent(StudentDao studentDao) {
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Daffy"," Duck","Daffy@luv2code.com");

		System.out.println("Saving the student...");
		studentDao.save(tempStudent);

		int theId= tempStudent.getId();
		System.out.println("Save student.Generated id: "+theId);

		System.out.println("Retrieving student with id:"+theId);
		Student myStudent = studentDao.findById(theId);

		//Display student
		System.out.println("Found the student: "+ myStudent);
	}

	private void createMultipleStudents(StudentDao studentDao) {
		//create the student object
		System.out.println("Creating 3 student objects....");
		Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
		Student tempStudent2 = new Student("Marry", "Public", "marry@luv2code.com");
		Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@luv2code.com");

		////save the student object
		System.out.println("Saving the students...");
		studentDao.save(tempStudent1);
		studentDao.save(tempStudent2);
		studentDao.save(tempStudent3);
	}

	private void createStudent(StudentDao studentDao){
		//create the student object
		System.out.println("Creating new student object....");
		Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");
		//save the student object

		System.out.println("Saving the student.....");
		studentDao.save(tempStudent);

		//display id of the saved student
		System.out.println("Saved student. Generated id :"+tempStudent.getId());
	}
}
