import { PASSWORD_SUCCESS, PASSWORD_FAILED, RESET_CHANGE_PASSWORD_STORE } from "../../actionTypes";

export default function passwordChangeReducer (
  state = { isPasswordChanged: false, error: "" },
  action = {}
) {
  switch (action.type) {
    case PASSWORD_SUCCESS: {
      return { ...state, isPasswordChanged: true, error: "" };
    }
    case PASSWORD_FAILED: {
      return { ...state, isPasswordChanged: false, error: action.payload.error };
    }

    case RESET_CHANGE_PASSWORD_STORE: {
      return { ...state, isPasswordChanged: false, error: "" };
    }

    default:
      return state;
  }
}

