import axios from "axios";
import { updateAccessoryUrl } from "../../../constants";
import {
  UPDATE_ACCESSORY_SUCCESS,
  UPDATE_ACCESSORY_FAILED,
  UPDATE_ACCESSORY_FAILED_WITH_RESPONSE,
} from "../../actionTypes";
/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
export const updateAccessory = async (updatedAccessory, id) => {
  try {
    const newUpdatedAccessory = { ...updatedAccessory, id };
    await axios.put(updateAccessoryUrl, newUpdatedAccessory);
    return { type: UPDATE_ACCESSORY_SUCCESS };
  } catch (error) {
    if (!error.response) {
      return { type: UPDATE_ACCESSORY_FAILED, payload: error.message };
    }
    return {
      type: UPDATE_ACCESSORY_FAILED_WITH_RESPONSE,
      payload: error.response.data.message,
    };
  }
};
