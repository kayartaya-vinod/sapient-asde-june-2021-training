package com.sapient.asde.batch5.vehicledataservice.entity;

import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("#{@environment.getProperty('spring.data.mongodb.vehicleCollection')}")
public class VehicleDownload {
  @Id
  private String id;
  private String dealerId;
  private String tankCapacity;
  private String mediaInterface;
  private Boolean theftAlarm;
  private List<String> pictureUrls;
  private String tripMeter;
  private Integer airBagCount;
  private String description;
  private Double price;
  private String color;
  @Indexed(unique = true)
  private String vin;
  private String brand;
  private String model;
  private Integer year;
  private String vehicleType;
  private List<String> fuelType;
  private String unitsFuelConsumption;
  private String transmissionGearType;
  private String steeringWheelType;
  private String vehicleSpeed;
  private String horn;
  private String powerTrainTorque;
  private String accelaration;
  private Boolean nightMode;
  private Boolean isABS;
  private String wheelRadius;
  private String wheelSpeed;
  private Light light;
  private String ignitionTime;
  private Boolean odometer;
  private Boolean washerFluid;
  private Boolean malfunctionIndicator;
  private Integer batteryLevel;
  private Boolean airConditioning;
  private List<String> languageConfiguration;
  private String mirror;
  private Boolean childSafetyLock;
  private String topSpeedLimit;
  private Climate climate;
  private Boolean chime;

  
}