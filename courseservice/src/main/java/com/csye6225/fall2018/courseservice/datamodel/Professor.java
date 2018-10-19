package com.csye6225.fall2018.courseservice.datamodel;

import java.util.*;

//import javax.xml.bind.annotation.XmlRootElement;


public class Professor {
	private String professorName;
	private String professorID;
	private String department;
	private String contactEmail;
	private List<Course> teachedCourse;
	
	private Professor() {
		
	}
	
	public Professor(String professorName, String professorID, String department, String contactEmail, List<Course> teachedCourse) {
		this.professorName = professorName;
		this.professorID = professorID;
		this.department = department;
		this.contactEmail = contactEmail;
		this.teachedCourse = teachedCourse;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public String getProfessorID() {
		return professorID;
	}

	public void setProfessorID(String professorID) {
		this.professorID = professorID;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public List<Course> getTeachedCourse() {
		return teachedCourse;
	}

	public void setTeachedCourse(List<Course> teachedCourse) {
		this.teachedCourse = teachedCourse;
	}
	
	
}
