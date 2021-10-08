package com.sapient.asde.batch5.vehicleservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("sponsored_vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SponsoredVehicles {
    
    @Id
    private String id;
    private String vid;

}
