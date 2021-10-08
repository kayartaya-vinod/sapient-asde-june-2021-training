/**
@Author Aravind M Krishnan - aravind.m.krishnan@publicissapient.com 
*/

import axiosMock from "axios";
import {
  GET_FAVORITE_VEHICLES,
  GET_FAVORITE_VEHICLES_ERROR,
  UPDATE_FAVORITES,
  UPDATE_FAVORITES_ERROR,
} from "../../actionTypes";
import {
  getFavoriteVehicles,
  updateFavorites,
} from "./favoriteVehicleActionCreator";

jest.mock("axios");
describe("favoriteVehicleActionCreator test", () => {
  it(`should return action type: ${GET_FAVORITE_VEHICLES} on resolve `, async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {
        name: "honda",
      },
    });

    expect(await getFavoriteVehicles()).toEqual({
      type: GET_FAVORITE_VEHICLES,
      payload: { name: "honda" },
    });
  });

  it(`should return action type: ${GET_FAVORITE_VEHICLES_ERROR} on error `, async () => {
    axiosMock.get.mockRejectedValue(new Error("Error"));

    expect(await getFavoriteVehicles()).toEqual({
      type: GET_FAVORITE_VEHICLES_ERROR,
      payload: { error: "Error" },
    });
  });

  it(`should return action type:${UPDATE_FAVORITES} on resolve`, async () => {
    axiosMock.put.mockResolvedValueOnce({
      data: {
        success: true,
      },
    });
    const v = { id: "1", brand: "tata" };
    expect(await updateFavorites(v)).toEqual({
      type: UPDATE_FAVORITES,
      payload: { id: "1", brand: "tata" },
    });
  });

  it(`should return action type: ${UPDATE_FAVORITES_ERROR} on error `, async () => {
    axiosMock.put.mockRejectedValue(new Error("Error"));
    const v = { id: "1", brand: "tata" };
    expect(await updateFavorites(v)).toEqual({
      type: UPDATE_FAVORITES_ERROR,
      payload: "Error",
    });
  });
});
