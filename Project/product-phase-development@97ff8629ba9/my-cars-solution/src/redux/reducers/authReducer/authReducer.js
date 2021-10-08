import { EMAIL_KEY, ID_KEY, JWT_TOKEN_KEY, NAME_KEY, ROLE_KEY } from '../../../constants';
import { LOGOUT_SUCCESS, LOGIN_SUCCESS, LOGIN_FAILED } from '../../actionTypes';

const initialState = localStorage[JWT_TOKEN_KEY]
    ? {
        isLoggedIn: true,
        token: localStorage[JWT_TOKEN_KEY],
        user: {
            id: localStorage[ID_KEY],
            name: localStorage[NAME_KEY],
            email: localStorage[EMAIL_KEY],
            role: localStorage[ROLE_KEY],
        },
    }
    : { isLoggedIn: false, token: null, user: { id: "", name: "", email: "", role: "" } };

const authReducer = (state = initialState, action = {}) => {
    switch (action.type) {
        case LOGIN_FAILED:
        case LOGOUT_SUCCESS: {
            return {
                ...action.payload,
                isLoggedIn: false,
                token: null,
                user: null,
            };
        }
        case LOGIN_SUCCESS: {
            return { ...action.payload, isLoggedIn: true };
        }
        default:
            return state;
    }
};

export default authReducer;
