/**
 * @author Sakshi Yadav  sakshi.yadav@publicissapient.com
 */



package com.sapient.asde.batch5.vehicleservice.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("vehicle_accessories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleAccessory {
  @Id
  private String id;

  private String dealerId;
 
  @TextIndexed()
  private String name;
 
  @TextIndexed()
  private Double price;

  @TextIndexed()
  private String description;

  private List<String> pictureUrls;
}




