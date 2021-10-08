import { combineReducers } from 'redux';
import contactReducer from './contactReducer';
import singleContactReducer from './singleContactReducer';

export default combineReducers({
    contactReducer, // represents a state in the store
    singleContactReducer,
    // list of all other reducers you eventuall will have
});
