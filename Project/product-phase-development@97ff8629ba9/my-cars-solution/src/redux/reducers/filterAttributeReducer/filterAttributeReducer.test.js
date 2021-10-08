import reducer from "./filterAttributeReducer";
import {
  SET_FILTER_ATTRIBUTES
} from "../../actionTypes";

describe("filterAttributeReducer test", () => {
  it("should return the initial state", () => {
    expect(reducer(undefined, {})).toEqual("");
  });

  it("should get attribute query", () => {
    const previousState = "";
    expect(
      reducer(previousState, {
        type: SET_FILTER_ATTRIBUTES,
        payload: "?brand=Maruti",
      })
    ).toEqual("?brand=Maruti");
  });
});
