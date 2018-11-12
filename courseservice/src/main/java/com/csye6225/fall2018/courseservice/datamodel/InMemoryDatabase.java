package com.csye6225.fall2018.courseservice.datamodel;

import java.util.*;

public class InMemoryDatabase {

	private static HashMap<String, Program> programDB = new HashMap<>();
	private static HashMap<Long, Professor> professorDB = new HashMap<>();
	private static HashMap<Long, Student> studentDB = new HashMap<>();
	
	public static HashMap<String, Program> getProgramDB() {
		return programDB;
	}
	
	public static HashMap<Long, Professor> getProfessorDB() {
		return professorDB;
	}
	
	public static HashMap<Long, Student> getStudentDB() {
		return studentDB;
	}
	
}
