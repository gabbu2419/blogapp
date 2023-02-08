package com.myblog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//steps for custom exception handling
/*
 1.create class and it should be child class of RuntineException
 2. create variables for the messages u want to display
 3. generate constructor. and in super add string format
 4. generate getters
*/


@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{   
	
	
	private String resourceName;  //in web services, resource name is nothing but table name i.e here resource name is Post table
    private String fieldName;
    private long fieldValue;
    
    
    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName,fieldValue));  //super keyword to give custom message
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}


	public String getResourceName() {
		return resourceName;
	}


	public String getFieldName() {
		return fieldName;
	}


	public long getFieldValue() {
		return fieldValue;
	}
    
    
	

}
