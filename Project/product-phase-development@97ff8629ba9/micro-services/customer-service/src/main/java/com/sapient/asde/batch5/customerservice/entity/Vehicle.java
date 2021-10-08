package com.sapient.asde.batch5.customerservice.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
  @Id
  private String id;
  private String dealerId;

  @TextIndexed()
  private String tankCapacity;

  private String mediaInterface;
  private Boolean theftAlarm;
  private List<String> pictureUrls;
  private String tripMeter;
  private Integer airBagCount;

  @TextIndexed()
  private String description;

  @TextIndexed()
  private Double price;
  
  @TextIndexed()
  private String color;

  private String vin;

  @TextIndexed()
  private String brand;

  @TextIndexed()
  private String model;

  private Integer year;

  @TextIndexed()
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