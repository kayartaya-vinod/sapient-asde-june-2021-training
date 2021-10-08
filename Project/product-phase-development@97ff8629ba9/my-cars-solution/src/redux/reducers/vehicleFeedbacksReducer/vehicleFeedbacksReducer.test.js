/**
@Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
*/
import { FEEDBACK_FAILURE, FEEDBACK_SUCCESS } from "../../actionTypes";
import vehicleFeedbacksReducer from "./vehicleFeedbacksReducer";

describe("vehicleFeedBacksReducer tests", () => {
    it("should return default state", () => {
        const state = vehicleFeedbacksReducer({}, {});
        expect(state).toEqual({});
    });

    it("should return FEEDBACK_SUCCESS state", () => {
        const state = vehicleFeedbacksReducer([],{ type: FEEDBACK_SUCCESS, payload: { data: [{}, {}] }});
        expect(state).toHaveProperty("data");
        expect(state).toEqual({ data: [{}, {}] });
    });

    it("should return FEEDBACK_FAILURE state", () => {
        const state = vehicleFeedbacksReducer([], { type: FEEDBACK_FAILURE, payload:"Error" });
        expect(state).toEqual({ error : "Error" });
    });

});
