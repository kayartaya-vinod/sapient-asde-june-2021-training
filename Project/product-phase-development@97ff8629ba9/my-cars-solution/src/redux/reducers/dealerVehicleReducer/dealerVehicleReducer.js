/**
@author Abhishek Singh abhishek.singh8@publicisssapient.com
*/

import { GET_DEALER_VEHICLES, SELECT_DEALER_VEHICLES, REMOVE_SELECTED_DEALER_VEHICLES, FILTER_DEALER_VEHICLES, RESET_DEALER_VEHICLE_STATE } from "../../actionTypes";

const initialState = {
  isFirst: true,
  isLast: true,
  vehicles: [],
  selectedVehicles: [],
  filteredVehicles: [],
};

const dealerVehicleReducer = (state = initialState, action = {}) => {
  switch (action.type) {
    case GET_DEALER_VEHICLES: {
      const { content, first, last } = action.payload;

      return { ...state, vehicles: content, filteredVehicles: content, isFirst: first, isLast: last };
    }

    case SELECT_DEALER_VEHICLES: {
      const selectedVehicles = [...state.selectedVehicles.concat(action.payload)];
      return { ...state, selectedVehicles };
    }

    case REMOVE_SELECTED_DEALER_VEHICLES: {
      const removeVehicles = action.payload;
      const selectedVehicles = [...state.selectedVehicles];
      removeVehicles.forEach((id) => {
        const index = selectedVehicles.findIndex((s) => s === id);
        selectedVehicles.splice(index, 1);
      });

      return { ...state, selectedVehicles };
    }

    case FILTER_DEALER_VEHICLES: {
      const filteredVehicles = action.payload;
      return { ...state, filteredVehicles: [...filteredVehicles] };
    }

    case RESET_DEALER_VEHICLE_STATE: {
      return initialState;
    }

    default:
      return state;
  }
};

export default dealerVehicleReducer;