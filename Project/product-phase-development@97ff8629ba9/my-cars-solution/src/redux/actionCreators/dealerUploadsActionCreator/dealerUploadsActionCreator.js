/**
@Author Sakshi Yadav Sakshi.Yadav@publicissapient.com
*/
import axios from "axios";
import {
  GET_DEALER_UPLOADS,
  GET_DEALER_UPLOADS_ERROR,
  RESET_SINGLE_VEHICLE_STATE,
} from "../../actionTypes";
import { baseUrl, JWT_TOKEN_KEY } from "../../../constants/index.js";

axios.defaults.headers.common[
  "Authorization"
] = `JWT ${localStorage[JWT_TOKEN_KEY]}`;

export const fetchDealeruploads = async (dealerId) => {
  try {

    const { data } = await axios.get(`${baseUrl}/data/my-uploads/${dealerId}`);
    return { type: GET_DEALER_UPLOADS, payload: data };
  } catch (error) {
    return {
      type: GET_DEALER_UPLOADS_ERROR,
      payload: error.response.data
    };
  }
};
export const resetSingleVehicleState = () => {
  return { type: RESET_SINGLE_VEHICLE_STATE};
}