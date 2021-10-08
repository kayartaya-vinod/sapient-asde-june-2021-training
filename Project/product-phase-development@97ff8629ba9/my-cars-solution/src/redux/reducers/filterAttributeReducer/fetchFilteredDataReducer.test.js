import fetchFilteredDataReducer from "./fetchFilteredDataReducer";
import {
  FETCH_FILTERED_LIST,
  FETCH_FILTERED_LIST_ERROR,
} from "../../actionTypes";

describe("fetchFilteredDataReducer test", () => {
  it("should return default state", () => {
    const state = fetchFilteredDataReducer({ vehicles: [], error: null }, {});
    expect(state).toEqual({ vehicles: [], error: null });
  });
  it("should return start state", () => {
    const state = fetchFilteredDataReducer(undefined, {});
    expect(state).toEqual({
      error: null,
      start: true,
      vehicles: [],
    });
  });
  it("should should get filtered data", () => {
    const previousState = {};
    expect(
      fetchFilteredDataReducer(previousState, {
        type: FETCH_FILTERED_LIST,
        payload: { content: [{ brand: "Maruti" }], totalPages: 1 },
      })
    ).toEqual({ vehicles: [{ brand: "Maruti" }], start: false, totalPages: 1 });
  });
  it("should handle an error message", () => {
    const previousState = [];
    expect(
      fetchFilteredDataReducer(previousState, {
        type: FETCH_FILTERED_LIST_ERROR,
        payload: "error",
      })
    ).toEqual({ error: "error" });
  });
});
