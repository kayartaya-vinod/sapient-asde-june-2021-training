/**
@Author Sumitesh Naithani sumitesh.naithani@publicissapient.com
*/
import axios from "axios";
import { addAccessoryUrl } from "../../../constants";
import { ADD_ACCESSORY, ADD_ACCESSORY_ERROR } from "../../actionTypes";

export const addAccessory = async (accessory) => {
  try {
    const { data } = await axios.post(`${addAccessoryUrl}`,accessory);
    return { type: ADD_ACCESSORY, payload: data };
  } catch (error) {
    if (!error.response) {
      return { type: ADD_ACCESSORY_ERROR, payload: error.message };
    }
    return { type: ADD_ACCESSORY_ERROR, payload: error.response.data.message };
  }
};
