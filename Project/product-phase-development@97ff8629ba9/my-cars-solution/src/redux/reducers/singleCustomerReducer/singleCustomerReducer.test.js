/**
@Author1 Mutharasan E - mutharasan.e@publicissapient.com 
@Author2 Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/

import reducer from "./singleCustomerReducer";
import {
  GET_CUSTOMER_BY_ID,
  GET_CUSTOMER_BY_ID_ERROR,
  UPDATE_CUSTOMER,
  UPDATE_CUSTOMER_ERROR,
} from "../../actionTypes";

describe("singleCustomerReducer test", () => {
  test("should return the initial state", () => {
    expect(reducer(undefined, {})).toEqual({
      alternateEmail: '',
      address: {
          default: {
              buildingNo: '',
              street: '',
              landMark: '',
              city: '',
              state: '',
              pinCode: '',
          },
          anotherAddress: {
              buildingNo: '',
              street: '',
              landMark: '',
              city: '',
              state: '',
              pinCode: '',
          },
      },
      contactNo: '',
      alternateContactNo: '',
      wishlist: [],
      isVerified: false,
      userId: '',
  });
  });

  test("should get an customer object", () => {
    const previousState = "";
    expect(
      reducer(previousState, {
        type: GET_CUSTOMER_BY_ID,
        payload: { name: "Muthu" },
      })
    ).toEqual({ name: "Muthu" });
  });

  test("should get update object", () => {
    const previousState = "";
    expect(
      reducer(previousState, {
        type: UPDATE_CUSTOMER,
        payload: { name: "YOGI" },
      })
    ).toEqual({ name: "YOGI" });
  });

  test("should handle an error message", () => {
    const previousState = "";
    expect(
      reducer(previousState, {
        type: GET_CUSTOMER_BY_ID_ERROR,
        payload: "error",
      })
    ).toEqual({ 0: "e", 1: "r", 2: "r", 3: "o", 4: "r" });
  });

  test("should handle an error message for UPDATE_CUSTOMER", () => {
    const previousState = "";
    expect(
      reducer(previousState, {
        type: UPDATE_CUSTOMER_ERROR,
        payload: "error",
      })
    ).toEqual({ 0: "e", 1: "r", 2: "r", 3: "o", 4: "r" });
  });
  test("should get default state ", () => {
    const previousState = "";
    expect(
      reducer(previousState, {
        type: "default",
        payload: { name: "YOGI" },
      })
    ).toEqual("");
  });
  test("should work with initial state", () => {
    const initialState = {
      alternateEmail: '',
      address: {
          default: {
              buildingNo: '',
              street: '',
              landMark: '',
              city: '',
              state: '',
              pinCode: '',
          },
          anotherAddress: {
              buildingNo: '',
              street: '',
              landMark: '',
              city: '',
              state: '',
              pinCode: '',
          },
      },
      contactNo: '',
      alternateContactNo: '',
      wishlist: [],
      isVerified: false,
      userId: '',
  };
    const state = reducer(initialState, undefined);
    expect(state).toEqual(initialState);
  });
});
