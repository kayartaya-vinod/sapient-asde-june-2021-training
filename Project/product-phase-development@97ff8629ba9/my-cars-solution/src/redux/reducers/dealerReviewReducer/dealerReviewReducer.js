/**
@author Neha neha1@publicisssapient.com
*/
import { GET_DEALER_REVIEWS ,GET_DEALER_REVIEWS_ERROR} from "../../actionTypes";

const initialState = {
    isFirst: true,
    isLast: true,
    error: null,
    data: []
};

const dealerReviewReducer = (state = initialState, action = {}) => {
    switch (action.type) {
        case GET_DEALER_REVIEWS: {
           const { feedbacks, page:{first,last} } = action.payload;
            
        

            return { ...state, data: feedbacks, isFirst: first, isLast: last };
            
        }

        case GET_DEALER_REVIEWS_ERROR: {
            return { ...state, error: action.payload };
        }
    
        default:
            return state;
    }
};

export default dealerReviewReducer;