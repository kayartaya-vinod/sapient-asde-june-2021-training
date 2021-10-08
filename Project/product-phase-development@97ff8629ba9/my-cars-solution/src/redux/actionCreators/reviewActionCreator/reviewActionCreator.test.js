/**
@Author Rohit Bhatt rohit.bhatt1@publicissapient.com
*/
import axiosMock from "axios";
import {
  POST_MY_REVIEW,
  POST_MY_REVIEW_ERROR,
  GET_MY_REVIEW,
  GET_MY_REVIEW_ERROR,
} from "../../actionTypes";
import { postReview, getReview } from "./reviewActionCreator";

jest.mock("axios");

describe("reviewActionCreator", () => {
  it("should return success for postReview", async () => {
    axiosMock.post.mockResolvedValueOnce({
      data: {
        success: true,
      },
    });

    expect(await postReview()).toEqual({
      type: POST_MY_REVIEW,
      payload: { success: true },
    });
  });

  it("should return error for postReview", async () => {
    axiosMock.post.mockRejectedValue(new Error("Error"));

    expect(await postReview()).toEqual({
      type: POST_MY_REVIEW_ERROR,
      payload: "Error",
    });
  });

  it("should return success for getReview", async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {
        success: true,
        review: "This is the initial review!",
      },
    });

    expect(await getReview()).toEqual({
      type: GET_MY_REVIEW,
      payload: { success: true, review: "This is the initial review!" },
    });
  });

  it("should return error for getReview", async () => {
    axiosMock.get.mockRejectedValue(new Error("Error"));

    expect(await getReview()).toEqual({
      type: GET_MY_REVIEW_ERROR,
      payload: "Error",
    });
  });
});
