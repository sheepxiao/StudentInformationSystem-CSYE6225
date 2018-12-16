package com.csye6225.fall2018.courseservice;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.csye6225.fall2018.courseservice.datamodel.Course;
import com.csye6225.fall2018.courseservice.datamodel.Registrar;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateRegistrar implements RequestHandler<Course, Course> {

	private static final String POST_URL = "http://studentinformationsystemxiao-env.zsaqrntfjf.us-east-2.elasticbeanstalk.com/webapi/registerOffering";
	

    public Course handleRequest(Course course, Context context) {
        context.getLogger().log("Received event: " + course);
        
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(POST_URL);
        HttpResponse response = null;
        
        ObjectMapper jsonMapper = new ObjectMapper();
        
        Registrar registrar = new Registrar();
        registrar.setOfferingId(course.getCourseId());
        registrar.setDepartment(course.getDepartment());
        registrar.setRegistrationId(course.getId());
        registrar.setOfferingType("Course");
        registrar.setPerUnitPrice("1500");

        try {
            StringEntity params = new StringEntity(jsonMapper.writeValueAsString(registrar));
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            response =  client.execute(request);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        
      
        return course;
    }
    
    
}
