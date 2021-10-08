import { SIGNUP, SIGNUP_FAILED } from "../../actionTypes";
import signupReducer from "./signupReducer";

describe("signupReducer tests", () => {
  it("should return the initial state", () => {
    expect(signupReducer({}, undefined)).toEqual({});
  });
  it("should return initial state", () => {
    expect(signupReducer(undefined, {})).toEqual({});
  });
  it("should return default state", () => {
    expect(signupReducer({}, {})).toEqual({});
  });
  it("should return SIGNUP state", () => {
    expect(
      signupReducer(
        {},
        {
          type: SIGNUP,
          payload: { firstname: "John" },
        }
      )
    ).toEqual({ firstname: "John" });
  });
  it("should return SIGNUP_FAILED state", () => {
    expect(
      signupReducer(
        {},
        {
          type: SIGNUP_FAILED,
          payload: "ERROR",
        }
      )
    ).toEqual({ 0: "E", 1: "R", 2: "R", 3: "O", 4: "R" });
  });
});
