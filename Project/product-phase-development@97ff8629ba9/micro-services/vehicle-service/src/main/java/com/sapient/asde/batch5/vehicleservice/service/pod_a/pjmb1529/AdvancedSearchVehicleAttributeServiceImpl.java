package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1529;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import java.util.List;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Abhishek Singh abhishek.singh8@publicissapient.com
 */
@Slf4j
@Service("pjmb1529_advancedsearchvehicleattributeserviceimpl")
public class AdvancedSearchVehicleAttributeServiceImpl implements AdvancedSearchVehicleAttributeSerice {
  @Autowired
  MongoTemplate mongoTemplate;

  @Override
  public List<JsonObject> getAdvancedSearchAttributes() throws ServiceException {
    log.trace("getAdvancedSearchAttributes()");

    ProjectionOperation project = project().andExclude("_id");

    Aggregation aggregate = newAggregation(project);
    AggregationResults<JsonObject> aggregationResult = mongoTemplate.aggregate(aggregate, "advanced_search_attributes",
        JsonObject.class);

    log.trace("Results = {}", aggregationResult.getMappedResults());

    return aggregationResult.getMappedResults();
  }

}
