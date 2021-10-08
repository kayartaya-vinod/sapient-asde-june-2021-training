/**
@Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
*/
import axios from 'axios';
import { vehicleFeedbacksUrl } from '../../../constants';
import { FEEDBACK_FAILURE, FEEDBACK_SUCCESS } from '../../actionTypes';

export const getVehicleFeedbacks = async (id) => {
    try{
        const {data} = await axios.get(`${vehicleFeedbacksUrl}?q=${id}`);
        return { type: FEEDBACK_SUCCESS, payload: data };
    }catch(error){
        if (!error.response) {
            return { type: FEEDBACK_FAILURE, payload: error.message };
        } else {
            return { type: FEEDBACK_FAILURE, payload: error.response.data.message };
        }
    }
}