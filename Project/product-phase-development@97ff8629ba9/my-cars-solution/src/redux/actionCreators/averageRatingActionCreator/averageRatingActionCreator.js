/**
@Author Manvendra Singh manvendra.singh@publicissapient.com
*/

import axios from "axios";
import {
  GET_AVERAGE_RATING,
  GET_AVERAGE_RATING_ERROR,
} from "../../actionTypes";
import { vehiclesUrl } from "../../../constants/index.js";

export const getAverageRating = async (vehicleId) => {
  try {
    const { data } = await axios.get(`${vehiclesUrl}average-rating/${vehicleId}`);
    
    return { type: GET_AVERAGE_RATING, payload: data };
  } catch (error) {
    return { type: GET_AVERAGE_RATING_ERROR, payload: error.message };
  }
};
