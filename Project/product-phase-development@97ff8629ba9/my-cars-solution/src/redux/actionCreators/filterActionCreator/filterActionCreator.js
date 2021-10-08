import axios from "axios";
import { advSearchUrl } from "../../../constants";
import {
  FETCH_FILTERED_LIST,
  FETCH_FILTERED_LIST_ERROR,
  RESET_FETCH_FILTERED_LIST,
} from "../../actionTypes";

export const fetchFilteredData = async (filter, page = 1) => {
  try {
    const { data } = await axios.get(`${advSearchUrl}${filter}&page=${page}`);
    return { type: FETCH_FILTERED_LIST, payload: data.data };
  } catch (error) {
    return {
      type: FETCH_FILTERED_LIST_ERROR,
      payload: { error: error.message },
    };
  }
};

export const resetFetchFilteredData = () => {
  return { type: RESET_FETCH_FILTERED_LIST };
};
