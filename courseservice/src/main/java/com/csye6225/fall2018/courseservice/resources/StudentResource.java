package com.csye6225.fall2018.courseservice.resources;

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice.datamodel.Student;
import com.csye6225.fall2018.courseservice.service.StudentService;

@Path("students")
public class StudentResource {

	StudentService studentService = new StudentService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}
	 
	@GET
	@Path("/{studentID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudentByID(@PathParam("studentID") String studentID) {
		return studentService.getStudent(studentID);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addStudent(Student student) {
		studentService.addStudent(student);
	}
	
	@PUT
	@Path("/{studentID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student updateProfessor(@PathParam("studentID") String studentID, Student student) {
		return studentService.updateStudentInformation(studentID, student);
	}
	
	@DELETE
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student deleteStudent(@PathParam("studentID") String studentID) {
		return studentService.deleteStudent(studentID);
	}
 }
