/**
@Author Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/

import reducer, { addVehicle } from "./vehiclesReducer";
import {
  ADD_VEHICLE,
  ADD_VEHICLE_ERROR,
  GET_VEHICLES,
  GET_VEHICLES_ERROR,
  RESET_ADD_VEHICLE,
} from "../../actionTypes";

describe("vehicleReducer test", () => {
  test("should return the initial state", () => {
    expect(reducer(undefined, {})).toEqual({ error: null, vehicles: [] });
  });

  test("should expext state", () => {
    const previousState = { vehicles: [], error: null };

    expect(
      reducer(previousState, {
        type: GET_VEHICLES,
        payload: {
          content: [],
          totalPages: 1,
        },
      })
    ).toEqual({
      error: null,
      vehicles: [...previousState["vehicles"]],
      totalPages: 1,
    });
  });
  test("should handle an error message", () => {
    const previousState = "";
    expect(
      reducer(previousState, {
        type: GET_VEHICLES_ERROR,
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

/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
describe("addVehicleReducer test", () => {
  test("should return the initial state", () => {
    expect(addVehicle(undefined, {})).toEqual({ success: null, message: null });
  });

  test("should expect state", () => {
    expect(
      addVehicle("", {
        type: ADD_VEHICLE,
        payload: {
          success: true,
        },
      })
    ).toEqual({
      success: true,
    });
  });

  test("should handle an error message", () => {
    expect(
      addVehicle("", {
        type: ADD_VEHICLE_ERROR,
        payload: "error",
      })
    ).toEqual({ success: false, message: "error" });
  });
  test("should work with initial state", () => {
    const initialState = { success: null, message: null };
    const state = addVehicle(initialState, undefined);
    expect(state).toEqual(initialState);
  });
  test("should work with initial state", () => {
    const state = addVehicle(undefined, { type: RESET_ADD_VEHICLE });
    expect(state).toEqual({ success: null });
  });
});
