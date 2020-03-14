package com.example.MyApp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "database_sequences")
public class data_sequences
{
	@Id
	private String id;
	
	private long seq;
	
	public data_sequences() {}
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public long getSeq()
	{
		return seq;
	}
	
	public void setId(long seq)
	{
		this.seq = seq;
	}
}
