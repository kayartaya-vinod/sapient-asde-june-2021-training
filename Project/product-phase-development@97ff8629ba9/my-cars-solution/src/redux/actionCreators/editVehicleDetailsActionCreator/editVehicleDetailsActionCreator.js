/**
@Author Sumitesh Naithani - sumitesh.naithani@publicissapient.com 
*/
import {
  UPDATE_VEHICLE_DETAILS,
  UPDATE_VEHICLE_DETAILS_ERROR,
  GET_VEHICLE_BY_ID,
  GET_VEHICLE_BY_ID_ERROR,
} from "../../actionTypes";
import axios from "axios";
import {
  dealerUrl,
  updateVehicleDetailsUrl,
  JWT_TOKEN_KEY,
} from "../../../constants";

export const updateVehicleDetails = async (vehicle) => {
  try {
    axios.defaults.headers.common[
      "Authorization"
    ] = `JWT ${localStorage[JWT_TOKEN_KEY]}`;
    const { data } = await axios.put(updateVehicleDetailsUrl, vehicle);
    return { type: UPDATE_VEHICLE_DETAILS, payload: data };
  } catch (error) {
    return { type: UPDATE_VEHICLE_DETAILS_ERROR, payload: error.message };
  }
};

export const fetchVehicleById = async (id) => {
  try {
    const { data } = await axios.get(`${dealerUrl}${id}`);
    return { type: GET_VEHICLE_BY_ID, payload: data.data };
  } catch (error) {
    return {
      type: GET_VEHICLE_BY_ID_ERROR,
      payload: { error: error.message },
    };
  }
};
