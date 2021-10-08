/**
@author Abhishek Singh abhishek.singh8@publicisssapient.com
*/

import { GET_DEALER_VEHICLE_ACCESSORIES, RESET_DEALER_VEHICLE_ACCESSORIES_STATE, DELETE_ACCESSORY, SELECT_DEALER_ACCESSORY } from "../../actionTypes";

const initialState = {
  isFirst: true,
  isLast: true,
  accessories: [],
  idToDelete: ""
};

const dealerAccessoriesReducer = (state = initialState, action = {}) => {
  switch (action.type) {
    case GET_DEALER_VEHICLE_ACCESSORIES: {
      const { content, first, last } = action.payload;

      return { ...state, accessories: content, isFirst: first, isLast: last };
    }

    case RESET_DEALER_VEHICLE_ACCESSORIES_STATE: {
      return initialState;
    }

    case SELECT_DEALER_ACCESSORY: {
      const id = action.payload;
      return { ...state, idToDelete: id };
    }

    case DELETE_ACCESSORY:
      const delState = { ...state };
      const accessories = delState["accessories"];
      const index = accessories.findIndex((a) => a.id === action.payload);
      accessories.splice(index, 1);
      delState["accessories"] = accessories;
      return { ...delState };

    default:
      return state;
  }
};

export default dealerAccessoriesReducer;