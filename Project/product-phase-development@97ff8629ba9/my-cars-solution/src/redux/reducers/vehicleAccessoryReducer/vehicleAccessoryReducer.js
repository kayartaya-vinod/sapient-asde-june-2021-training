/**
@Author Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/

import {
  GET_VEHICLE_ACCESSORY,
  GET_VEHICLE_ACCESSORY_ERROR,
} from "../../actionTypes";

const vehicleAccessoryReducer = (state = {}, action = {}) => {
  switch (action.type) {
    case GET_VEHICLE_ACCESSORY:
      return { ...action.payload };
    case GET_VEHICLE_ACCESSORY_ERROR:
      return { ...action.payload };
    default:
      return state;
  }
};

export default vehicleAccessoryReducer;

