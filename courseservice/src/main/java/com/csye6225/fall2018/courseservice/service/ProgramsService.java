package com.csye6225.fall2018.courseservice.service;

import java.util.*;

import com.csye6225.fall2018.courseservice.datamodel.Course;
import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Program;

public class ProgramsService {
	static Map<String, Program> programsMap = InMemoryDatabase.getProgramDB();
	
	//GET, get all programs
	public List<Program> getAllPrograms() {	
		List<Program> list = new ArrayList<>();
		for (Program program : programsMap.values()) {
			list.add(program);
		}
		return list ;
	}
	
	//GET, get a program by it's name
	public Program getProgram(String programName) {
		return programsMap.get(programName);
	}

	//POST, add a new program
	public void addProgram(Program newProgram) {
		programsMap.put(newProgram.getProgramName(), newProgram);
	}
	
	//PUT, update information of a program
	public Program updateProfessorInformation(String programName, Program newProgramInfo) {	
		programsMap.put(programName, newProgramInfo);
		return programsMap.get(programName);
	}
	
	//DELETE, delete a program
	public Program deleteProgram(String programName) {
		Program deletedProgramInfo = programsMap.get(programName);
		programsMap.remove(programName);
		return deletedProgramInfo;
	}

}