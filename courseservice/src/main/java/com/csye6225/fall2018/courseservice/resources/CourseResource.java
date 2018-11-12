package com.csye6225.fall2018.courseservice.resources;

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice.datamodel.Course;
import com.csye6225.fall2018.courseservice.service.CourseService;

@Path("/courses")
public class CourseResource {

	CourseService courseService = new CourseService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getAllCourses() {
		return courseService.getAllCourses();	
	}
	
	@GET
	@Path("/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course getCourse(@PathParam("courseId") String courseId) {
		return courseService.getCourse(courseId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course addProfessor(Course course) {
		return courseService.addCourse(course);
	}
	
	@PUT
	@Path("/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course updateCourse(@PathParam("courseId") String courseId, Course course) {
		return courseService.updateCourseInformation(courseId, course);
	}
	
	@DELETE
	@Path("/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course deleteProfessor(@PathParam("courseId") String courseId) {
		return courseService.deleteCourse(courseId);
	}
}
