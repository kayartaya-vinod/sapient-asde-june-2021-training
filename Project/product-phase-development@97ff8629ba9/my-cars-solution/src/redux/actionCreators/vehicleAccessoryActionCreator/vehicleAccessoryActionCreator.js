/**
@Author Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/

import axios from "axios";
import { vehicleAccessoryUrl } from "../../../constants";
import {
  GET_VEHICLE_ACCESSORY,
  GET_VEHICLE_ACCESSORY_ERROR,
} from "../../actionTypes";

export const fetchVehicleAccessoryById = async (id) => {
  try {
    const { data } = await axios.get(vehicleAccessoryUrl + id);
    return { type: GET_VEHICLE_ACCESSORY, payload: data.data };
  } catch (error) {
    return { type: GET_VEHICLE_ACCESSORY_ERROR, payload: error.message };
  }
};
