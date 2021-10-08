package com.sapient.asde.batch5.vehicleservice.repository;

import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;

@Repository
public class SearchRepository {
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<Vehicle> searchByText(String text,Integer page)  {

    TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny( text);

    Query query = TextQuery.queryText(criteria).sortByScore();

    List<Vehicle> vehicle = mongoTemplate.find(query.limit( page), Vehicle.class);
    return vehicle;
    }
}
