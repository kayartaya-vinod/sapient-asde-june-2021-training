import axiosMock from "axios";
import { UPDATE_PASSWORD, UPDATE_PASSWORD_ERROR } from "../../actionTypes";
import { updatePassword } from "./updatePasswordActionCreator";

jest.mock("axios");

describe("updatePasswordActionCreator", () => {
  it("should return success", async () => {
    axiosMock.post.mockResolvedValueOnce("Success");

    expect(await updatePassword()).toEqual({
      type: UPDATE_PASSWORD,
      payload: "Success",
    });
  });

  it("should return error", async () => {
    axiosMock.post.mockRejectedValue(new Error("Error"));

    expect(await updatePassword()).toEqual({
      type: UPDATE_PASSWORD_ERROR,
      payload: "Error",
    });
  });
});
