import axiosMock from "axios";
import { CHECK_EMAIL, CHECK_EMAIL_ERROR } from "../../actionTypes";
import { checkEmail } from "./checkEmailActionCreator";

jest.mock("axios");

describe("checkEmailActionCreator", () => {
  it("should return success", async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {},
    });

    expect(await checkEmail()).toEqual({
      type: CHECK_EMAIL,
      payload: {},
    });
  });

  it("should return error", async () => {
    axiosMock.get.mockRejectedValue(new Error("Error"));

    expect(await checkEmail()).toEqual({
      type: CHECK_EMAIL_ERROR,
      payload: "Error",
    });
  });
});
