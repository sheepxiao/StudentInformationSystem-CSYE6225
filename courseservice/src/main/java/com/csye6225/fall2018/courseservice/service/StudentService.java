package com.csye6225.fall2018.courseservice.service;

import java.util.*;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Student;

public class StudentService {
	static Map<Long, Student> studentMap = InMemoryDatabase.getStudentDB();
	static DynamoDbConnector dynamoDb;
    DynamoDBMapper mapper;
    
    public StudentService() {
 		dynamoDb = new DynamoDbConnector();
 		dynamoDb.init();
 		mapper = new DynamoDBMapper(dynamoDb.getClient());
 	}
	
	//GET, get all students
    public List<Student> getAllStudents() {	
		//Getting the list
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
		    .withIndexName("studentId-index")
		    .withConsistentRead(false);

	    List<Student> list =  mapper.scan(Student.class, scanExpression);
	    return list ;
	}
	
	//GET, get a student by ID
    public Student getStudent(String studentId) {
		List<Student> list = getStudentFromDynamoDB(studentId);
		return list.size() != 0 ? list.get(0) : null;
	}

	//POST, add a new student
	public Student addStudent(Student student) {	
		Student newStudent = new Student(student.getStudentId(), student.getFirstName(), student.getLastName(), 
				student.getJoiningDate(), student.getDepartment(), student.getRegisteredCourses());
		mapper.save(newStudent);
		
		System.out.println("Item added.");
	    System.out.println(newStudent.toString());
	    return newStudent; 
	}
	
	//PUT, update a student information
	public Student updateStudentInformation(String studentId, Student student) {	
		List<Student> list = getStudentFromDynamoDB(studentId);
		Student oldStudent = null;
		if(list.size() != 0) {
			oldStudent = list.get(0);
			oldStudent.setFirstName(student.getFirstName());
			oldStudent.setLastName(student.getLastName());
			oldStudent.setJoiningDate(student.getJoiningDate());
			oldStudent.setDepartment(student.getDepartment());
			oldStudent.setRegisteredCourses(student.getRegisteredCourses());
			mapper.save(oldStudent);
			System.out.println("Updated.");
			System.out.println(oldStudent.toString());
		}
		return oldStudent;
	}
	
	//DELETE, delete a student
	public Student deleteStudent(String studentId) {
		List<Student> list = getStudentFromDynamoDB(studentId);
		Student student = null;
		if(list.size() != 0){
			student = list.get(0);
			mapper.delete(student);
			Student deletedStudent = mapper.load(Student.class, student.getId());
			
			if (deletedStudent == null) {
	            System.out.println("The student is deleted.");
	            System.out.println(student.toString());
	        }
		}
		return student;
	}
	
	public List<Student> getStudentFromDynamoDB(String stuId){
	    HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
	    eav.put(":v1",  new AttributeValue().withS(stuId));

	    DynamoDBQueryExpression<Student> queryExpression = new DynamoDBQueryExpression<Student>()
			.withIndexName("studentId-index")
			.withConsistentRead(false)
			.withKeyConditionExpression("studentId = :v1")
			.withExpressionAttributeValues(eav);

	    List<Student> list =  mapper.query(Student.class, queryExpression);
		return list;
	}

}
