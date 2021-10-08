import axios from "axios";
import { LOGIN_SUCCESS, LOGOUT_SUCCESS, LOGIN_FAILED } from "../../actionTypes";
import {
  loginUrl,
  JWT_TOKEN_KEY,
  NAME_KEY,
  ID_KEY,
  EMAIL_KEY,
  ROLE_KEY,
  ROLE_CUSTOMER,
  ROLE_DEALER,
} from "../../../constants";

export const fetchLoginState = async () => {
  try {
    const { data } = await axios.get(loginUrl);
    return { type: LOGIN_SUCCESS, payload: data };
  } catch (error) {
    return {
      type: LOGIN_FAILED,
      payload: error.message,
    };
  }
};

export const logOut = () => {
  localStorage.removeItem(JWT_TOKEN_KEY);
  localStorage.removeItem(ID_KEY);
  localStorage.removeItem(NAME_KEY);
  localStorage.removeItem(EMAIL_KEY);
  localStorage.removeItem(ROLE_KEY);
  localStorage.removeItem("FAV_VEHICLES");
  return {
    type: LOGOUT_SUCCESS,
    payload: { isLoggedIn: false, token: null, user: null },
  };
};

export const login = async (user) => {
  try {
    const { data } = await axios.post(loginUrl, user);
    const { token, name, email, id, userType = "customer" } = data;
    let role = ROLE_CUSTOMER;

    axios.defaults.headers.common["Authorization"] = `JWT ${token}`;

    localStorage.setItem(JWT_TOKEN_KEY, token);
    localStorage.setItem(ID_KEY, id);
    localStorage.setItem(NAME_KEY, name);
    localStorage.setItem(EMAIL_KEY, email);
    if (userType.toLowerCase() === "customer") {
      role = ROLE_CUSTOMER;
      localStorage.setItem(ROLE_KEY, ROLE_CUSTOMER);
    } else if (userType.toLowerCase() === "dealer") {
      role = ROLE_DEALER;
      localStorage.setItem(ROLE_KEY, ROLE_DEALER);
    }
    return {
      type: LOGIN_SUCCESS,
      payload: {
        isLoggedIn: true,
        token,
        user: { name, id, email, role },
      },
    };
  } catch (error) {
    if (!error.response) {
      return { type: LOGIN_FAILED, payload: error.message };
    }
    return { type: LOGIN_FAILED, payload: error.response.data.message };
  }
};
