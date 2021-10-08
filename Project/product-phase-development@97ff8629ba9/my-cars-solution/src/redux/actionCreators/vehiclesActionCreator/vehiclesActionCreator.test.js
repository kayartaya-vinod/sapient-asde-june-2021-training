/**
@Author Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/

import axiosMock from "axios";
import {
  ADD_VEHICLE,
  ADD_VEHICLE_ERROR,
  GET_VEHICLES,
  GET_VEHICLES_1,
  GET_VEHICLES_ERROR,
} from "../../actionTypes";
import { addVehicles, fetchVehicles } from "./vehiclesActionCreator";

jest.mock("axios");
describe("vehicleActionCreator test", () => {
  it(`should return action type: ${GET_VEHICLES_1} on resolve `, async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {
        name: "honda",
      },
    });

    expect(await fetchVehicles(1)).toEqual({
      type: GET_VEHICLES_1,
      payload: { name: "honda" },
    });
  });
  it(`should return action type: ${GET_VEHICLES} on resolve `, async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {
        name: "honda",
      },
    });

    expect(await fetchVehicles(2)).toEqual({
      type: GET_VEHICLES,
      payload: { name: "honda" },
    });
  });
  it(`should return action type: ${GET_VEHICLES_1} on resolve when no value is passed to function`, async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {
        name: "honda",
      },
    });

    expect(await fetchVehicles()).toEqual({
      type: GET_VEHICLES_1,
      payload: { name: "honda" },
    });
  });

  it(`should return action type: ${GET_VEHICLES_ERROR} on error `, async () => {
    axiosMock.get.mockRejectedValue(new Error("Error"));

    expect(await fetchVehicles(1)).toEqual({
      type: GET_VEHICLES_ERROR,
      payload: { error: "Error" },
    });
  });
});

describe("vehiclesActionCreator test", () => {
  it(`should return action type: ${ADD_VEHICLE} on resolve `, async () => {
    axiosMock.post.mockResolvedValueOnce({
      data: {
        name: "honda",
      },
    });

    expect(await addVehicles("dummyVehicle")).toEqual({
      type: ADD_VEHICLE,
      payload: { name: "honda" },
    });
  });

  it(`should return action type: ${ADD_VEHICLE_ERROR} on error `, async () => {
    axiosMock.post.mockRejectedValue(new Error("Error"));

    expect(await addVehicles("dummyVehicle")).toEqual({
      type: ADD_VEHICLE_ERROR,
      payload: "Error",
    });
  });

  it(`should return action type: ${ADD_VEHICLE_ERROR} on error `, async () => {
    const errObj = { response: { data: { message: "ERROR_FOUND" } } };
    axiosMock.post.mockReturnValue(Promise.reject({ ...errObj }));
    expect(await addVehicles("dummyVehicle")).toEqual({
      type: ADD_VEHICLE_ERROR,
      payload: "ERROR_FOUND",
    });
  });
});
