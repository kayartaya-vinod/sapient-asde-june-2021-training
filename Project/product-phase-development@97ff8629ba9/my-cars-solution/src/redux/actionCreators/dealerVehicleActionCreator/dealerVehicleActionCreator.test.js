/**
@author Abhishek Singh abhishek.singh8@publicisssapient.com
*/

import axiosMock from "axios";

import { getDealerVehicles, selectDealerVehicles, removeDealerVehicles, deleteSelectedVehicles, downloadSelectedVehicles, filterDealerVehicles } from "./dealerVehicleActionCreator";

import { FILTER_DEALER_VEHICLES, GET_DEALER_VEHICLES, NO_DEALER_VECHICLES_DISPATCH_ACTION, REMOVE_SELECTED_DEALER_VEHICLES, SELECT_DEALER_VEHICLES } from "../../actionTypes";

jest.mock("axios");

describe("dealerVehicleActionCreator test", () => {
  it(`should return ${GET_DEALER_VEHICLES} on calling getDealerVehicles on resolve`, async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {
        success: true,
        data: "data"
      },
    });

    expect(
      await getDealerVehicles(1)
    ).toHaveProperty("type");

  });

  it(`should return ${NO_DEALER_VECHICLES_DISPATCH_ACTION} on calling getDealerVehicles on error`, async () => {
    axiosMock.get.mockRejectedValue(new Error("error"));

    expect(
      await getDealerVehicles(1)
    ).toHaveProperty("type");

  });

  it(`should return ${SELECT_DEALER_VEHICLES} on calling selectDealerVehicles on resolve`, async () => {
    expect(
      selectDealerVehicles(["id"])
    ).toHaveProperty("type");
  });

  it(`should return ${REMOVE_SELECTED_DEALER_VEHICLES} on calling removeDealerVehicles on resolve`, async () => {
    expect(
      removeDealerVehicles(["id"])
    ).toHaveProperty("type");
  });

  it(`should return ${REMOVE_SELECTED_DEALER_VEHICLES} on calling deleteSelectedVehicles on resolve`, async () => {
    axiosMock.delete.mockResolvedValueOnce();

    expect(
      await deleteSelectedVehicles(["id"])
    ).toHaveProperty("type");

  });

  it(`should return ${NO_DEALER_VECHICLES_DISPATCH_ACTION} on calling deleteSelectedVehicles on error`, async () => {
    axiosMock.delete.mockRejectedValue(new Error("error"));

    expect(
      await deleteSelectedVehicles(["id"])
    ).toHaveProperty("type");

  });

  it(`should return ${NO_DEALER_VECHICLES_DISPATCH_ACTION} on calling deleteSelectedVehicles when vehicleIds.length is zero`, async () => {

    expect(
      await deleteSelectedVehicles([])
    ).toHaveProperty("type");
  });

  it(`should return ${NO_DEALER_VECHICLES_DISPATCH_ACTION} on calling downloadSelectedVehicles on resolve`, async () => {
    axiosMock.get.mockResolvedValueOnce();

    expect(
      await downloadSelectedVehicles(["id"])
    ).toHaveProperty("type");
  });

  it(`should return ${NO_DEALER_VECHICLES_DISPATCH_ACTION} on calling downloadSelectedVehicles on error`, async () => {
    axiosMock.get.mockRejectedValue(new Error("error"));

    expect(
      await downloadSelectedVehicles(["id"])
    ).toHaveProperty("type");
  });

  it(`should return ${NO_DEALER_VECHICLES_DISPATCH_ACTION} on calling downloadSelectedVehicles when vehicleIds.length is zero`, async () => {

    expect(
      await downloadSelectedVehicles([])
    ).toHaveProperty("type");
  });

  it(`should return ${FILTER_DEALER_VEHICLES} on calling filterDealerVehicles on resolve`, async () => {
    expect(
      filterDealerVehicles(["id"])
    ).toHaveProperty("type");
  });

});