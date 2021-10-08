package com.sapient.asde.batch5.vehicleservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Climate {
  private Boolean climateControl;
  private Boolean sunroof;
  private Boolean defrost;
}
