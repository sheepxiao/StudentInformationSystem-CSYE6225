package com.csye6225.fall2018.courseservice.service;

import java.util.*;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.courseservice.datamodel.Board;
import com.csye6225.fall2018.courseservice.datamodel.Course;

public class BoardService {
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper; 
	
	public BoardService(){
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	//GET, get all boards
	public List<Board> getAllBoards() {	
		//Getting the list
	    DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
			  .withIndexName("boardId-index")
			  .withConsistentRead(false);

		List<Board> list =  mapper.scan(Board.class, scanExpression);
	    return list ;
	}
	
	//GET, get a board by ID
	public Board getBoard(String boardId) {
		List<Board> list = getBoardFromDynamoDB(boardId);
		return list.size() != 0 ? list.get(0) : null;
	}
		
	//POST, add a new board
	public Board addBoard(Board board) {
		 Board newBoard = new Board(board.getBoardId(), board.getCourseId());
		 mapper.save(newBoard);
				
		 System.out.println("Item added:");
	     System.out.println(newBoard.toString());
		 return newBoard;
	}
	
	//PUT, update a board information
	public Board updateBoardInformation(String boardId, Board board) {	
		List<Board> list = getBoardFromDynamoDB(boardId);
		Board oldBoard = null;
		if(list.size() != 0) {
			oldBoard = list.get(0);
			oldBoard.setCourseId(board.getCourseId());
			mapper.save(oldBoard);
			System.out.println("Item updated:");
			System.out.println(oldBoard.toString());
		}
		return oldBoard;
	}
				
	//DELETE, delete a board
	public Board deleteBoard(String boardId) {
	   List<Board> list = getBoardFromDynamoDB(boardId);
	   Board board = null;
	   if(list.size() != 0){
		   board = list.get(0);
		   String deleteBoardId = board.getBoardId();
		   mapper.delete(board);
		   
		   // deleting corresponding announcements in Announcements table 
		   AnnouncementService annService = new AnnouncementService();
		   annService.deleteAnnouncementsByBoardId(deleteBoardId);
		   
		   Board deletedBoard = mapper.load(Board.class, board.getId());		
		   if (deletedBoard == null) {
			    System.out.println("The board is deleted.");
			    System.out.println(board.toString());
		   }
	   }
				
		 return board;
    }
		
	public List<Board> getBoardFromDynamoDB(String boardId){
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(boardId));

		DynamoDBQueryExpression<Board> queryExpression = new DynamoDBQueryExpression<Board>()
			.withIndexName("boardId-index")
			.withConsistentRead(false)
			.withKeyConditionExpression("boardId = :v1")
			.withExpressionAttributeValues(eav);

		List<Board> list =  mapper.query(Board.class, queryExpression);
		return list;
   }
			
		
}
