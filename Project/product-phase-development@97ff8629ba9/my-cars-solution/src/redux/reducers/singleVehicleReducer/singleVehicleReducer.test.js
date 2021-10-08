/**
@Author Sakshi Yadav - sakshi.yadav@publicissapient.com 
*/

import reducer from './singleVehicleReducer';
import {
  GET_VEHICLE_BY_ID,
  GET_VEHICLE_BY_ID_ERROR,
    } from "../../actionTypes";

describe('singleVehicleReducer test', () => {
  test('should return the initial state', () => {
    expect(reducer(undefined, {})).toEqual({
        "dealerId": "",
        "tankCapacity": "",
        "mediaInterface": "",
        "theftAlarm": "",
        "pictureUrls": [],
        "tripMeter": "",
        "airBagCount": "",
        "description": "",
        "price": "",
        "color": "",
        "vin": "",
        "brand": "",
        "model": "",
        "year": "",
        "vehicleType": "",
        "fuelType": [],
        "unitsFuelConsumption": "",
         "transmissionGearType": "",
        "steeringWheelType": "",
        "vehicleSpeed": "",
        "horn": "",
        "powerTrainTorque": "",
        "accelaration": "",
        "nightMode": "",
        "isABS": false,
        "wheelRadius": "",
        "wheelSpeed": "",
        "light": {
          "fog": false,
          "hazard": false,
          "parking": false,
          "dynamicHighBeam":false,
          "automaticHeadlights": false
        },
        "ignitionTime": "",
        "odometer": false,
        "washerFluid": false,
        "malfunctionIndicator": false,
        "batteryLevel": "",
        "airConditioning": false,
        "languageConfiguration": "",
        "mirror": "automatic",
        "childSafetyLock": false,
        "topSpeedLimit": "",
        "climate": {
          "climateControl": false,
          "sunroof": false,
          "defrost": false
        },
        "chime": false,  
    });
  });

  test('should handle a vehicle object ', () => {
    const previousState = "";
    expect(reducer(previousState, { type:GET_VEHICLE_BY_ID, payload: { brand : "Honda"} })).toEqual({ brand : "Honda"});
  });

  test('should handle an error message', () => {
    const previousState = "";
    expect(reducer(previousState, { type: GET_VEHICLE_BY_ID_ERROR, payload: "error"})).toEqual({ 0: "e", 1: "r", 2: "r", 3: "o", 4: "r" });
  });
});