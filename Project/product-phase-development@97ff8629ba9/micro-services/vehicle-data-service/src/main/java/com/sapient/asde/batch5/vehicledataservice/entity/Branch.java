package com.sapient.asde.batch5.vehicledataservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Branch {
     private String buildingNo;
     private String street;
     private String landMark;
     private String city;
     private String state;
     private String pinCode;
}
