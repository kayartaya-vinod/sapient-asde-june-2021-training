import { SET_FILTER_ATTRIBUTES } from '../../actionTypes';

const filterAttributeReducer = (state = '', action = {}) => {
    if (action.type === SET_FILTER_ATTRIBUTES) {
        return action.payload;
    } else {
        return state;
    }
};

export default filterAttributeReducer;

// need to be deleted
