package com.sapient.asde.batch5.vehicleservice.repository;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;

import org.springframework.data.domain.Page;

/**
 * @author Deepthi S deepthi.s@publicissapient.com
 */
public interface CustomVehicleRepository {
    Page<Vehicle> findByAdvSearch(String[] vehicleType, String[] brand, Double minPrice, Double maxPrice,String[] color, String[] fuelType, Integer airBagCount, Integer year, Integer page);
}
