package com.csye6225.fall2018.courseservice.resources;

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice.datamodel.Course;
import com.csye6225.fall2018.courseservice.datamodel.Professor;
import com.csye6225.fall2018.courseservice.service.ProfessorsService;

@Path("professors")
public class ProfessorsResource {

	ProfessorsService profService = new ProfessorsService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getProfessors() {
		return profService.getAllProfessors();
	}
	 
	@GET
	@Path("/{professorID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor getProfessorByID(@PathParam("professorID") String profID) {
		return profService.getProfessor(profID);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addProfessor(Professor professor) {
		profService.addProfessor(professor);
	}
	
	@PUT
	@Path("/{professorID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Professor updateProfessor(@PathParam("professorID") String profID, 
			Professor prof) {
		return profService.updateProfessorInformation(profID, prof);
	}
	
	@DELETE
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor deleteProfessor(@PathParam("professorID") String profID) {
		return profService.deleteProfessor(profID);
	}
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Professor> getProfessorsByDeparment(@QueryParam("department") String department) {
//		if (department == null) {
//			return profService.getAllProfessors();
//		}
//		return profService.getProfessorsByDepartment(department);
//	}

 }
