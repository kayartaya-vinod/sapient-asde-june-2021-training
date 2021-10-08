/**
@Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
*/
import axiosMock from "axios";
import { FEEDBACK_FAILURE, FEEDBACK_SUCCESS } from "../../actionTypes";
import { getVehicleFeedbacks } from "./vehicleFeedbacksActionCreator";


jest.mock("axios");

describe("vehicleFeedbacksActionCreator", () => {
    const id = "124"
    it("should return success Feedback Type", async () => {
        axiosMock.get.mockResolvedValueOnce({
            data: {},
        });

        expect(await getVehicleFeedbacks(id)).toEqual({
            type: FEEDBACK_SUCCESS,
            payload: {},
        });
    });

    it("should return error Feedback Type", async () => {
        axiosMock.get.mockRejectedValue(new Error("Error"));

        expect(await getVehicleFeedbacks(id)).toEqual({
            type: FEEDBACK_FAILURE,
            payload: "Error",
        });
    });
    
});
