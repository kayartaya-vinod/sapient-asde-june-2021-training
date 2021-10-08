/**
@Author Jaswant Arya - jaswant.arya@publicissapient.com
*/

import axios from "axios";
import {
  UPDATE_RATING,
  UPDATE_RATING_ERROR,
  GET_MY_RATING,
  GET_MY_RATING_ERROR,
} from "../../actionTypes";
import { vehiclesUrl } from "../../../constants/index.js";

export const updateRating = async (vehicleId, rating) => {
  try {
    const payload = {
      "vehicleId": vehicleId,
      "rating":rating
    };
    const { data } = await axios.post(`${vehiclesUrl}rating/`,payload );

    return { type: UPDATE_RATING, payload: data };
  } catch (error) {
    return { type: UPDATE_RATING_ERROR, payload: error.message };
  }
};

export const getRating = async (vehicleId) => {
  try {
    const { data } = await axios.get(`${vehiclesUrl}rating/${vehicleId}`);
    
    return { type: GET_MY_RATING, payload: data };
  } catch (error) {
    return { type: GET_MY_RATING_ERROR, payload: error.message };
  }
};
