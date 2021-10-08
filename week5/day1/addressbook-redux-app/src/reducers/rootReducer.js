import { combineReducers } from 'redux';
import contactReducer from './contactReducer';
import singleContactReducer from './singleContactReducer';
import loginReducer from './loginReducer';

export default combineReducers({
    contactReducer, // represents a state in the store
    singleContactReducer,
    loginReducer,
    // list of all other reducers you eventuall will have
});
