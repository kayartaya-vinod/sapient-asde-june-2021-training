import axios from "axios";
import { SIGNUP, SIGNUP_FAILED } from "../../actionTypes";
import { addCustomerUrl, registerUrl } from "../../../constants";

export const signup = async (user) => {
  try {
    const { data } = await axios.post(registerUrl, user);
    await axios.post(addCustomerUrl,{"userId": data.id}); //if user is customer
    return { type: SIGNUP, payload: data };
  } catch (error) {
    if (!error.response) {
      return { type: SIGNUP_FAILED, payload: error.message };
    }
    return {
      type: SIGNUP_FAILED,
      payload: error.response.data.message,
    };
  }
};
