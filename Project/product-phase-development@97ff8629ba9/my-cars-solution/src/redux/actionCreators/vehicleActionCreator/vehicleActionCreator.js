/**
@Author Sakshi Yadav - sakshi.yadav@publicissapient.com 
*/

import axios from 'axios';
import { vehiclesUrl } from '../../../constants/index';
import { GET_VEHICLE_BY_ID, GET_VEHICLE_BY_ID_ERROR } from '../../actionTypes';

export const fetchVehicleById = async (id) => {
    try {
        const { data } = await axios.get(`${vehiclesUrl}${id}`);
        return { type: GET_VEHICLE_BY_ID, payload: data.data };
    } catch (error) {
        return {
            type: GET_VEHICLE_BY_ID_ERROR,
            payload: { error: error.message },
        };
    }
};
