import { signup } from "./signupActionCreator";
import axiosMock from "axios";
import { SIGNUP, SIGNUP_FAILED } from "../../actionTypes";

jest.mock("axios");
describe("signupActionCreator test", () => {
  it(`should return  action Type : ${SIGNUP} on resolve`, async () => {
    axiosMock.post.mockResolvedValueOnce({
      data: { firstname: "john", token: "Welcome#123" },
    });

    expect(await signup({})).toEqual({
      type: SIGNUP,
      payload: { firstname: "john", token: "Welcome#123" },
    });
  });
  it(`should return  error action Type : ${SIGNUP_FAILED} on resolve`, async () => {
    axiosMock.post.mockRejectedValue(new Error("Error"));
    const user = {
      firstname: "John",
      lastName: "Doe",
      email: "john@doe.co",
      password: "topsecret",
      userType: "customer",
    };
    expect(await signup(user)).toEqual({
      type: SIGNUP_FAILED,
      payload: "Error",
    });
  });
});
