package com.sapient.asde.batch5.vehicleservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Light {

  private Boolean fog;
  private Boolean hazard;
  private Boolean parking;
  private Boolean dynamicHighBeam;
  private Boolean automaticHeadlights;

}
