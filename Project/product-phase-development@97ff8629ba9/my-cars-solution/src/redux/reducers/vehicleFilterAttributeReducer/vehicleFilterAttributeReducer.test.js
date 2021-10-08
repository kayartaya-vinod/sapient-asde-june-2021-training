import {
  GET_VEHICLE_FILTER_ATTRIBUTE,
  UPDATE_VEHICLE_FILTER,
} from "../../actionTypes";
import vehicleFilterAttributeReducer from "./vehicleFilterAttributeReducer";
const initialState = [];

describe("vehicleFilterAttributeReducer tests", () => {
  it("should return the initial state", () => {
    const state = vehicleFilterAttributeReducer(initialState, {});
    expect(state).toEqual(initialState);
    expect(vehicleFilterAttributeReducer(initialState, {})).toEqual(
      initialState
    );
  });

  it(`should change the state on ${GET_VEHICLE_FILTER_ATTRIBUTE}`, () => {
    const action = {
      type: GET_VEHICLE_FILTER_ATTRIBUTE,
      payload: [{ Attributes: "attributes" }],
    };
    const state = vehicleFilterAttributeReducer(initialState, action);

    expect(state).toEqual([{ Attributes: "attributes" }]);
  });

  it(`should change the state on ${UPDATE_VEHICLE_FILTER}`, () => {
    const ins = [{ attribute: "attributes" }];
    const action = {
      type: UPDATE_VEHICLE_FILTER,
      payload: { attribute: "attributes", queryField: ["queryField"] },
    };
    const state = vehicleFilterAttributeReducer(ins, action);
    expect(state).toEqual([
      { attribute: "attributes", queryField: ["queryField"] },
    ]);
  });
});
