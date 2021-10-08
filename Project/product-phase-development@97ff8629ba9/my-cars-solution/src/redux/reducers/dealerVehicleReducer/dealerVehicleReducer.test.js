/**
@author Abhishek Singh abhishek.singh8@publicisssapient.com
*/

import { GET_DEALER_VEHICLES, SELECT_DEALER_VEHICLES, REMOVE_SELECTED_DEALER_VEHICLES, FILTER_DEALER_VEHICLES, RESET_STATE } from "../../actionTypes";

import dealerVehicleReducer from "./dealerVehicleReducer";

const initialState = {
  isFirst: true,
  isLast: true,
  vehicles: [],
  selectedVehicles: [],
  filteredVehicles: [],
};

describe("dealerVehicleReducer tests", () => {
  it("should return the initial state", () => {
    const state = dealerVehicleReducer(initialState, {});
    expect(state).toEqual(initialState);
    expect(dealerVehicleReducer(undefined, {})).toEqual(initialState);
  });

  it(`should change the state on ${GET_DEALER_VEHICLES}`, () => {
    const content = ["vehicle"];
    const first = true;
    const last = true;
    const action = {
      type: GET_DEALER_VEHICLES, payload: {
        content,
        first,
        last
      }
    };
    const state = dealerVehicleReducer(initialState, action);

    expect(state).toEqual({ ...initialState, vehicles: content, filteredVehicles: content, isFirst: first, isLast: last });
  });

  it(`should change the state on ${SELECT_DEALER_VEHICLES}`, () => {
    const vehicleIds = ["id1"];
    const action = {
      type: SELECT_DEALER_VEHICLES, payload: vehicleIds
    };
    const state = dealerVehicleReducer(initialState, action);

    expect(state).toEqual({ ...initialState, selectedVehicles: vehicleIds });

  });

  it(`should change the state on ${SELECT_DEALER_VEHICLES}`, () => {
    const vehicleIds = ["id1"];
    const action = {
      type: REMOVE_SELECTED_DEALER_VEHICLES, payload: vehicleIds
    };
    const state = dealerVehicleReducer({ ...initialState, selectedVehicles: ["id1", "id2"] }, action);

    expect(state).toEqual({ ...initialState, selectedVehicles: ["id2"] });

  });

  it(`should change the state on ${FILTER_DEALER_VEHICLES}`, () => {
    const vehicle = ["id1"];
    const action = {
      type: FILTER_DEALER_VEHICLES, payload: vehicle
    };
    const state = dealerVehicleReducer(initialState, action);

    expect(state).toEqual({ ...initialState, filteredVehicles: vehicle });

  });

  it(`should change the state on ${RESET_STATE}`, () => {
    const action = {
      type: RESET_STATE
    };
    const state = dealerVehicleReducer(initialState, action);

    expect(state).toEqual({ ...initialState });

  });

});