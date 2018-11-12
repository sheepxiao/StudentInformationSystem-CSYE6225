package com.csye6225.fall2018.courseservice.resources;

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice.datamodel.Board;
import com.csye6225.fall2018.courseservice.service.BoardService;

@Path("/boards")
public class BoardResource {
	BoardService boardService = new BoardService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Board> getAllBoards() {
		return boardService.getAllBoards();
	}

	@GET
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Board getBoard(@PathParam("boardId") String boardId) {
		return boardService.getBoard(boardId);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Board addBoard(Board board){
		return boardService.addBoard(board);
	}

	@PUT
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Board updateBoard(@PathParam("boardId") String boardId, 
			Board board) {
		return boardService.updateBoardInformation(boardId, board);
	}
	
	@DELETE
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Board deleteBoard(@PathParam("boardId") String boardId) {
		return boardService.deleteBoard(boardId);
	}

}
