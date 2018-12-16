package com.csye6225.fall2018.courseservice.datamodel;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="registrar")
public class Registrar {
	private String registrationId;
	private String offeringId;
	private String offeringType;
	private String department;
	private String perUnitPrice;
	
	public Registrar() {
		
	}

	@DynamoDBHashKey(attributeName = "registrationId")
	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	
	@DynamoDBAttribute(attributeName="offeringId")
	public String getOfferingId() {
		return offeringId;
	}

	public void setOfferingId(String offeringId) {
		this.offeringId = offeringId;
	}
    
	@DynamoDBAttribute(attributeName="offeringType")
	public String getOfferingType() {
		return offeringType;
	}

	public void setOfferingType(String offeringType) {
		this.offeringType = offeringType;
	}
    
	@DynamoDBAttribute(attributeName="department")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
    
	@DynamoDBAttribute(attributeName="perUnitPrice")
	public String getPerUnitPrice() {
		return perUnitPrice;
	}

	public void setPerUnitPrice(String perUnitPrice) {
		this.perUnitPrice = perUnitPrice;
	}
    
    
   
}
