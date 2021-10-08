package com.sapient.asde.batch5.vehicledataservice.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document("#{@environment.getProperty('spring.data.mongodb.vehicleCollection')}")
public class Vehicle {
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

  public Vehicle(String id, String dealerId, String tankCapacity, String mediaInterface, Boolean theftAlarm,
      List<String> pictureUrls, String tripMeter, Integer airBagCount, String description, Double price, String color,
      String vin, String brand, String model, Integer year, String vehicleType, List<String> fuelType,
      String unitsFuelConsumption, String transmissionGearType, String steeringWheelType, String vehicleSpeed,
      String horn, String powerTrainTorque, String accelaration, Boolean nightMode, Boolean isABS, String wheelRadius,
      String wheelSpeed, Light light, String ignitionTime, Boolean odometer, Boolean washerFluid,
      Boolean malfunctionIndicator, Integer batteryLevel, Boolean airConditioning, List<String> languageConfiguration,
      String mirror, Boolean childSafetyLock, String topSpeedLimit, Climate climate, Boolean chime) {
    this.id = id;
    this.dealerId = dealerId;
    this.tankCapacity = tankCapacity;
    this.mediaInterface = mediaInterface;
    this.theftAlarm = theftAlarm;
    this.pictureUrls = pictureUrls;
    this.tripMeter = tripMeter;
    this.airBagCount = airBagCount;
    this.description = description;
    this.price = price;
    this.color = color;
    this.vin = vin;
    this.brand = brand;
    this.model = model;
    this.year = year;
    this.vehicleType = vehicleType;
    this.fuelType = fuelType;
    this.unitsFuelConsumption = unitsFuelConsumption;
    this.transmissionGearType = transmissionGearType;
    this.steeringWheelType = steeringWheelType;
    this.vehicleSpeed = vehicleSpeed;
    this.horn = horn;
    this.powerTrainTorque = powerTrainTorque;
    this.accelaration = accelaration;
    this.nightMode = nightMode;
    this.isABS = isABS;
    this.wheelRadius = wheelRadius;
    this.wheelSpeed = wheelSpeed;
    this.light = light;
    this.ignitionTime = ignitionTime;
    this.odometer = odometer;
    this.washerFluid = washerFluid;
    this.malfunctionIndicator = malfunctionIndicator;
    this.batteryLevel = batteryLevel;
    this.airConditioning = airConditioning;
    this.languageConfiguration = languageConfiguration;
    this.mirror = mirror;
    this.childSafetyLock = childSafetyLock;
    this.topSpeedLimit = topSpeedLimit;
    this.climate = climate;
    this.chime = chime;
  }

  public Vehicle( String dealerId, String tankCapacity, String mediaInterface, Boolean theftAlarm,
      List<String> pictureUrls, String tripMeter, Integer airBagCount, String description, Double price, String color,
      String vin, String brand, String model, Integer year, String vehicleType, List<String> fuelType,
      String unitsFuelConsumption, String transmissionGearType, String steeringWheelType, String vehicleSpeed,
      String horn, String powerTrainTorque, String accelaration, Boolean nightMode, Boolean isABS, String wheelRadius,
      String wheelSpeed, Light light, String ignitionTime, Boolean odometer, Boolean washerFluid,
      Boolean malfunctionIndicator, Integer batteryLevel, Boolean airConditioning, List<String> languageConfiguration,
      String mirror, Boolean childSafetyLock, String topSpeedLimit, Climate climate, Boolean chime) {
    this.dealerId = dealerId;
    this.tankCapacity = tankCapacity;
    this.mediaInterface = mediaInterface;
    this.theftAlarm = theftAlarm;
    this.pictureUrls = pictureUrls;
    this.tripMeter = tripMeter;
    this.airBagCount = airBagCount;
    this.description = description;
    this.price = price;
    this.color = color;
    this.vin = vin;
    this.brand = brand;
    this.model = model;
    this.year = year;
    this.vehicleType = vehicleType;
    this.fuelType = fuelType;
    this.unitsFuelConsumption = unitsFuelConsumption;
    this.transmissionGearType = transmissionGearType;
    this.steeringWheelType = steeringWheelType;
    this.vehicleSpeed = vehicleSpeed;
    this.horn = horn;
    this.powerTrainTorque = powerTrainTorque;
    this.accelaration = accelaration;
    this.nightMode = nightMode;
    this.isABS = isABS;
    this.wheelRadius = wheelRadius;
    this.wheelSpeed = wheelSpeed;
    this.light = light;
    this.ignitionTime = ignitionTime;
    this.odometer = odometer;
    this.washerFluid = washerFluid;
    this.malfunctionIndicator = malfunctionIndicator;
    this.batteryLevel = batteryLevel;
    this.airConditioning = airConditioning;
    this.languageConfiguration = languageConfiguration;
    this.mirror = mirror;
    this.childSafetyLock = childSafetyLock;
    this.topSpeedLimit = topSpeedLimit;
    this.climate = climate;
    this.chime = chime;
  }
  
}