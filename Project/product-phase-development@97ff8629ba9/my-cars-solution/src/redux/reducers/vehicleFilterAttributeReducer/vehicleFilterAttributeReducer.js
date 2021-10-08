import {
  GET_VEHICLE_FILTER_ATTRIBUTE,
  UPDATE_VEHICLE_FILTER,
} from "../../actionTypes";

const initialState = [];

const vehicleFilterAttributeReducer = (state = initialState, action = {}) => {
  switch (action.type) {
    case GET_VEHICLE_FILTER_ATTRIBUTE: {
      return [...action.payload];
    }
    case UPDATE_VEHICLE_FILTER: {
      const { attribute, queryField } = action.payload;

      const newState = [...state];
      const index = state.findIndex((s) => s.attribute === attribute);
      const filter = { ...state[index], queryField: [...queryField] };
      newState[index] = filter;
      return [...newState];
    }
    default:
      return state;
  }
};

export default vehicleFilterAttributeReducer;
