import { fetchFilteredData } from "./filterActionCreator";
import axiosMock from "axios";
import {
  FETCH_FILTERED_LIST,
  FETCH_FILTERED_LIST_ERROR,
} from "../../actionTypes";

jest.mock("axios");
describe("filterActionCreator test", () => {

    it(`should return action type: ${FETCH_FILTERED_LIST} on resolve `, async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {
        data: "filtered data"
      },
    });
    expect(await fetchFilteredData("?brand=Maruti")).toEqual({
      type: FETCH_FILTERED_LIST,
      payload: "filtered data",
    });
  });

  it(`should return action type: ${FETCH_FILTERED_LIST_ERROR} on error `, async () => {
    axiosMock.get.mockRejectedValue(new Error("Error"));

    expect(await fetchFilteredData("")).toEqual({
      type: FETCH_FILTERED_LIST_ERROR,
      payload: { error: "Error" },
    });
  });
});
