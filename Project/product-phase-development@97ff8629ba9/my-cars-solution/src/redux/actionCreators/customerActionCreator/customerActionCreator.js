/**
@Author1 Mutharasan E - mutharasan.e@publicissapient.com 
@Author2 Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/

import axios from 'axios';
import { customerUrl, JWT_TOKEN_KEY } from '../../../constants';

import {
    GET_CUSTOMER_BY_ID,
    GET_CUSTOMER_BY_ID_ERROR,
    UPDATE_CUSTOMER,
    UPDATE_CUSTOMER_ERROR,
} from '../../actionTypes';

axios.defaults.headers.common[
    'Authorization'
] = `JWT ${localStorage[JWT_TOKEN_KEY]}`;

export const fetchCustomerById = async () => {
    try {
        const { data } = await axios.get(customerUrl);
        return { type: GET_CUSTOMER_BY_ID, payload: data['data'] };
    } catch (error) {
        return {
            type: GET_CUSTOMER_BY_ID_ERROR,
            payload: { error: error.message },
        };
    }
};

export const updateCustomer = async (customer) => {
    try {
        const { data } = await axios.put(customerUrl, customer);
        return { type: UPDATE_CUSTOMER, payload: data };
    } catch (error) {
        return {
            type: UPDATE_CUSTOMER_ERROR,
            payload: { error: error.message },
        };
    }
};
