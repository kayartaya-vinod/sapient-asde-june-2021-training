import { ADD_TO_COMPARE, LOGOUT_SUCCESS, REMOVE_FROM_COMPARE } from "../../actionTypes";
import { toast } from "react-toastify";
const vehicleCompareListReducer = (state = [], action = {}) => {
  switch (action.type) {
    case ADD_TO_COMPARE: {
      // check if action.payload is already in the array, if yes then don't add
      const idState = [...state];
      const idIndex = idState.indexOf(action.payload);
      if (idIndex === -1 && idState.length < 4) {
        idState.push(action.payload);
      }
      else if (idIndex !== -1) {
        toast.info("Vehicle already present", {
          position: toast.POSITION.BOTTOM_LEFT,
          autoClose: 2000,
        });
      }

      return [...idState];
    }

    case REMOVE_FROM_COMPARE: {
      const idState = [...state];
      const idIndex = idState.indexOf(action.payload);
      if (idIndex !== -1) {
        idState.splice(idIndex, 1);
      }
      return [...idState];
    }

    case LOGOUT_SUCCESS: {
      return [];
    }

    default:
      return state;
  }
};

export default vehicleCompareListReducer;
