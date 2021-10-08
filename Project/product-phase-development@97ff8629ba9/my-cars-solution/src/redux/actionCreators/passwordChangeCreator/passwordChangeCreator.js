import axios from 'axios';
import { RESET_CHANGE_PASSWORD_STORE } from '../../actionTypes';
import { changePasswordUrl } from '../../../constants';

export const changePassword = async (payload) => {
    try {
        const { data } = await axios.post(changePasswordUrl, payload);
        return {
            type: 'NO_REDUX_REQUIRED',
            payload: { token: data.token },
            success: true,
        };
    } catch (error) {
        return { type: '', success: false, error: error.message };
    }
};

export const resetChangePasswordReducer = () => {
    return { type: RESET_CHANGE_PASSWORD_STORE };
};
