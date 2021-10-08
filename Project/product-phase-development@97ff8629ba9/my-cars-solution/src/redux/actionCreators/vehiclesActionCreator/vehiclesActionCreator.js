/**
@Author Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/

import axios from "axios";
import { vehiclesUrl } from "../../../constants";
import {
  ADD_VEHICLE,
  ADD_VEHICLE_ERROR,
  GET_VEHICLES,
  GET_VEHICLES_1,
  GET_VEHICLES_ERROR,
} from "../../actionTypes";

export const fetchVehicles = async (page = 1) => {
  try {
    const { data } = await axios.get(`${vehiclesUrl}?page=${page}`);
    if (page === 1) return { type: GET_VEHICLES_1, payload: data };
    return { type: GET_VEHICLES, payload: data };
  } catch (error) {
    return {
      type: GET_VEHICLES_ERROR,
      payload: { error: error.message },
    };
  }
};

/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
export const addVehicles = async (vehicle) => {
  const {
    fog,
    hazard,
    parking,
    dynamicHighBeam,
    automaticHeadlights,
    climateControl,
    sunroof,
    defrost,
    dealerId,
    tankCapacity,
    mediaInterface,
    theftAlarm,
    pictureUrls,
    tripMeter,
    airBagCount,
    description,
    price,
    color,
    vin,
    brand,
    model,
    year,
    vehicleType,
    fuelType,
    unitsFuelConsumption,
    transmissionGearType,
    steeringWheelType,
    vehicleSpeed,
    horn,
    powerTrainTorque,
    accelaration,
    nightMode,
    isABS,
    wheelRadius,
    wheelSpeed,
    ignitionTime,
    odometer,
    washerFluid,
    malfunctionIndicator,
    batteryLevel,
    airConditioning,
    mirror,
    childSafetyLock,
    topSpeedLimit,
    chime,
  } = vehicle;

  const light = { fog, hazard, parking, dynamicHighBeam, automaticHeadlights };
  const climate = { climateControl, sunroof, defrost };

  const newVehicle = {
    dealerId,
    tankCapacity,
    mediaInterface,
    theftAlarm,
    pictureUrls,
    tripMeter,
    airBagCount,
    description,
    price,
    color,
    vin,
    brand,
    model,
    year,
    vehicleType,
    fuelType,
    unitsFuelConsumption,
    transmissionGearType,
    steeringWheelType,
    vehicleSpeed,
    horn,
    powerTrainTorque,
    accelaration,
    nightMode,
    isABS,
    wheelRadius,
    wheelSpeed,
    ignitionTime,
    odometer,
    washerFluid,
    malfunctionIndicator,
    batteryLevel,
    airConditioning,
    mirror,
    childSafetyLock,
    topSpeedLimit,
    light,
    climate,
    chime,
  };

  try {
    const { data } = await axios.post(`${vehiclesUrl}`, newVehicle);
    return { type: ADD_VEHICLE, payload: data };
  } catch (error) {
    if (!error.response) {
      return { type: ADD_VEHICLE_ERROR, payload: error.message };
    }
    return { type: ADD_VEHICLE_ERROR, payload: error.response.data.message };
  }
};
