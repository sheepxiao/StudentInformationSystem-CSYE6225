package com.csye6225.fall2018.courseservice.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.courseservice.datamodel.Registrar;

public class RegistrarService {

	static DynamoDbConnector dynamoDb;
    DynamoDBMapper mapper; 
    
    public RegistrarService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
    
    public Registrar addRegistra(Registrar registrar) {
    	mapper.save(registrar);
    	return registrar; 	
    }
    
    
}
