package com.sapient.asde.batch5.vehicledataservice.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Climate {
  private Boolean climateControl;
  private Boolean sunroof;
  private Boolean defrost;
  public Climate(Boolean climateControl, Boolean sunroof, Boolean defrost) {
    this.climateControl = climateControl;
    this.sunroof = sunroof;
    this.defrost = defrost;
  }
  @Override
  public String toString() {
    return climateControl + "," + defrost + "," + sunroof;
  }
  
}
