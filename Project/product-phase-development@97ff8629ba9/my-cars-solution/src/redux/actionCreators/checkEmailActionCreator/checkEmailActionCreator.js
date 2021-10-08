import axios from "axios";
import { checkEmailUrl} from "../../../constants";
import {
    CHECK_EMAIL,
    CHECK_EMAIL_ERROR,
} from "../../actionTypes";

export const checkEmail = async (token) => {
    try {
        const {data} = await axios.get(`${checkEmailUrl}?jwt=${token}`);
        return { type: CHECK_EMAIL, payload: data };
    } catch (error) {
        return { type: CHECK_EMAIL_ERROR, payload: error.message };
    }
};