import { FETCH_VEHICLE_COMPARE, FETCH_VEHICLE_COMPARE_ERROR } from "../../actionTypes";

const initialState = { content: {}, ids: [], images: [], error: null };
const vehicleObjectCompareReducer = (state = initialState, action = {}) => {
  switch (action.type) {
    case FETCH_VEHICLE_COMPARE: {
      return {
        ...state,
        content: { ...action.payload.content },
        ids: [...action.payload.ids],
        images: [...action.payload.images],
      };
    }
    case FETCH_VEHICLE_COMPARE_ERROR: {
      return { ...state, error: action.payload };
    }
    default:
      return state;
  }
};

export default vehicleObjectCompareReducer;
