package com.csye6225.fall2018.courseservice.service;

import java.util.*;

import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Student;

public class StudentService {
	static Map<String, Student> studentMap = InMemoryDatabase.getStudentDB();
	
	//GET, get all students
	public List<Student> getAllStudents() {	
		List<Student> list = new ArrayList<>();
		for (Student student : studentMap.values()) {
			list.add(student);
		}
		return list ;
	}
	
	//GET, get a student by ID
	public Student getStudent(String studentID) {
		return studentMap.get(studentID);
	}

	//POST, add a new student
	public void addStudent(Student newStudent) {
		//create a new student object
		studentMap.put(newStudent.getStudentID(), newStudent);
	}
	
	//PUT, update a student information
	public Student updateProfessorInformation(String studentID, Student newStudInfo) {	
		studentMap.put(studentID, newStudInfo);
		return studentMap.get(studentID);
	}
	
	//DELETE, delete a student
	public Student deleteStudent(String studentID) {
		Student deletedStudInfo = studentMap.get(studentID);
		studentMap.remove(studentID);
		return deletedStudInfo;
	}

}
