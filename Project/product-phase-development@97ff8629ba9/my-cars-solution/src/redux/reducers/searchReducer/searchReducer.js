/**
# @Author Yogamber Singh Negi yogamber.negi@publicissapient.com

@Author Hrishant Raj hrishant.raj@publicissapient.com

@Author Aditya Gheewala aditya.gheewala@publicissapient.com [case SEARCH_ACCESSORIES]
# */
import {
  SEARCH,
  SEARCH_ERROR,
  SEARCH_ACCESSORIES,
  SEARCH_ACCESSORIES_1,
  SEARCH_1,
} from "../../actionTypes";

const initialState = { vehicles: [], accessories: [], error: null };
const searchReducer = (state = initialState, action = {}) => {
  switch (action.type) {
    case SEARCH_1: {
      return { vehicles: action.payload };
    }
    case SEARCH: {
      return { vehicles: [...action.payload] };
    }

    case SEARCH_ACCESSORIES_1:
      return { accessories: action.payload };
    case SEARCH_ACCESSORIES: {
      return {
        accessories: [...action.payload],
      };
    }
    case SEARCH_ERROR: {
      return { error: action.payload };
    }
    default:
      return state;
  }
};

export default searchReducer;
