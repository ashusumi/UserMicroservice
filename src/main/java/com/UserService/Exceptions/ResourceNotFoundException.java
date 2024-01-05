package com.UserService.Exceptions;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(){
		super("Resource Not Found in db!");
	}
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
	
	

	
}
