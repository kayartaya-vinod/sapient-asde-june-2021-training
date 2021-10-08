/**
@Author <Sanchit Mahto> <sanchit.mahto@publicissapient.com>
*/

import axiosMock from "axios";
import { verifyEmailForForgotPassword } from "./verifyEmailForForgotPasswordCreator";
import { EMAIL_VERIFIED, EMAIL_VERIFICATION_FAILED } from "../../actionTypes";

jest.mock("axios");

describe("verifyEmailForForgotPasswordCreator test", () => {
  it(`should return action type: ${EMAIL_VERIFIED} on resolve `, async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: "sanchit@gmail.com",
    });

    expect(await verifyEmailForForgotPassword()).toEqual({
      type: EMAIL_VERIFIED,
      payload: "sanchit@gmail.com",
    });
  });

  it(`should return action type: ${EMAIL_VERIFICATION_FAILED} on error `, async () => {
    axiosMock.get.mockRejectedValue(new Error("Error"));

    expect(await verifyEmailForForgotPassword()).toEqual({
      type: EMAIL_VERIFICATION_FAILED,
      payload: "Error",
    });
  });
});
