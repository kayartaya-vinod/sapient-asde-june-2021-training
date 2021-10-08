import axios from "axios";
import {
  GET_VEHICLE_FILTER_ATTRIBUTE,
  GET_VEHICLE_FILTER_ATTRIBUTE_ERROR,
  UPDATE_VEHICLE_FILTER,
} from "../../actionTypes";
import { advSearchAttributesUrl } from "../../../constants";
export const getFilterAttributes = async () => {
  try {
    const { data } = await axios.get(`${advSearchAttributesUrl}`);
    return { type: GET_VEHICLE_FILTER_ATTRIBUTE, payload: data.data };
  } catch (error) {
    return { type: GET_VEHICLE_FILTER_ATTRIBUTE_ERROR, payload: error.message };
  }
};

export const updateFilters = (attribute, queryField) => {
  return { type: UPDATE_VEHICLE_FILTER, payload: { attribute, queryField } };
};
