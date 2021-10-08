package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1527;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1143.GetVehicleByIdService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
@Slf4j
@Service("pjmb1527_vehicleComparisonWithIdsServiceImpl")
public class VehicleComparisonWithIdsServiceImpl implements VehicleComparisonWithIdsService {

  @Autowired
  VehicleRepository repo;

  @Autowired
  GetVehicleByIdService getVehicleService;

  @Autowired
  AttributesProperties properties;

  @Override
  public Map<String, Object> getVehiclesById(List<String> ids)
      throws ServiceException, JsonMappingException, JsonProcessingException {
    Iterable<Vehicle> vehicles = repo.findAllById(ids);
    List<Vehicle> sortedVehicles = new ArrayList<>();
    for (Vehicle v : vehicles)
      sortedVehicles.add(v);
    Collections.sort(sortedVehicles, (v1, v2) -> ids.indexOf(v1.getId()) - (ids.indexOf(v2.getId())));
    log.trace("getVehiclesById inside VehicleComparisonWithIdsServiceImpl");

    String imgAttribute = "pictureUrls";
    String idsAttribute = "id";
    Map<String, List<String>> map = properties.getAttributes();
    Map<String, Object> result2 = new LinkedHashMap<>();
    Map<String, Object> finalResult = new LinkedHashMap<>();

    ObjectMapper obj = new ObjectMapper();
    List<Object> array = null;

    // Content Attribute
    for (Map.Entry<String, List<String>> entry : map.entrySet()) {
      Map<String, Object> result = new LinkedHashMap<>();
      // key --> [ "basicInformation", "performance" ]
      String key = entry.getKey();
      List<String> props = map.get(key); // --> ["id", "price", "brand"]
      for (String i : props) {
        array = new ArrayList<>();
        for (Vehicle v : sortedVehicles) {
          String jsonStr = obj.writeValueAsString(v);
          ObjectNode node = new ObjectMapper().readValue(jsonStr, ObjectNode.class);
          JsonNode value = node.get(i);
          array.add(value);
        }
        result.put(i, array);
      }
      result2.put(key, result);
    }

    // Images Attribute
    List<Object> imgArray = new ArrayList<>();
    for (Vehicle v : sortedVehicles) {
      String jsonStr = obj.writeValueAsString(v);
      ObjectNode node = new ObjectMapper().readValue(jsonStr, ObjectNode.class);
      JsonNode value = node.get(imgAttribute).get(0); // pictureUrls is an array in data model, so grabing the first url
                                                      // as string.
      imgArray.add(value);
    }

    // Ids Attribute
    List<Object> idsArray = new ArrayList<>();
    for (Vehicle v : sortedVehicles) {
      String jsonStr = obj.writeValueAsString(v);
      ObjectNode node = new ObjectMapper().readValue(jsonStr, ObjectNode.class);
      JsonNode value = node.get(idsAttribute);
      idsArray.add(value);
    }

    // Pushing the data to finalResult map
    finalResult.put("content", result2);
    finalResult.put("ids", idsArray);
    finalResult.put("images", imgArray);

    return finalResult;
  }
}
