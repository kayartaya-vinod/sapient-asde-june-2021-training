import { ADD_TO_COMPARE, LOGOUT_SUCCESS, REMOVE_FROM_COMPARE } from "../../actionTypes";
import vehicleCompareListReducer from "./vehicleCompareListReducer";


const initialState = [];
describe("vehicleCompareReducer Test", () => {
  it("should return default state", () => {
    expect(vehicleCompareListReducer(undefined, {})).toEqual(initialState);
  });
  it("should return ADD_TO_COMPARE state", () => {
    const previousState = [];
    expect(
      vehicleCompareListReducer(previousState, {
        type: ADD_TO_COMPARE,
        payload: "id1",
      })).toEqual(["id1"]);
  });
  it("should perform REMOVE_FROM_COMPARE state", () => {
    const previousState = ["id1"];
    expect(
      vehicleCompareListReducer(previousState, {
        type: REMOVE_FROM_COMPARE,
        payload: "id1",
      })
    ).toEqual([]);
  });

  it("should return initial state on LOGOUT_SUCCESS", () => {
    const previousState = ["id1"];
    expect(
      vehicleCompareListReducer(previousState, {
        type: LOGOUT_SUCCESS,
      })
    ).toEqual([]);
  });
});
