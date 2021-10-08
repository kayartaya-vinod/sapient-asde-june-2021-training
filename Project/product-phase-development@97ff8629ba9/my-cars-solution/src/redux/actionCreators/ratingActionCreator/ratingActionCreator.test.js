/**
@Author Jaswant Arya - jaswant.arya@publicissapient.com
*/

import { updateRating, getRating } from "./ratingActionCreator";
import axiosMock from "axios";
import {
  UPDATE_RATING,
  UPDATE_RATING_ERROR,
  GET_MY_RATING,
  GET_MY_RATING_ERROR,
} from "../../actionTypes";

jest.mock("axios");

describe("ratingActionCreator", () => {
  it("should return success for updateRating", async () => {
    axiosMock.post.mockResolvedValueOnce({
      data: {
        success: true,
      },
    });

    expect(await updateRating(1, 2)).toEqual({
      type: UPDATE_RATING,
      payload: { success: true },
    });
  });

  it("should return error for updateRating", async () => {
    axiosMock.post.mockRejectedValue(new Error("Error"));

    expect(await updateRating(1, 2)).toEqual({
      type: UPDATE_RATING_ERROR,
      payload: "Error",
    });
  });

  it("should return success for getRating", async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {
        success: true,
        rating: 5,
      },
    });

    expect(await getRating(1)).toEqual({
      type: GET_MY_RATING,
      payload: { success: true, rating: 5 },
    });
  });

  it("should return error for getRating", async () => {
    axiosMock.get.mockRejectedValue(new Error("Error"));

    expect(await getRating(1)).toEqual({
      type: GET_MY_RATING_ERROR,
      payload: "Error",
    });
  });
});
