package com.csye6225.fall2018.courseservice.service;

import java.util.*;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.datamodel.Course;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.courseservice.datamodel.Board;
import com.csye6225.fall2018.courseservice.EmailAnnouncement;

public class CourseService {
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper; 
	
	public CourseService(){
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
		
	}
	
	//GET, get all courses
	public List<Course> getAllCourses() {	
		//Getting the list
	   DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
		    .withIndexName("courseId-index")
		    .withConsistentRead(false);

	   List<Course> list =  mapper.scan(Course.class, scanExpression);
       return list ;
    }
	
	//GET, get a course by ID
    public Course getCourse(String courseId) {
		List<Course> list = getCourseFromDynamoDB(courseId);
		return list.size() != 0 ? list.get(0) : null;
	}
	
    //POST, add a new course
	public Course addCourse(Course course) {
		String topicArn = EmailAnnouncement.createTopic("course"+course.getCourseId());
	    Course newCourse = new Course(course.getCourseId(), course.getProfessorId(), 
	    		course.getTaId(), course.getDepartment(), 
	    		course.getBoardId(), course.getRoster(), topicArn);
		mapper.save(newCourse);
		
		Board board = new Board(newCourse.getBoardId(),newCourse.getCourseId());
		BoardService boardSer = new BoardService();
		boardSer.addBoard(board);
			
		System.out.println("Item added:");
		System.out.println(newCourse.toString());	    
		return newCourse;
    }
	
	//PUT, update a course information
	public Course updateCourseInformation(String courseId, Course course) {	
		List<Course> list = getCourseFromDynamoDB(courseId);
		Course oldCourse = null;
		if(list.size() != 0) {
			oldCourse = list.get(0);
			oldCourse.setProfessorId(course.getProfessorId());
			oldCourse.setTaId(course.getTaId());
			oldCourse.setDepartment(course.getDepartment());
			oldCourse.setBoardId(course.getBoardId());
			oldCourse.setRoster(course.getRoster());
			oldCourse.setSNSTopicArn(course.getSNSTopicArn());
			mapper.save(oldCourse);
			System.out.println("Item updated:");
		    System.out.println(oldCourse.toString());
		}			
		return oldCourse;
	}
		
	//DELETE, delete a course
	public Course deleteCourse(String courseId) {
		List<Course> list = getCourseFromDynamoDB(courseId);
		Course course = null;
		if(list.size() != 0){
			course = list.get(0);
			mapper.delete(course);
			Course deletedCourse = mapper.load(Course.class, course.getId());
				
			if (deletedCourse == null) {
		        System.out.println("The course is deleted.");
		        System.out.println(course.toString());
		    }
		}		
	 return course;
	 }
		
	 public List<Course> getCourseFromDynamoDB(String courseId){
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(courseId));

		DynamoDBQueryExpression<Course> queryExpression = new DynamoDBQueryExpression<Course>()
			.withIndexName("courseId-index")
			.withConsistentRead(false)
		    .withKeyConditionExpression("courseId = :v1")
			.withExpressionAttributeValues(eav);

	    List<Course> list =  mapper.query(Course.class, queryExpression);
	    return list;
     }
}