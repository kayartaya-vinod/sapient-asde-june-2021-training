/**
@Author Sumitesh Naithani sumitesh.naithani@publicissapient.com
*/
import {
  ADD_ACCESSORY,
  ADD_ACCESSORY_ERROR,
  RESET_ADD_ACCESSORY,
} from "../../actionTypes";

const addAccessoryReducer = (
  state = { success: null, message: null },
  action = {}
) => {
  switch (action.type) {
    case ADD_ACCESSORY:
      return { ...state, ...action.payload, success: true };
    case ADD_ACCESSORY_ERROR:
      return { ...state, success: false, message: action.payload };
    case RESET_ADD_ACCESSORY:
      return { success: null };
    default: {
      return state;
    }
  }
};

export default addAccessoryReducer;
