import {
    FETCH_FILTERED_LIST,
    FETCH_FILTERED_LIST_ERROR,
    RESET_FETCH_FILTERED_LIST,
} from '../../actionTypes';

const initialState = {
    vehicles: [],
    error: null,
    start: true,
};

const fetchFilteredDataReducer = (state = initialState, action = {}) => {
    switch (action.type) {
        case FETCH_FILTERED_LIST: {
            return { ...state, vehicles: action.payload.content, start: false, totalPages: action.payload.totalPages };
        }
        case FETCH_FILTERED_LIST_ERROR: {
            return { ...state, error: action.payload };
        }

        case RESET_FETCH_FILTERED_LIST: {
            return initialState;
        }

        default:
            return state;
    }
};

export default fetchFilteredDataReducer;
