import {
  GET_COMPARISON_BY_ID,
  GET_COMPARISON_BY_ID_ERROR,
} from "../../actionTypes";

const initialState = {
  "vehicleIds":[],
  "comparisonMatrixName":""
}

const displayVehicleComparisonReducer = (state =initialState, action = {}) => {
  switch (action.type) {
    case GET_COMPARISON_BY_ID: {
      return { ...action.payload };
    }
    case GET_COMPARISON_BY_ID_ERROR: {
      return { ...action.payload };
    }
    default:
      return state;
  }
};

export default displayVehicleComparisonReducer;
