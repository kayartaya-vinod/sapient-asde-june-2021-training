/**
@Author <Sumitesh Naithani> <sumitesh.naithani@publicissapient.com>
*/
import axios from 'axios';
import { updatePasswordUrl } from '../../../constants';
import { UPDATE_PASSWORD, UPDATE_PASSWORD_ERROR } from '../../actionTypes';

export const updatePassword = async (token, password) => {
    try {
        const data = await axios.post(updatePasswordUrl, { token, password });
        return { type: UPDATE_PASSWORD, payload: data };
    } catch (error) {
        return { type: UPDATE_PASSWORD_ERROR, payload: error.message };
    }
};
