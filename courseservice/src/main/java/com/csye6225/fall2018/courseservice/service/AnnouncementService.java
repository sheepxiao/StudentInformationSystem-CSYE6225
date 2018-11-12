package com.csye6225.fall2018.courseservice.service;

import java.util.*;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.courseservice.datamodel.Announcement;

public class AnnouncementService {
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper;

	public AnnouncementService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}

	//GET, get all announcements
	public List<Announcement> getAllAnnouncements() {
		// Getting the list
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withIndexName("boardId-announcementId-index")
				.withConsistentRead(false);

		List<Announcement> list = mapper.scan(Announcement.class, scanExpression);
		return list;
	}
	
	//GET, get a announcement by ID
	public Announcement getAnnouncement(String boardId, String announcementId) {
		List<Announcement> list = getAnnouncementFromDDB(boardId,announcementId);
		return list.size() != 0 ? list.get(0) : null;
	}

	//POST, add a new announcement
	public Announcement addAnnouncement(Announcement announcement) {
		Announcement newAnn = new Announcement(announcement.getAnnouncementId(), 
				announcement.getAnnouncementText(), announcement.getBoardId());
		mapper.save(newAnn);
		return newAnn;
	}
	
	//PUT, update an announcement information
	public Announcement updateAnnouncementInformation(String boardId, String announcementId, Announcement announcement) {
		List<Announcement> list = getAnnouncementFromDDB(boardId, announcementId);
		Announcement ann = null;
		if (list.size() != 0) {
			ann = list.get(0);
			ann.setAnnouncementText(announcement.getAnnouncementText());;
			mapper.save(ann);
			System.out.println("Item updated.");
			System.out.println(ann.toString());
		}
		return ann;
	}


	//DELETE, delete an announcement
	public Announcement deleteAnnouncement(String boardId, String announcementId) {
		List<Announcement> list = getAnnouncementFromDDB(boardId, announcementId);
		Announcement ann = null;
		if (list.size() != 0) {
			ann = list.get(0);
			mapper.delete(ann);
			Announcement deletedAnnouncement = mapper.load(Announcement.class, ann.getId());

			if (deletedAnnouncement == null) {
				System.out.println("The announcement is deleted.");
				System.out.println(ann.toString());
			}
		}

		return ann;
	}

	public List<Announcement> getAnnouncementFromDDB(String boardId, String announcementId ) {
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(boardId));
		eav.put(":v2", new AttributeValue().withS(announcementId));

		DynamoDBQueryExpression<Announcement> queryExpression = new DynamoDBQueryExpression<Announcement>()
				.withIndexName("boardId-announcementId-index")
				.withConsistentRead(false)
				.withKeyConditionExpression("boardId = :v1 and announcementId = :v2")
				.withExpressionAttributeValues(eav);

		List<Announcement> list = mapper.query(Announcement.class, queryExpression);
		return list;
	}
	
	public List<Announcement> getAnnouncementsByBoardId(String boardId){
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(boardId));
		
		DynamoDBQueryExpression<Announcement> queryExpression = new DynamoDBQueryExpression<Announcement>()
				.withIndexName("boardId-announcementId-index")
				.withConsistentRead(false)
				.withKeyConditionExpression("boardId = :v1")
				.withExpressionAttributeValues(eav);

		List<Announcement> list = mapper.query(Announcement.class, queryExpression);
		return list;
	}
	
	public void deleteAnnouncementsByBoardId(String boardId){
		List<Announcement> list = getAnnouncementsByBoardId(boardId);
		if(list.size()!= 0){
			mapper.batchDelete(list);
		}
	}

}