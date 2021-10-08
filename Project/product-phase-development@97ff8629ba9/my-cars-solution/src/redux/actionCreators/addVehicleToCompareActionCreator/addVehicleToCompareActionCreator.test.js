import { getAllBrands } from "./addVehicleToCompareActionCreator";
import axiosMock from "axios";
import { ADD_VEHICLE_TO_COMPARE, ADD_VEHICLE_TO_COMPARE_ERROR} from "../../actionTypes";

jest.mock("axios");
describe("SearchCreator test", () => {
  it(`should return action type: ${ADD_VEHICLE_TO_COMPARE} on resolve `, async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: [
        "c300","c400"
      ],
    });

    expect(await getAllBrands("mercedes","")).toEqual({
      type: ADD_VEHICLE_TO_COMPARE,
      payload: [
        "c300","c400"
      ],
    });
  });

  
  it(`should return action type: ${ADD_VEHICLE_TO_COMPARE_ERROR} on error `, async () => {
    axiosMock.get.mockRejectedValue(new Error([]));

    expect(await getAllBrands("abc")).toEqual({
      type: ADD_VEHICLE_TO_COMPARE_ERROR,
      payload: {data:[]},
    });
  });

  
});
