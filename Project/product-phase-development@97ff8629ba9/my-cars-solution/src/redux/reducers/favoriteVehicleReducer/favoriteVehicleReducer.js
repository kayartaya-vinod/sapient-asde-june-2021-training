/**
@Author Aravind M Krishnan - aravind.m.krishnan@publicissapient.com 
@Author Deepthi -deepthi.s@publicissapient.com
*/

import {
  GET_FAVORITE_VEHICLES,
  GET_FAVORITE_VEHICLES_ERROR,
  UPDATE_FAVORITES,
  UPDATE_FAVORITES_ERROR,
} from "../../actionTypes";

let favVehicles = localStorage.getItem("FAV_VEHICLES");
if (favVehicles) {
  favVehicles = JSON.parse(favVehicles);
}
const initialState = { vehicles: favVehicles || [], error: null };

const favoriteVehicleReducer = (state = initialState, action = {}) => {
  switch (action.type) {
    case GET_FAVORITE_VEHICLES: {
      localStorage.setItem("FAV_VEHICLES", JSON.stringify(action.payload.data));
      return {
        ...state,
        vehicles: [...action.payload.data],
      };
    }
    case GET_FAVORITE_VEHICLES_ERROR: {
      return { ...state, error: action.payload };
    }

    case UPDATE_FAVORITES: {
      const veh = action.payload;
      let currState = state.vehicles;
      const index = currState.findIndex((v) => v.id === veh.id);
      if (index === -1) {
        currState = [...currState, veh];
      } else {
        currState.splice(index, 1);
      }

      return {
        ...state,
        vehicles: [...currState],
      };
    }
    case UPDATE_FAVORITES_ERROR: {
      return { ...state, error: action.payload };
    }
    default:
      return state;
  }
};
export default favoriteVehicleReducer;
