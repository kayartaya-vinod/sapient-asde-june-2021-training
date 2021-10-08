package com.sapient.asde.batch5.customerservice.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
  @Id
  private String id;
  private String userId;
  private String alternateEmail;
  private Address address;
  private String contactNo;
  private String alternateContactNo;
  private List<String> wishlist;
}
