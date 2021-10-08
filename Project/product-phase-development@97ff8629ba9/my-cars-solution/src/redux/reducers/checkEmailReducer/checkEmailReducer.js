import {
    CHECK_EMAIL,
    CHECK_EMAIL_ERROR,
} from "../../actionTypes";
const initialState = { isValidEmail: false };
const checkEmailReducer = (state = initialState, action = {}) => {
    switch (action.type) {
        case CHECK_EMAIL: {
            return { ...action.payload, isValidEmail: true };
        }
        case CHECK_EMAIL_ERROR: {
            return { ...action.payload, isValidEmail: false };
        }
        default:
            return state;
    }
};

export default checkEmailReducer;