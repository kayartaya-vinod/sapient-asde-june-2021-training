import axios from 'axios';
import {
    ADD_NEW_CONTACT,
    DELETE_CONTACT,
    FETCH_CONTACTS,
    FETCH_CONTACT_BY_ID,
    UPDATE_CONTACT,
} from './actionTypes';

const baseUrl = 'http://localhost:8080/contacts/';

export function fetchContacts() {
    // return a function which is called by thunk,
    // and thunk will pass "dispatch" method as the argument,
    // so that we can disapatch an action
    return async function dispatchAction(dispatch) {
        // send a GET request to the REST server to get the data
        const { data } = await axios.get(baseUrl);
        dispatch({ type: FETCH_CONTACTS, payload: data });
    };
}

export const addNewContact = (contact) => async (dispatch) => {
    // send a POST request to the REST server to add new contact
    const { data } = await axios.post(baseUrl, contact);
    dispatch({ type: ADD_NEW_CONTACT, payload: data });
};

export const updateContact = (contact) => async (dispatch) => {
    // send a POST request to the REST server to add new contact
    const { data } = await axios.put(baseUrl + contact.id, contact);
    dispatch({ type: UPDATE_CONTACT, payload: data });
};

export const deleteContact = (id) => async (dispatch) => {
    await axios.delete(baseUrl + id);
    dispatch({ type: DELETE_CONTACT, payload: id });
};

export const fetchContactById = (id) => {
    return async (dispatch) => {
        const { data } = await axios.get(baseUrl + id);
        dispatch({ type: FETCH_CONTACT_BY_ID, payload: data });
    };
};
