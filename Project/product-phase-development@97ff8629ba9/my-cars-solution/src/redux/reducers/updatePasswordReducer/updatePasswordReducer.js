/**
@Author <Sumitesh Naithani> <sumitesh.naithani@publicissapient.com>
*/
import {
    UPDATE_PASSWORD,
    UPDATE_PASSWORD_ERROR,
} from "../../actionTypes";
const initialState = { isPasswordUpdated: false };
const updatePasswordReducer = (state = initialState , action = {}) => {
    switch (action.type) {
        case UPDATE_PASSWORD: {
            return { ...action.payload , isPasswordUpdated: true};
        }
        case UPDATE_PASSWORD_ERROR: {
            return { ...action.payload , isPasswordUpdated: false};
        }
        default:
            return state;
    }
};

export default updatePasswordReducer;