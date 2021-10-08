import axios from 'axios';
import {
    GET_SPONSORED_VEHICLE,
    GET_SPONSORED_VEHICLE_ERROR,
} from '../../actionTypes';
import { sponsoredVehiclesUrl } from '../../../constants';

/**
 *
 * author: vikash
 */
export const getSponsoredVehicles = async () => {
    try {
        const { data } = await axios.get(sponsoredVehiclesUrl);
        return { type: GET_SPONSORED_VEHICLE, payload: data.data };
    } catch (error) {
        return { type: GET_SPONSORED_VEHICLE_ERROR, payload: error.message };
    }
};
