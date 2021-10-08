/**
@Author Aravind M Krishnan - aravind.m.krishnan@publicissapient.com 
*/

import reducer from "./favoriteVehicleReducer";
import {
  GET_FAVORITE_VEHICLES,
  GET_FAVORITE_VEHICLES_ERROR,
  UPDATE_FAVORITES,
  UPDATE_FAVORITES_ERROR
} from "../../actionTypes";

describe("favoriteVehicleReducer test", () => {
  test("should return the initial state", () => {
    expect(reducer(undefined, {})).toEqual({ error: null, vehicles: [] });
  });

  test("should expect state for get favorites", () => {
    const previousState = { vehicles: [], error: null };

    expect(
      reducer(previousState, {
        type: GET_FAVORITE_VEHICLES,
        payload: {
          data: [],
        },
      })
    ).toEqual({
      error: null,
      vehicles: [...previousState["vehicles"]],
    });
  });

  test("should expect state for update favorites", () => {
    const previousState = { vehicles: [{}], error: null };

    expect(
      reducer(previousState, {
        type: UPDATE_FAVORITES,
        payload: {},
      })
    ).toEqual({
      error: null,
      vehicles: [...previousState["vehicles"]],
    });
  });
  test("should handle an error message for get favorites", () => {
    const previousState = "";
    expect(
      reducer(previousState, {
        type: GET_FAVORITE_VEHICLES_ERROR,
        payload: "error",
      })
    ).toEqual({ error: "error" });
  });
  test("should handle an error message for update favorites", () => {
    const previousState = "";
    expect(
      reducer(previousState, {
        type: UPDATE_FAVORITES_ERROR,
        payload: "error",
      })
    ).toEqual({ error: "error" });
  });
  test("should work with initial state", () => {
    const initialState = { vehicles: [], error: null };
    const state = reducer(initialState, undefined);
    expect(state).toEqual(initialState);
  });
});
