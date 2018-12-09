package com.csye6225.fall2018.courseservice;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.csye6225.fall2018.courseservice.service.BoardService;
import com.csye6225.fall2018.courseservice.service.CourseService;

public class EmailAnnouncement implements RequestHandler<DynamodbEvent, String>{
	private static AmazonSNS SNS_CLIENT = AmazonSNSClientBuilder.standard()
																.withRegion(Regions.US_EAST_2)
																.build();
	
	@Override
	public String handleRequest(DynamodbEvent events, Context context) {		
		for (DynamodbStreamRecord record : events.getRecords()){
			if(record.getEventName().equals("INSERT")) {
				System.out.println(record.getDynamodb().toString());
				String boardId = record.getDynamodb().getNewImage().get("boardId").getS();
				String topicArn = getTopicArnByBoardId(boardId);
				System.out.println(topicArn);
				String message = record.getDynamodb().getNewImage().get("announcementText").getS();
				sendEmailNotification(topicArn, message, "new announcement");
			}         
	    }
	    return "Success" + events.getRecords().size() + " records.";
	}
	
	public static String createTopic(String topicName) {
		return SNS_CLIENT.createTopic(topicName).getTopicArn();
	}
	
	public static void subscribe(String topicArn, String email) {
		SNS_CLIENT.subscribe(topicArn, "email", email);
		
	}
	
	public void sendEmailNotification(String topicArn, final String message, final String subject) {
		PublishRequest publishRequest = new PublishRequest(topicArn, message, subject);
		SNS_CLIENT.publish(publishRequest);
	}
	
	private String getTopicArnByBoardId(String boardId) {
		BoardService boardService = new BoardService();
		CourseService courseService = new CourseService();
		String courseId = boardService.getBoardFromDynamoDB(boardId).get(0).getCourseId();
		return courseService.getCourseFromDynamoDB(courseId).get(0).getSNSTopicArn();
	}
}
