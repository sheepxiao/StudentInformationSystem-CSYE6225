package com.csye6225.fall2018.courseservice.service;

import java.util.*;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Professor;

public class ProfessorsService {
	static HashMap<Long, Professor> prof_Map = InMemoryDatabase.getProfessorDB();
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper; 
	
	public ProfessorsService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	
	//GET, get all professors
	public List<Professor> getAllProfessors() {	
		//Getting the list
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withIndexName("professorId-index")
				.withConsistentRead(false);

		List<Professor> list =  mapper.scan(Professor.class, scanExpression);
	    return list ;
	}
	
	//GET, get a professor by ID
	public Professor getProfessor(String profId) {
		List<Professor> list = getProfessorFromDynamoDB(profId);
		return list.size() != 0 ? list.get(0) : null;
	}

	//POST, add a new professor
	public void addProfessor(Professor prof) {
		Professor newProf = new Professor(prof.getProfessorId(), 
				prof.getFirstName(), prof.getLastName(), 
				prof.getJoiningDate(), prof.getDepartment());
		mapper.save(newProf);
		
		System.out.println("Item added:");
	    System.out.println(newProf.toString());
	}
	
	//PUT, update a professor information
	public Professor updateProfessorInformation(String profId, Professor prof) {	
		List<Professor> list = getProfessorFromDynamoDB(profId);
		Professor oldProf = null;
		if(list.size() != 0) {
			oldProf = list.get(0);
			oldProf.setFirstName(prof.getFirstName());
			oldProf.setLastName(prof.getLastName());
			oldProf.setJoiningDate(prof.getJoiningDate());
			oldProf.setDepartment(prof.getDepartment());
			mapper.save(oldProf);
			System.out.println("Item updated:");
		    System.out.println(oldProf.toString());
		}
		return oldProf;
	}
	
	//DELETE, delete a professor
	public Professor deleteProfessor(String profId) {
		List<Professor> list = getProfessorFromDynamoDB(profId);
		Professor prof = null;
		if(list.size() != 0){
			prof = list.get(0);
			mapper.delete(prof);
			
			Professor deletedProf = mapper.load(Professor.class, prof.getId());
			if (deletedProf == null) {
	            System.out.println("The professor is deleted.");
	            System.out.println(prof.toString());
	        }
		}
		return prof;
	}
	
	//GET, get professors by a department 
	public List<Professor> getProfessorsByDepartment(String department) {	
		//Getting the list
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			if (prof.getDepartment().equals(department)) {
				list.add(prof);
			}
		}
		return list ;
	}
		
	
	public List<Professor> getProfessorFromDynamoDB(String profId){
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(profId));

		DynamoDBQueryExpression<Professor> queryExpression = new DynamoDBQueryExpression<Professor>()
		    .withIndexName("professorId-index")
		    .withConsistentRead(false)
		    .withKeyConditionExpression("professorId = :v1")
		    .withExpressionAttributeValues(eav);

		List<Professor> list =  mapper.query(Professor.class, queryExpression);
		return list;
	}

}
