/**
@Author Jay Aditya Nautiyal
*/

import axios from 'axios';
import { baseUrl, JWT_TOKEN_KEY } from '../../../constants';

import {
    SAVE_COMPARISON_MATRIX,
    SAVE_COMPARISON_MATRIX_ERROR,
} from '../../actionTypes';

axios.defaults.headers.common[
    'Authorization'
] = `JWT ${localStorage[JWT_TOKEN_KEY]}`;

export const saveComparisonMatrix = async (matrix) => {
    try {
        const { data } = await axios.post(baseUrl + "/vehicles/comparison", matrix);
        return { type: SAVE_COMPARISON_MATRIX, payload: data };
    } catch (error) {
        return {
            type: SAVE_COMPARISON_MATRIX_ERROR,
            payload: { error: error.message },
        };
    }
};
