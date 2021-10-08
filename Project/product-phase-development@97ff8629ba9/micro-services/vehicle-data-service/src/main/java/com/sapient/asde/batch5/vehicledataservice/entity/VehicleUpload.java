package com.sapient.asde.batch5.vehicledataservice.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document("#{@environment.getProperty('spring.data.mongodb.vehicleUploadCollection')}")
public class VehicleUpload {
    @Id
    private String id;
    private String dealerId;
    private String fileName;
    private String dateAndTime;
    private Integer noOfVehicles;
    private Double successRatio;

    public VehicleUpload(String dealerId,String fileName,String dateAndTime,Integer noOfVehicles,Double successRatio)
    {
        this.dealerId = dealerId;
        this.fileName = fileName;
        this.dateAndTime = dateAndTime;
        this.noOfVehicles = noOfVehicles;
        this.successRatio = successRatio;
    }
}