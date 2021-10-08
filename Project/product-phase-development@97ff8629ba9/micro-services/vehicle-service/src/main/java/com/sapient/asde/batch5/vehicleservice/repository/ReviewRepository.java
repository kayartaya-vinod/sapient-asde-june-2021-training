package com.sapient.asde.batch5.vehicleservice.repository;

import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.Feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Repository
public class ReviewRepository {

  @Autowired
  FeedbackRepository repo;
  RestTemplate restTemplate = new RestTemplate();
  @Value("${custUrl}")
  String custUrl;

  public String[][] getArray(String vid) {
    List<Feedback> array = repo.findByVehicleId(vid);
    int n = array.size();
    String[][] newArray = new String[n][5];
    for (int i = 0; i < array.size(); i++) {
      String name;
      try {
        name = restTemplate.getForObject(custUrl + array.get(i).getUserId(), String.class);

      } catch (Exception e) {
        log.trace("inside failed");
        continue;
      }

      if (array.get(i).getReview() != null)
        newArray[i][0] = array.get(i).getReview();
      else
        newArray[i][0] = null;
      newArray[i][1] = name;
      if (array.get(i).getRating() != null)
        newArray[i][2] = array.get(i).getRating().toString();
      else
        newArray[i][2] = null;
      newArray[i][3] = array.get(i).getUserId();
      newArray[i][4] = array.get(i).getVehicleId();
    }
    return newArray;
  }
}
