/**
@Author Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/

import {
  GET_VEHICLE_ACCESSORY,
  GET_VEHICLE_ACCESSORY_ERROR,
} from "../../actionTypes";
import vehicleAccessoryReducer from "./vehicleAccessoryReducer";

describe("vehicleAccessoryReducer Tests", () => {
  it("should return initial state", () => {
    expect(vehicleAccessoryReducer()).toEqual({});
  });
  it("should test GET_VEHICLE_ACCESSORY type", () => {
    expect(
      vehicleAccessoryReducer(
        {},
        { type: GET_VEHICLE_ACCESSORY, payload: { name: "accessory" } }
      )
    ).toEqual({ name: "accessory" });
  });
  it("should test GET_VEHICLE_ACCESSORY_ERROR type", () => {
    expect(
      vehicleAccessoryReducer(
        {},
        { type: GET_VEHICLE_ACCESSORY_ERROR, payload: { error: "error" } }
      )
    ).toEqual({ error: "error" });
  });
});
