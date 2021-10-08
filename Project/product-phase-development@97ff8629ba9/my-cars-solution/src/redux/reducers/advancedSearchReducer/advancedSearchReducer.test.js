import advancedSearchReducer from "./advancedSearchReducer";
import {
  ADD_REMOVE_BRAND,
  ADD_REMOVE_FUEL_TYPE,
  ADD_REMOVE_VEHICLE_TYPE,
  ADD_REMOVE_VEHICLE_COLOR,
  ADD_REMOVE_AIRBAG_COUNT,
  UPDATE_PRICE,
  UPDATE_YEAR,
} from "../../actionTypes";
const initialState = {
  brand: [],
  price: "",
  vehicleType: [],
  fuelType: [],
  color: [],
  airBagCount: [],
  year: "",
};

describe("advanceSearchReducer tests", () => {
  it("should return default state", () => {
    expect(advancedSearchReducer(undefined, {})).toEqual(initialState);
  });
  it("should return initial state", () => {
    expect(advancedSearchReducer(initialState, undefined)).toEqual(
      initialState
    );
  });
  it("should return ADD_REMOVE_BRAND state", () => {
    const previousState = {
      brand: [],
      price: "",
      vehicleType: [],
      fuelType: [],
      color: [],
      airBagCount: [],
      year: "",
    };
    expect(
      advancedSearchReducer(previousState, {
        type: ADD_REMOVE_BRAND,
        payload: "Maruti",
      })
    ).toEqual({
      brand: ["Maruti"],
      price: "",
      vehicleType: [],
      fuelType: [],
      color: [],
      airBagCount: [],
      year: "",
    });
  });
  it("should return ADD_REMOVE_BRAND state", () => {
    const previousState = {
      brand: ["Maruti"],
      price: "",
      vehicleType: [],
      fuelType: [],
      color: [],
      airBagCount: [],
      year: "",
    };
    expect(
      advancedSearchReducer(previousState, {
        type: ADD_REMOVE_BRAND,
        payload: "Maruti",
      })
    ).toEqual({
      brand: [],
      price: "",
      vehicleType: [],
      fuelType: [],
      color: [],
      airBagCount: [],
      year: "",
    });
  });
  it("should return ADD_REMOVE_FUEL_TYPE state", () => {
    const previousState = {
      brand: [],
      price: "",
      vehicleType: [],
      fuelType: [],
      color: [],
      airBagCount: [],
      year: "",
    };
    expect(
      advancedSearchReducer(previousState, {
        type: ADD_REMOVE_FUEL_TYPE,
        payload: "CNG",
      })
    ).toEqual({
      brand: [],
      price: "",
      vehicleType: [],
      fuelType: ["CNG"],
      color: [],
      airBagCount: [],
      year: "",
    });
  });
  it("should return ADD_REMOVE_FUEL_TYPE state", () => {
    const previousState = {
      brand: [],
      price: "",
      vehicleType: [],
      fuelType: ["CNG"],
      color: [],
      airBagCount: [],
      year: "",
    };
    expect(
      advancedSearchReducer(previousState, {
        type: ADD_REMOVE_FUEL_TYPE,
        payload: "CNG",
      })
    ).toEqual({
      brand: [],
      price: "",
      vehicleType: [],
      fuelType: [],
      color: [],
      airBagCount: [],
      year: "",
    });
  });
  it("should return ADD_REMOVE_VEHICLE_TYPE state", () => {
    const previousState = {
      brand: [],
      price: "",
      vehicleType: [],
      fuelType: [],
      color: [],
      airBagCount: [],
      year: "",
    };
    expect(
      advancedSearchReducer(previousState, {
        type: ADD_REMOVE_VEHICLE_TYPE,
        payload: "SUV",
      })
    ).toEqual({
      brand: [],
      price: "",
      vehicleType: ["SUV"],
      fuelType: [],
      color: [],
      airBagCount: [],
      year: "",
    });
  });
  it("should return ADD_REMOVE_VEHICLE_TYPE state", () => {
    const previousState = {
      brand: [],
      price: "",
      vehicleType: ["SUV"],
      fuelType: [],
      color: [],
      airBagCount: [],
      year: "",
    };
    expect(
      advancedSearchReducer(previousState, {
        type: ADD_REMOVE_VEHICLE_TYPE,
        payload: "SUV",
      })
    ).toEqual({
      brand: [],
      price: "",
      vehicleType: [],
      fuelType: [],
      color: [],
      airBagCount: [],
      year: "",
    });
  });
  it("should return ADD_REMOVE_VEHICLE_COLOR state", () => {
    const previousState = {
      brand: [],
      price: "",
      vehicleType: [],
      fuelType: [],
      color: [],
      airBagCount: [],
      year: "",
    };
    expect(
      advancedSearchReducer(previousState, {
        type: ADD_REMOVE_VEHICLE_COLOR,
        payload: "red",
      })
    ).toEqual({
      brand: [],
      price: "",
      vehicleType: [],
      fuelType: [],
      color: ["red"],
      airBagCount: [],
      year: "",
    });
  });
  it("should return ADD_REMOVE_VEHICLE_COLOR state", () => {
    const previousState = {
      brand: [],
      price: "",
      vehicleType: [],
      fuelType: [],
      color: ["red"],
      airBagCount: [],
      year: "",
    };
    expect(
      advancedSearchReducer(previousState, {
        type: ADD_REMOVE_VEHICLE_COLOR,
        payload: "red",
      })
    ).toEqual({
      brand: [],
      price: "",
      vehicleType: [],
      fuelType: [],
      color: [],
      airBagCount: [],
      year: "",
    });
  });
  it("should return ADD_REMOVE_AIRBAG_COUNT state", () => {
    const previousState = {
      brand: [],
      price: "",
      vehicleType: [],
      fuelType: [],
      color: [],
      airBagCount: [],
      year: "",
    };
    expect(
      advancedSearchReducer(previousState, {
        type: ADD_REMOVE_AIRBAG_COUNT,
        payload: "2",
      })
    ).toEqual({
      brand: [],
      price: "",
      vehicleType: [],
      fuelType: [],
      color: [],
      airBagCount: ["2"],
      year: "",
    });
  });
  it("should return ADD_REMOVE_AIRBAG_COUNT state", () => {
    const previousState = {
      brand: [],
      price: "",
      vehicleType: [],
      fuelType: [],
      color: [],
      airBagCount: ["2"],
      year: "",
    };
    expect(
      advancedSearchReducer(previousState, {
        type: ADD_REMOVE_AIRBAG_COUNT,
        payload: "2",
      })
    ).toEqual({
      brand: [],
      price: "",
      vehicleType: [],
      fuelType: [],
      color: [],
      airBagCount: [],
      year: "",
    });
  });
  it("should return UPDATE_PRICE state", () => {
    const previousState = {
      brand: [],
      price: "",
      vehicleType: [],
      fuelType: [],
      color: [],
      airBagCount: [],
      year: "",
    };
    expect(
      advancedSearchReducer(previousState, {
        type: UPDATE_PRICE,
        payload: "100000,200000",
      })
    ).toEqual({
      brand: [],
      price: "100000,200000",
      vehicleType: [],
      fuelType: [],
      color: [],
      airBagCount: [],
      year: "",
    });
  });
  it("should return UPDATE_YEAR state", () => {
    const previousState = {
      brand: [],
      price: "",
      vehicleType: [],
      fuelType: [],
      color: [],
      airBagCount: [],
      year: "",
    };
    expect(
      advancedSearchReducer(previousState, {
        type: UPDATE_YEAR,
        payload: "2012",
      })
    ).toEqual({
      brand: [],
      price: "",
      vehicleType: [],
      fuelType: [],
      color: [],
      airBagCount: [],
      year: "2012",
    });
  });
});
