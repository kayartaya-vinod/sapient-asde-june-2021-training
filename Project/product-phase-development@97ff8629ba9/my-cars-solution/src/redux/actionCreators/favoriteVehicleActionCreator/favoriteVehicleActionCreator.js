/**
@Author Aravind M Krishnan - aravind.m.krishnan@publicissapient.com 
@Author Deepthi - deepthi.s@publicissapient.com
*/

import axios from "axios";
import {
  GET_FAVORITE_VEHICLES,
  GET_FAVORITE_VEHICLES_ERROR,
  UPDATE_FAVORITES,
  UPDATE_FAVORITES_ERROR,
} from "../../actionTypes";
import { customerUrl, favUrl, JWT_TOKEN_KEY } from "../../../constants";

axios.defaults.headers.common[
  "Authorization"
] = `JWT ${localStorage[JWT_TOKEN_KEY]}`;

export const getFavoriteVehicles = async () => {
  try {
    const { data } = await axios.get(`${customerUrl}favorites`);
    //  const { data } = await axios.get(
    //    `http://localhost:8096/api/customers/favorites`
    //  );
    return { type: GET_FAVORITE_VEHICLES, payload: data };
  } catch (error) {
    return {
      type: GET_FAVORITE_VEHICLES_ERROR,
      payload: { error: error.message },
    };
  }
};
export const updateFavorites = async (v) => {
  try {
    const { data } = await axios.put(`${favUrl}${v.id}`);
    //const { data } = await axios.put(`http://localhost:8080/api/customers/favorites/${v.id}`)
    if (data.success) {
      return { type: UPDATE_FAVORITES, payload: v };
    } else {
      return { type: UPDATE_FAVORITES_ERROR, payload: data.msg };
    }
  } catch (error) {
    return { type: UPDATE_FAVORITES_ERROR, payload: error.message };
  }
};
