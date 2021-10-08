/**
@author Abhishek Singh abhishek.singh8@publicisssapient.com
*/


import axios from 'axios';
import { getDealerAccessoriesUrl, JWT_TOKEN_KEY, AccessoryDeleteUrl } from '../../../constants';
import { GET_DEALER_VEHICLE_ACCESSORIES, RESET_DEALER_VEHICLE_ACCESSORIES_STATE, SELECT_DEALER_ACCESSORY, DELETE_ACCESSORY } from "../../actionTypes";

axios.defaults.headers.common[
  'Authorization'
] = `JWT ${localStorage[JWT_TOKEN_KEY]}`;

export const getDealerAccessories = async (page = 1) => {
  try {
    const { data } = await axios.get(`${getDealerAccessoriesUrl}?page=${page}`);
    return { type: GET_DEALER_VEHICLE_ACCESSORIES, payload: data.data };
  } catch (error) {
    if (!error.response) {
      return { type: GET_DEALER_VEHICLE_ACCESSORIES, error: error.message, payload: { content: [], first: true, last: true } };
    } else {
      return { type: GET_DEALER_VEHICLE_ACCESSORIES, error: error.response.data.message, payload: { content: [], first: true, last: true } };
    }
  }
};

export const deleteAccessory = async (id) => {
  try {
    await axios.delete(AccessoryDeleteUrl + "?id=" + id);
    return { type: DELETE_ACCESSORY, payload: id};
  } catch (error) {
    if (!error.response) {
      return { type: 'NO_DISPATCH_ACTION', payload: error.message };
    } else {
      return { type: 'NO_DISPATCH_ACTION', payload: error.response.data.message ? error.response.data.message : "Something went wrong, please try again later." };
    }
  }
};

export const selectDealerAccessory = (id) => {
  return { type: SELECT_DEALER_ACCESSORY, payload: id };
};

export const resetDealerAccessories = () => {
  return { type: RESET_DEALER_VEHICLE_ACCESSORIES_STATE };
};