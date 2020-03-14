package com.example.MyApp.service;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import  java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired; //Annotation
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;    //Annotation 

import com.example.MyApp.model.data_sequences;

@Service
public class SequenceGeneratorService 
{
	private MongoOperations mongoOperations;
	
	@Autowired
	public SequenceGeneratorService(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}
	
	public long generateSequence(String seqName) 
	{
		data_sequences counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
				new Update().inc("seq",1), options().returnNew(true).upsert(true),data_sequences.class);
		return !Objects.isNull(counter)?counter.getSeq() : 1;
	}	
}
