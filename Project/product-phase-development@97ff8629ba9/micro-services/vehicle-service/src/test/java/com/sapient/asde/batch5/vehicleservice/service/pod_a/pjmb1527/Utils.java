
/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1527;

import java.util.ArrayList;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;

public final class Utils {

  private Utils() {
    throw new IllegalStateException("Utility class");
  }

  public static Iterable<Vehicle> getVehicles() {
    Iterable<Vehicle> vehicles = new ArrayList<>();
    Vehicle vehicle1 = new Vehicle();

    ((ArrayList<Vehicle>) vehicles).add(vehicle1);

    return vehicles;
  }

}
