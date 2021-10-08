/**
@Author Manvendra Singh manvendra.singh@publicissapient.com
*/

import { getAverageRating } from "./averageRatingActionCreator";
import axiosMock from "axios";
import {
  GET_AVERAGE_RATING,
  GET_AVERAGE_RATING_ERROR,
} from "../../actionTypes";

jest.mock("axios");

describe("ratingActionCreator", () => {

  it("should return success for averageRating", async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {
        success: true,
        averageRating:4.5,
        totalCustomer:200
      },
    });

    expect(await getAverageRating()).toEqual({
      type: GET_AVERAGE_RATING,
      payload: { success: true,averageRating: 4.5,totalCustomer:200},
    });
  });

  it("should return error for averageRating", async () => {
    axiosMock.get.mockRejectedValue(new Error("Error"));

    expect(await getAverageRating()).toEqual({
      type: GET_AVERAGE_RATING_ERROR,
      payload:"Error" 
    });
  });
});
