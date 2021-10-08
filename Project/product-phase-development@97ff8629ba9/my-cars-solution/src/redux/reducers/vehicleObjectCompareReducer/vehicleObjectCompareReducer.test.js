import  vehicleObjectCompareReducer  from "./vehicleObjectCompareReducer";
import {
  FETCH_VEHICLE_COMPARE,
  FETCH_VEHICLE_COMPARE_ERROR,
} from "../../actionTypes";
import axiosMock from "axios";

describe("fetch vehicle compare reducer test", () => {
  it("should return default state", () => {
    const state = vehicleObjectCompareReducer({
      content: {},
      ids: [],
      images: [],
      error: null,
    },{});
    expect(state).toEqual({ content: {}, ids: [], images: [], error: null });
  });

  it("should return start state", () => {
    const state = vehicleObjectCompareReducer(undefined, {});
    expect(state).toEqual({
      content: {},
      ids: [],
      images: [],
      error: null,
    });
  });
  it("should should get filtered data", () => {
    const previousState = {};
    expect(
      vehicleObjectCompareReducer(previousState, {
        type: FETCH_VEHICLE_COMPARE,
        
        payload: { content :{
      "basicInformation": {
        "brand": ["Honda", "Honda", "Honda"],
        "model" :["c100","d100","r560"],
        "price": [918231.0,null,true],}},
        ids : ["id1"],
        images : ["img1.jpg"] },
      })
    ).toEqual({ content : {
      "basicInformation": {
        "brand": ["Honda", "Honda", "Honda"],
        "model" :["c100","d100","r560"],
        "price": [918231.0,null,true],}},
        ids : ["id1"],
        images : ["img1.jpg"] });
  });
  it("should handle an error message", () => {
    const previousState = {};
    expect(
      vehicleObjectCompareReducer(previousState, {
        type: FETCH_VEHICLE_COMPARE_ERROR,
        payload: "error"}
    )).toEqual( {error: "error"});
  });
});
