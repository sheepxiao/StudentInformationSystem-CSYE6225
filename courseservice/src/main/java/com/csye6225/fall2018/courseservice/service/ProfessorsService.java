package com.csye6225.fall2018.courseservice.service;

import java.util.*;

import com.csye6225.fall2018.courseservice.datamodel.Course;
import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Professor;

public class ProfessorsService {
	static Map<String, Professor> profMap = InMemoryDatabase.getProfessorDB();
	
	//GET, get all professors
	public List<Professor> getAllProfessors() {	
		List<Professor> list = new ArrayList<>();
		for (Professor prof : profMap.values()) {
			list.add(prof);
		}
		return list ;
	}
	
	//GET, get a professor by ID
	public Professor getProfessor(String profID) {
		return profMap.get(profID);
	}

	//POST, add a new professor
	public void addProfessor(Professor prof) {
		//create a new Professor Object
//		Professor prof = new Professor(professor);
		profMap.put(prof.getProfessorID(), prof);
	}
	
	//PUT, update a professor information
	public Professor updateProfessorInformation(String profID, Professor newProfInfo) {	
		profMap.put(profID, newProfInfo);
		return profMap.get(profID);
	}
	
	//DELETE, delete a professor
	public Professor deleteProfessor(String profID) {
		Professor deletedProfInfo = profMap.get(profID);
		profMap.remove(profID);
		return deletedProfInfo;
	}

}
