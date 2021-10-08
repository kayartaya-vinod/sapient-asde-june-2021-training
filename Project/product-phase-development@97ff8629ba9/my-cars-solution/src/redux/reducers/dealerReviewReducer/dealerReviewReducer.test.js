/**
@author Neha neha1@publicisssapient.com
*/


import { GET_DEALER_REVIEWS, GET_DEALER_REVIEWS_ERROR } from "../../actionTypes";

import dealerReviewReducer from "./dealerReviewReducer";

const initialState = {
    isFirst: true,
    isLast: true,
    error: null,
    data: [],

};

describe("dealerVehicleReducer tests", () => {
    it("should return the initial state", () => {
        const state = dealerReviewReducer(initialState, {});
        expect(state).toEqual(initialState);
        expect(dealerReviewReducer(undefined, {})).toEqual(initialState);
    });

    it(`should change the state on ${GET_DEALER_REVIEWS}`, () => {
        const feedbacks = ["vehicle"];
        const first = true;
        const last = true;
        const page = { first, last };

        const action = {
            type: GET_DEALER_REVIEWS, payload: {
                feedbacks,
                page
            }
        };
        const state = dealerReviewReducer(initialState, action);

        expect(state).toEqual({ ...initialState, data: feedbacks, isFirst: first, isLast: last });
    });

    it("should handle an error message", () => {
        const previousState = [];
        expect(
            dealerReviewReducer(previousState, {
                type: GET_DEALER_REVIEWS_ERROR,
                payload: "error",
            })
        ).toEqual({ error: "error" });
    });




});