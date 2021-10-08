import { CHECK_EMAIL, CHECK_EMAIL_ERROR } from "../../actionTypes";
import checkEmailReducer from "./checkEmailReducer";

describe("checkEmailReducer tests", () => {
  it("should return default state", () => {
    const state = checkEmailReducer({}, {});
    expect(state).toEqual({});
  });

  it("should return CHECK_EMAIL state", () => {
    const state = checkEmailReducer({}, { type: CHECK_EMAIL });
    expect(state).toHaveProperty("isValidEmail");
    expect(state).toEqual({ isValidEmail: true });
  });

  it("should return CHECK_EMAIL_ERROR state", () => {
    const state = checkEmailReducer({}, { type: CHECK_EMAIL_ERROR });
    expect(state).toHaveProperty("isValidEmail");
    expect(state).toEqual({ isValidEmail: false });
  });

  it("should return initial state", () => {
    const initialState = { isValidEmail: false };
    const state = checkEmailReducer(initialState, undefined);
    expect(state).toEqual(initialState);
  });
});
