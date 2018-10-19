package com.csye6225.fall2018.courseservice.datamodel;

import java.util.*;

public class Course {
	private String courseName;
	private List<Lecture> lectures;
	private String board;
	private String roster;
	private List<Student> enrolledStudents;
	private Professor associatedPro;
	private Student courseTA;
	
	private Course() {
		
	}
	
	public Course(String courseName, List<Lecture> lectures, String board, String roster, List<Student> enrolledStudents, Professor associatedPro, Student courseTA) {
		this.courseName = courseName;
		this.lectures = lectures;
		this.board = board;
		this.roster = roster;
		this.enrolledStudents = enrolledStudents;
		this.associatedPro = associatedPro;
		this.courseTA = courseTA;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<Lecture> getLectures() {
		return lectures;
	}

	public void setLectures(List<Lecture> lectures) {
		this.lectures = lectures;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getRoster() {
		return roster;
	}

	public void setRoster(String roster) {
		this.roster = roster;
	}

	public List<Student> getEnrolledStudents() {
		return enrolledStudents;
	}

	public void setEnrolledStudents(List<Student> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}

	public Professor getAssociatedPro() {
		return associatedPro;
	}

	public void setAssociatedPro(Professor associatedPro) {
		this.associatedPro = associatedPro;
	}

	public Student getCourseTA() {
		return courseTA;
	}

	public void setCourseTA(Student courseTA) {
		this.courseTA = courseTA;
	}
	
	
}
