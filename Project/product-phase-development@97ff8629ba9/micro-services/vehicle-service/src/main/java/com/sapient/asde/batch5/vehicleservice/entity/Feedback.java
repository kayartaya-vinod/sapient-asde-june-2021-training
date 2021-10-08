package com.sapient.asde.batch5.vehicleservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("feedbacks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
  @Id
  private String id;
  private String vehicleId; // _id of vehicles collection
  private String userId; // _id of users collection

  private String review;

  private Integer rating;
}
