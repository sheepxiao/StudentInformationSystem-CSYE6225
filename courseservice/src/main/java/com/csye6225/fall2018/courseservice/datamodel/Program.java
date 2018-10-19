package com.csye6225.fall2018.courseservice.datamodel;

import java.util.*;

public class Program {
	private String programName;
	private List<Course> programCourses;
	
	public Program(String programName, List<Course> programCourses) {
		this.programCourses = programCourses;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public List<Course> getProgramCourses() {
		return programCourses;
	}

	public void setProgramCourses(List<Course> programCourses) {
		this.programCourses = programCourses;
	}
	
	
}
