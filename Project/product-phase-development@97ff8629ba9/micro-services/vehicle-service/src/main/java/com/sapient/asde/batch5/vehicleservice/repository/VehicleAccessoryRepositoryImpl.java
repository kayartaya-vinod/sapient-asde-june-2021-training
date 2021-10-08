package com.sapient.asde.batch5.vehicleservice.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import java.util.List;


import org.bson.json.JsonObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

/**
 * @author Neha neha1@publicissapient.com
 */

@Repository
public class VehicleAccessoryRepositoryImpl implements VehicleAccessoryRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<JsonObject> vehicleAccessory(String id) {
        ObjectId objectId = new ObjectId(id);
        ProjectionOperation project = project().andExclude("_id");
        MatchOperation matchStage = Aggregation.match(Criteria.where("_id").is(objectId));
        Aggregation aggregate = Aggregation.newAggregation(matchStage, project);

        AggregationResults<JsonObject> aggregationResult = mongoTemplate.aggregate(aggregate, "vehicle_accessories",
                JsonObject.class);

        return aggregationResult.getMappedResults();
    }


    
    

}
