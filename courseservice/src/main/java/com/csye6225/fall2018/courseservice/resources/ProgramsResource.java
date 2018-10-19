package com.csye6225.fall2018.courseservice.resources;

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice.datamodel.Course;
import com.csye6225.fall2018.courseservice.datamodel.Program;
import com.csye6225.fall2018.courseservice.service.ProgramsService;

@Path("programs")
public class ProgramsResource {

	ProgramsService programService = new ProgramsService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Program> getAllPrograms() {
		return programService.getAllPrograms();
	}
	 
	@GET
	@Path("/{programName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program getProgramByName(@PathParam("programName") String programName) {
		return programService.getProgram(programName);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addProgram(Program program) {
		programService.addProgram(program);
	}
	
	@PUT
	@Path("/{programName}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program updateProfessor(@PathParam("programName") String programName, Program newProgram) {
		return programService.updateProfessorInformation(programName, newProgram);
	}
	
	@DELETE
	@Path("/{programName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program deleteProgram(@PathParam("programName") String programName) {
		return programService.deleteProgram(programName);
	}
 }
