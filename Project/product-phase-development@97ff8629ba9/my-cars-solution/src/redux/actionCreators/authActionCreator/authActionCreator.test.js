import axiosMock from "axios";
import { LOGIN_SUCCESS, LOGOUT_SUCCESS, LOGIN_FAILED } from "../../actionTypes";
import { fetchLoginState, logOut, login } from "./authActionCreator";
import { ROLE_CUSTOMER } from '../../../constants';

jest.mock("axios");

describe("authActionCreator", () => {
  it("should return success Action Type", async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {},
    });

    expect(await fetchLoginState()).toEqual({
      type: LOGIN_SUCCESS,
      payload: {},
    });
  });

  it("should return error Action Type", async () => {
    axiosMock.get.mockRejectedValue(new Error("Error"));

    expect(await fetchLoginState()).toEqual({
      type: LOGIN_FAILED,
      payload: "Error",
    });
  });
  it("should return data ", async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: "007"
    });
    let KEY = 'foo';
    let VALUE = 'bar';
    expect(localStorage.setItem(KEY,VALUE)).toHaveBeenCalled;
  });
  it("should return isLoggedIn as false", () => {
    expect(logOut()).toEqual({
      type: LOGOUT_SUCCESS,
      payload: { isLoggedIn: false,token:null,user:null },
    });
  });
  it("should return LOGIN FAILED with message Network error", async () => {
    axiosMock.post.mockRejectedValue(({response:null,message:'Network Error'}));
    const user = {
      password: "vinod@vinod.co",
      username: "topsecret",
    };
    expect(await login(user)).toEqual({
      type: LOGIN_FAILED,
      payload: "Network Error",
    });
  });
  it("should return LOGIN FAILED ", async () => {
    axiosMock.post.mockRejectedValue(new Error('Error'));
    const user = {
      password: "vinod@vinod.co",
      username: "topsecret",
    };
    expect(await login(user)).toEqual({
      type: LOGIN_FAILED,
      payload: "Error",
    });
  });
  it("should return type as LOGIN_SUCCESS",async () => {
    const user = {
      password: "vinod@vinod.co",
      username: "topsecret",
    };
    axiosMock.post.mockResolvedValueOnce({
      data: {
        name:'vinod',
        token:null
      },
    });
    expect(await login(user)).toEqual({
      type: LOGIN_SUCCESS,
      payload: {
        isLoggedIn: true,
        token: null,
        user: {
          name: 'vinod',
          role: ROLE_CUSTOMER
        },
      },
    });
  });
});
