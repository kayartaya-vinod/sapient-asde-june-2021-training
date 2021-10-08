import axios from 'axios';

import {
    ADD_NEW_CONTACT,
    DELETE_CONTACT,
    FETCH_CONTACTS,
    FETCH_CONTACT_BY_ID,
    LOGIN_SUCCESS,
    UPDATE_CONTACT,
} from './actionTypes';

const baseUrl = 'http://localhost:8080/api/contacts/';
const authUrl = 'http://localhost:8080/api/auth/';

export async function fetchContacts() {
    const { data } = await axios.get(baseUrl);
    return { type: FETCH_CONTACTS, payload: data };
}

export const addNewContact = async (contact) => {
    const { data } = await axios.post(baseUrl, contact);
    return { type: ADD_NEW_CONTACT, payload: data };
};

export const updateContact = async (contact) => {
    const { data } = await axios.put(baseUrl + contact.id, contact);
    return { type: UPDATE_CONTACT, payload: data };
};

export const deleteContact = async (id) => {
    await axios.delete(baseUrl + id);
    return { type: DELETE_CONTACT, payload: id };
};

export const fetchContactById = async (id) => {
    const { data } = await axios.get(baseUrl + id);
    return { type: FETCH_CONTACT_BY_ID, payload: data };
};

export const login = async (user) => {
    const { data } = await axios.post(authUrl + 'login', user);
    // adding the "Authorization" header to all subsequent requests using axios
    axios.defaults.headers.common['Authorization'] = `JWT ${data}`;
    return { type: LOGIN_SUCCESS, token: data, user };
};

export const logout = async () => {
    delete axios.defaults.headers.common['Authorization'];
    return { type: 'LOGOUT' };
};
