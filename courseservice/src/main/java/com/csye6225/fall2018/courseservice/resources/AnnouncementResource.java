package com.csye6225.fall2018.courseservice.resources;

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice.datamodel.Announcement;
import com.csye6225.fall2018.courseservice.service.AnnouncementService;

@Path("/announcements")
public class AnnouncementResource {
	AnnouncementService annService = new AnnouncementService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Announcement> getAllAnnouncements() {
		return annService.getAllAnnouncements();
	}

	@GET
	@Path("/{boardId}-{announcementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Announcement getAnnouncement(@PathParam("boardId") String boardId, @PathParam("announcementId")String announcementId) {
		return annService.getAnnouncement(boardId, announcementId);
	}
	
	@GET
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Announcement> getAnnouncementsByBoardId(@PathParam("boardId") String boardId) {
		return annService.getAnnouncementsByBoardId(boardId);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Announcement addAnnouncement(Announcement announcement){
		return annService.addAnnouncement(announcement);
	}

	@PUT
	@Path("/{boardId}-{announcementId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Announcement updateAnnouncement(@PathParam("boardId") String boardId, @PathParam("announcementId")String announcementId,
			Announcement announcement) {
		return annService.updateAnnouncementInformation(boardId, announcementId, announcement);
	}
	
	@DELETE
	@Path("/{boardId}-{announcementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Announcement deleteAnnouncement(@PathParam("boardId") String boardId,@PathParam("announcementId")String announcementId) {
		return annService.deleteAnnouncement(boardId, announcementId);
	}
}
