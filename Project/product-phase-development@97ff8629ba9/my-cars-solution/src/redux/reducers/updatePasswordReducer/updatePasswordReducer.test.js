import { UPDATE_PASSWORD, UPDATE_PASSWORD_ERROR } from "../../actionTypes";
import updatePasswordReducer from "./updatePasswordReducer";

describe("forgotPasswordReducer tests", () => {
  it("should return default state", () => {
    const state = updatePasswordReducer({}, {});
    expect(state).toEqual({});
  });

  it("should return FORGOT_PASSWORD state", () => {
    const state = updatePasswordReducer({}, { type: UPDATE_PASSWORD });
    expect(state).toHaveProperty("isPasswordUpdated");
    expect(state).toEqual({ isPasswordUpdated: true });
  });

  it("should return FORGOT_PASSWORD_ERROR state", () => {
    const state = updatePasswordReducer({}, { type: UPDATE_PASSWORD_ERROR });
    expect(state).toHaveProperty("isPasswordUpdated");
    expect(state).toEqual({ isPasswordUpdated: false });
  });

  it("should return initial state", () => {
    const initialState = { isPasswordUpdated: false };
    const state = updatePasswordReducer(initialState, {type: UPDATE_PASSWORD_ERROR});
    expect(state).toEqual(initialState);
  });
});
