package com.sapient.asde.batch5.vehicledataservice.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Light {

  private Boolean fog;
  private Boolean hazard;
  private Boolean parking;
  private Boolean dynamicHighBeam;
  private Boolean automaticHeadlights;

  public Light(Boolean fog, Boolean hazard, Boolean parking, Boolean dynamicHighBeam, Boolean automaticHeadlights) {
    this.fog = fog;
    this.hazard = hazard;
    this.parking = parking;
    this.dynamicHighBeam = dynamicHighBeam;
    this.automaticHeadlights = automaticHeadlights;
  }

  @Override
  public String toString() {
    return automaticHeadlights + "," + dynamicHighBeam + "," + fog
        + "," + hazard + "," + parking;
  }
  

}
