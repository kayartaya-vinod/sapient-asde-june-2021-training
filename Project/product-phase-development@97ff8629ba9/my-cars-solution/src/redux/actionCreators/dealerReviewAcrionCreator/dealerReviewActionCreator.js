
/**
@author Neha neha1@publicisssapient.com
*/

import axios from 'axios';

import { GET_DEALER_REVIEWS, GET_DEALER_REVIEWS_ERROR} from "../../actionTypes";
import { JWT_TOKEN_KEY,getDealerReviewsUrl } from '../../../constants';

axios.defaults.headers.common[
    'Authorization'
] = `JWT ${localStorage[JWT_TOKEN_KEY]}`;

export const getDealerReviews = async (page = 1) => {
    try {
        const { data } = await axios.get(`${getDealerReviewsUrl}?page=${page}`);
        return { type: GET_DEALER_REVIEWS, payload: data.data };
    } catch (error) {
        if (!error.response) {
            return { type: GET_DEALER_REVIEWS_ERROR, payload: { error: error.message } };
        } else {
            return { type: GET_DEALER_REVIEWS_ERROR, payload: { error: error.response.data.message } };
        }
    }
};

