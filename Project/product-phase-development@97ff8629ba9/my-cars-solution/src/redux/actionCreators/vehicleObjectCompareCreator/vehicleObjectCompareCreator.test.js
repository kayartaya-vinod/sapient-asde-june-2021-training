import { fetchVehiclesForCompare } from "./vehicleObjectCompareCreator";
import {
  FETCH_VEHICLE_COMPARE,
  FETCH_VEHICLE_COMPARE_ERROR,
} from "../../actionTypes";
import axiosMock from "axios";

const contents = {
  basicInformation: {
    brand: ["Honda", "Honda", "Honda"],
    model: ["c100", "d100", "r560"],
    price: [918231.0, null, true],
  },
};
const idss = ["id1"];
const imagess = ["img1.jpg"];
jest.mock("axios");
describe("filterActionCreator test", () => {
  it(`should return action type: ${FETCH_VEHICLE_COMPARE} on resolve `, async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {
        data:
            "vehicle Object compare"
      },
    });
    expect(await  fetchVehiclesForCompare("?ids=id1")).toEqual({
      type: FETCH_VEHICLE_COMPARE,
      payload: "vehicle Object compare"
    });
  });

  it(`should return action type: ${FETCH_VEHICLE_COMPARE_ERROR} on error `, async () => {
    axiosMock.get.mockRejectedValue(new Error("Error"));

    expect(await fetchVehiclesForCompare("")).toEqual({
      type: FETCH_VEHICLE_COMPARE_ERROR,
      payload: "Error" ,
    });
  });
});
