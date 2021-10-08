package com.sapient.asde.batch5.vehicleservice.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("comparisons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleComparison {
  @Id
  private String id;
  private String userId; // _id of users collection
  private List<String> vehicleIds; // _id s of selected vehicles for comparison
  private String comparisonMatrixName;
  private Date createdDate;
}
