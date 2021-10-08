/**
 * @author Abhishek Rajgaria, abhishek.rajgaria@publicissapient.com
 */

/**
 * @author Pritam Patel, pritam.patel@publicisspaient.com
 */

import { vehicleCompareUrl } from "../../../constants";
import axios from "axios";
import { FETCH_VEHICLE_COMPARE, FETCH_VEHICLE_COMPARE_ERROR } from "../../actionTypes";


export const fetchVehiclesForCompare = async (query = "") => {
  try {
    const { data } = await axios.get(`${vehicleCompareUrl}${query}`);
    return { type: FETCH_VEHICLE_COMPARE, payload: data.data };
  }
  catch (error) {
    return { type: FETCH_VEHICLE_COMPARE_ERROR, payload: error.message };
  }
};
