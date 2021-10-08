import {
    ADD_NEW_CONTACT,
    DELETE_CONTACT,
    FETCH_CONTACTS,
    UPDATE_CONTACT,
} from '../actions/actionTypes';

// this method is executed by redux, whenever an action is dispatched.
// redux supplies the current state and the action dispatched as 2 arguments to this function.
// this function, should return the modified state, back to the store
export default function contactReducer(state = [], action = {}) {
    switch (action.type) {
        case FETCH_CONTACTS: {
            return [...action.payload];
        }
        case ADD_NEW_CONTACT: {
            // action.payload ---> represents the new contact to be added
            const contacts = [...state]; // local copy of state
            contacts.push({ ...action.payload }); // push a shallow copy of the payload (which is a new contact)
            return [...contacts]; // return a shallow copy of modified state
        }
        case UPDATE_CONTACT: {
            const contacts = [...state]; // local copy of state
            const index = contacts.findIndex((c) => c.id === action.payload.id);
            if (index !== -1) {
                contacts[index] = { ...action.payload };
            }
            return [...contacts];
        }
        case DELETE_CONTACT: {
            // action.payload ---> represents the id of the contact to be deleted
            const contacts = [...state]; // local copy of state
            const index = contacts.findIndex((c) => c.id === action.payload);
            contacts.splice(index, 1);
            return [...contacts];
        }
        default:
            return state;
    }
}
