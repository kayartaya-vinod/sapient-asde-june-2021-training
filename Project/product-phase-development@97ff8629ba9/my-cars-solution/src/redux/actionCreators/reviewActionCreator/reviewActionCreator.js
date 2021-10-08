/**
@Author Rohit Bhatt rohit.bhatt1@publicissapient.com
*/
import axios from "axios";
import { GET_MY_REVIEW, GET_MY_REVIEW_ERROR, POST_MY_REVIEW, POST_MY_REVIEW_ERROR} from '../../actionTypes';
import { vehiclesUrl } from "../../../constants/index.js";

export const postReview = async(vehicleId, review) => {
  try {
    const { data } = await axios.post(`${vehiclesUrl}${vehicleId}/reviews`, {review} );
    return { type:POST_MY_REVIEW, payload: data}
  } catch (error) {
      return { type: POST_MY_REVIEW_ERROR, payload: error.message };
  }
};


export const getReview = async(vehicleId) => {
  try {
    const { data } = await axios.get(`${vehiclesUrl}${vehicleId}/reviews`);
    return { type:GET_MY_REVIEW, payload: data}
  } catch (error) {
      return { type: GET_MY_REVIEW_ERROR, payload: error.message };
  }

};

