/**
# @Author Yogamber Singh Negi yogamber.negi@publicissapient.com
@Author Aditya Gheewala aditya.gheewala@publicissapient.com [test("should get an accessory search object")]
# */
import reducer from "./searchReducer";
import {
  SEARCH,
  SEARCH_ERROR,
  SEARCH_ACCESSORIES,
  SEARCH_ACCESSORIES_1,
  SEARCH_1,
} from "../../actionTypes";

describe("searchReducer test", () => {
  test("should return the initial state", () => {
    expect(reducer([], {})).toEqual([]);
  });

  test("should get a search object", () => {
    const previousState = { vehicles: [] };
    expect(
      reducer(previousState, {
        type: SEARCH,
        payload: [],
      })
    ).toEqual({ vehicles: [...previousState["vehicles"]]});

    expect(
      reducer(previousState, {
        type: SEARCH_1,
        payload: [],
      })
    ).toEqual({ vehicles: []});
  });

  test("should get an accessory search object", () => {
    const previousState = { accessories: [] };
    expect(
      reducer(previousState, {
        type: SEARCH_ACCESSORIES,
        payload: [],
      })
    ).toEqual({ accessories: [...previousState["accessories"]]});

    expect(
      reducer(previousState, {
        type: SEARCH_ACCESSORIES_1,
        payload: [],
      })
    ).toEqual({ accessories: []});
  });

  test("should handle an error message", () => {
    const previousState = [];
    expect(
      reducer(previousState, {
        type: SEARCH_ERROR,
        payload: "error",
      })
    ).toEqual({"error": "error"});
  });

  test("should handle an error message", () => {
    const previousState = [];
    expect(
      reducer(previousState, {
        type: "FOR_DEFAULT",
        payload: "error",
      })
    ).toEqual([]);
  });
});
