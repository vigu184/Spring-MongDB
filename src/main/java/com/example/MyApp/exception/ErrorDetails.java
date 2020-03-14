package com.example.MyApp.exception;

import java.util.Date;


public class ErrorDetails 
{
	private Date timestamp;
	private String message;
	private String details;
	
	public ErrorDetails (Date timestamp, String message, String details) 
	{
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
	public Date timestamp() 
	{
		return timestamp; //Gives time-stamp of Error
	}
	
	public String message() 
	{
		return message; //Throws the error message
	}
	
	public String details() 
	{
		return details; //Gives details of the error
	}

}
