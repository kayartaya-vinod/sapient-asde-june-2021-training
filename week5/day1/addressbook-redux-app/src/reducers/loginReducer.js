import { LOGIN_SUCCESS, LOGOUT } from '../actions/actionTypes';

function loginReducer(state = { token: null, user: null }, action = {}) {
    switch (action.type) {
        case LOGIN_SUCCESS: {
            return { ...state, token: action.token, user: action.user };
        }
        case LOGOUT: {
            return { ...state, token: null, user: null };
        }
        default:
            return state;
    }
}
export default loginReducer;
