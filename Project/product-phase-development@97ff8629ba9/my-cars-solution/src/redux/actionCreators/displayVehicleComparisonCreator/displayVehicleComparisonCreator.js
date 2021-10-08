import axios from "axios";
import { displayCompareUrl } from "../../../constants/index";
import { GET_COMPARISON_BY_ID, GET_COMPARISON_BY_ID_ERROR } from "../../actionTypes";

export const fetchComparisonById = async (id) => {
  try {
    
    const { data } = await axios.get(`${displayCompareUrl}${id}`);
    
    return { type: GET_COMPARISON_BY_ID, payload: data.data };
  } catch (error) {
    return { type: GET_COMPARISON_BY_ID_ERROR, error: error.message };
  }
};
