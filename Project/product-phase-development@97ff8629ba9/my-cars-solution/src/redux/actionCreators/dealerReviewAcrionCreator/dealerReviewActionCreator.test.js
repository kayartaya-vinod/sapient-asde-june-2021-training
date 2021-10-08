/**
@author Neha neha1@publicisssapient.com
*/

import axiosMock from "axios";
import { GET_DEALER_REVIEWS, GET_DEALER_REVIEWS_ERROR } from "../../actionTypes";
import { getDealerReviews } from "./dealerReviewActionCreator";



jest.mock("axios");

describe("dealerVehicleActionCreator test", () => {
    it(`should return ${GET_DEALER_REVIEWS} on calling getDealerReviews on resolve`, async () => {
        axiosMock.get.mockResolvedValueOnce({
            data: {
                success: true,
                data: "data"
            },
        });

        expect(
            await getDealerReviews(1)
        ).toHaveProperty("type");

    });

    it(`should return ${GET_DEALER_REVIEWS_ERROR} on calling getDealerVehicles on error`, async () => {
        axiosMock.get.mockRejectedValue(new Error("error"));

        expect(
            await getDealerReviews(1)
        ).toHaveProperty("type");

    });
    

});