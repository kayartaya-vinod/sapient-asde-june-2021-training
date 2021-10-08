import { LOGOUT_SUCCESS, LOGIN_SUCCESS, LOGIN_FAILED } from "../../actionTypes";
import authReducer from "./authReducer";

describe("authReducer tests", () => {
  it("should return default state", () => {
    const state = authReducer({}, {});
    expect(state).toEqual({});
  });

  it("should return LOGIN_SUCCESS state", () => {
    const state = authReducer({}, { type: LOGIN_SUCCESS });
    expect(state).toHaveProperty("isLoggedIn");
    expect(state).toEqual({ isLoggedIn: true });
  });

  it("should return LOGIN_FAILED state", () => {
    const state = authReducer({}, { type: LOGIN_FAILED });
    expect(state).toHaveProperty("isLoggedIn");
    expect(state).toEqual({ isLoggedIn: false,token:null,user:null });
  });

  it("should return LOGOUT_SUCCESS state", () => {
    const state = authReducer({}, { type: LOGOUT_SUCCESS });
    expect(state).toHaveProperty("isLoggedIn");
    expect(state).toEqual({ isLoggedIn: false,token:null,user:null });
  });
  it("should work with initial state", () => {
    const state = authReducer({ isLoggedIn: false }, undefined);
    expect(state).toHaveProperty("isLoggedIn");
    expect(state).toEqual({ isLoggedIn: false });
  });
});
