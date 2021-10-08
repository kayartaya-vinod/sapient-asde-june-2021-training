import { FETCH_CONTACT_BY_ID } from '../actions/actionTypes';

function singleContactReducer(state = {}, action = {}) {
    switch (action.type) {
        case FETCH_CONTACT_BY_ID: {
            return { ...action.payload };
        }
        default: {
            return state;
        }
    }
}

export default singleContactReducer;
