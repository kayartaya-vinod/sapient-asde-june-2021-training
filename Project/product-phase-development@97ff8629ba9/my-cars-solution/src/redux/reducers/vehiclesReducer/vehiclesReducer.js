/**
@Author Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/
import {
  ADD_VEHICLE,
  ADD_VEHICLE_ERROR,
  GET_VEHICLES,
  GET_VEHICLES_1,
  GET_VEHICLES_ERROR,
  RESET_ADD_VEHICLE,
} from "../../actionTypes";
const initialState = { vehicles: [], error: null };
const vehiclesReducer = (state = initialState, action = {}) => {
  switch (action.type) {
    case GET_VEHICLES:
      return {
        ...state,
        vehicles: [...state["vehicles"], ...action.payload.content],
        totalPages: action.payload.totalPages,
      };
    case GET_VEHICLES_1:
      return {
        vehicles: [...action.payload.content],
        totalPages: action.payload.totalPages,
      };
    case GET_VEHICLES_ERROR: {
      return { ...state, error: action.payload };
    }
    default:
      return state;
  }
};

/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
export const addVehicle = (
  state = { success: null, message: null },
  action = {}
) => {
  switch (action.type) {
    case ADD_VEHICLE:
      return { ...state, ...action.payload, success: true };
    case ADD_VEHICLE_ERROR:
      return { ...state, success: false, message: action.payload };
    case RESET_ADD_VEHICLE:
      return { success: null };
    default: {
      return state;
    }
  }
};

export default vehiclesReducer;
