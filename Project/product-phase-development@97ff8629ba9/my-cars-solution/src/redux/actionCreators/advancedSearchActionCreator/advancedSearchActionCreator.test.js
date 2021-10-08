import {
  ADD_REMOVE_BRAND,
  ADD_REMOVE_FUEL_TYPE,
  ADD_REMOVE_VEHICLE_TYPE,
  ADD_REMOVE_VEHICLE_COLOR,
  ADD_REMOVE_AIRBAG_COUNT,
  UPDATE_PRICE,
  UPDATE_YEAR,
} from "../../actionTypes";
import {
  addOrRemoveBrand,
  addOrRemoveAirBagCount,
  addOrRemoveFuelType,
  addOrRemoveVehicleColor,
  addOrRemoveVehicleType,
  updatePrice,
  updateYear,
} from "./advancedSearchActionCreator";

describe("advancedSearchActionCreator test", () => {
  it("should return add remove brand Action Type", () => {
    expect(addOrRemoveBrand("Maruti")).toEqual({
      type: ADD_REMOVE_BRAND,
      payload: "Maruti",
    });
  });
  it("should return add remove vehicle type Action Type", () => {
    expect(addOrRemoveVehicleType("SUV")).toEqual({
      type: ADD_REMOVE_VEHICLE_TYPE,
      payload: "SUV",
    });
  });
  it("should return add remove vehicle color Action Type", () => {
    expect(addOrRemoveVehicleColor("red")).toEqual({
      type: ADD_REMOVE_VEHICLE_COLOR,
      payload: "red",
    });
  });
  it("should return add remove fuel type Action Type", () => {
    expect(addOrRemoveFuelType("CNG")).toEqual({
      type: ADD_REMOVE_FUEL_TYPE,
      payload: "CNG",
    });
  });
  it("should return add remove air bag count Action Type", () => {
    expect(addOrRemoveAirBagCount("2")).toEqual({
      type: ADD_REMOVE_AIRBAG_COUNT,
      payload: "2",
    });
  });
  it("should return update price Action Type", () => {
    expect(updatePrice("1000000,2000000")).toEqual({
      type: UPDATE_PRICE,
      payload: "1000000,2000000",
    });
  });
  it("should return update year Action Type", () => {
    expect(updateYear("2020")).toEqual({
      type: UPDATE_YEAR,
      payload: "2020",
    });
  });
});
