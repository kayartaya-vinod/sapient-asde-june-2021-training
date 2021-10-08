/**
@Author <Sanchit Mahto> <sanchit.mahto@publicissapient.com>
*/

import { resetPasswordUrl } from "../../../constants";
import axios from "axios";

import { EMAIL_VERIFIED, EMAIL_VERIFICATION_FAILED } from "../../actionTypes";

export const verifyEmailForForgotPassword = async (email) => {
  try {
    const { data } = await axios.get(`${resetPasswordUrl}?email=${email}`);
    return { type: EMAIL_VERIFIED, payload: data };
  } catch (error) {
    return { type: EMAIL_VERIFICATION_FAILED, payload: error.message };
  }
};
