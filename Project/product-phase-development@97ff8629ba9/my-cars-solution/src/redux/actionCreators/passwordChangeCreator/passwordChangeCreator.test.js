import {
  changePassword,
  resetChangePasswordReducer,
} from "./passwordChangeCreator";
import axiosMock from "axios";
import {
  PASSWORD_FAILED,
  RESET_CHANGE_PASSWORD_STORE,
  LOGIN_SUCCESS,
} from "../../actionTypes";

jest.mock("axios");
describe("passwordChangeCreator test", () => {
  it(`should return action type: ${LOGIN_SUCCESS} on resolve `, async () => {
    axiosMock.post.mockResolvedValueOnce({
      data: {
        message: "SUCCESS",
      },
    });

    expect(
      await changePassword({
        old_password: "old",
        password: "new",
        new_password: "new",
      })
    ).toHaveProperty("type");
  });

  it(`should return action type: ${PASSWORD_FAILED} on error `, async () => {
    axiosMock.post.mockRejectedValue(new Error("Error"));

    expect(await changePassword({ old_password: "old" })).toHaveProperty(
      "type"
    );
  });

  it(`should return the action type: ${RESET_CHANGE_PASSWORD_STORE}`, () => {
    expect(resetChangePasswordReducer()).toEqual({
      type: RESET_CHANGE_PASSWORD_STORE,
    });
  });
});
