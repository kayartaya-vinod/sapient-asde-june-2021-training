/**
@Author Sakshi Yadav - sakshi.yadav@publicissapient.com 
*/
import { fetchVehicleById } from "./vehicleActionCreator";
import axiosMock from "axios";
import { GET_VEHICLE_BY_ID } from "../../actionTypes";
import { waitFor } from "@testing-library/react";

jest.mock("axios");
describe("carActionCreator test", () => {
  it(`should return action type: ${GET_VEHICLE_BY_ID} on resolve `, async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {
        data: {
          brand: "Honda",
        },
      },
    });

    waitFor(async () => {
      expect(await fetchVehicleById("vehicle123")).toEqual({
        type: GET_VEHICLE_BY_ID,
        payload: { data: { brand: "Honda" } },
      });
    });
  });
});
