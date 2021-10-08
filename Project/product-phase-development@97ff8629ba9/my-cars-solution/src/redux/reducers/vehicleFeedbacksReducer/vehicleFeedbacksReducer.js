/**
@Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
*/
import { FEEDBACK_FAILURE, FEEDBACK_SUCCESS } from "../../actionTypes";

const initialState = {
    data: [],
    error:null
}
const vehicleFeedbacksReducer = (state = initialState, action = {}) => {
    switch (action.type) {
        case FEEDBACK_SUCCESS: {
            return { ...action.payload };
        }

        case FEEDBACK_FAILURE: {
            return { ...state, error: action.payload };
        }
        default:
            return state;
    }
};

export default vehicleFeedbacksReducer;