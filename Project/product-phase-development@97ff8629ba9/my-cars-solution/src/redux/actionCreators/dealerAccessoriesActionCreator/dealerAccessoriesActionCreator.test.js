/**
@author Abhishek Singh abhishek.singh8@publicisssapient.com
*/

import axiosMock from "axios";

import {
  deleteAccessory,
  getDealerAccessories,
  resetDealerAccessories,
  selectDealerAccessory,
} from "./dealerAccessoriesActionCreator";

import {
  DELETE_ACCESSORY,
  GET_DEALER_VEHICLE_ACCESSORIES,
  RESET_DEALER_VEHICLE_ACCESSORIES_STATE,
  SELECT_DEALER_ACCESSORY,
} from "../../actionTypes";

jest.mock("axios");

describe("dealerVehicleActionCreator test", () => {
  it(`should return ${GET_DEALER_VEHICLE_ACCESSORIES} on calling getDealerAccessories on resolve`, async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {
        success: true,
        data: "data",
      },
    });

    expect(await getDealerAccessories(1)).toHaveProperty("type");
  });

  it(`should return ${GET_DEALER_VEHICLE_ACCESSORIES} on calling getDealerAccessories on error`, async () => {
    axiosMock.get.mockRejectedValue(new Error("error"));

    expect(await getDealerAccessories(1)).toHaveProperty("type");
  });

  it(`should return ${RESET_DEALER_VEHICLE_ACCESSORIES_STATE} on calling resetDealerAccessories`, async () => {
    expect(resetDealerAccessories()).toHaveProperty("type");
  });

  it(`should return ${SELECT_DEALER_ACCESSORY} on calling resetDealerAccessories`, async () => {
    expect(selectDealerAccessory()).toHaveProperty("type");
  });

  it(`should return action type: NO_DISPATCH_ACTION on error `, async () => {
    axiosMock.delete.mockRejectedValue(new Error("Error"));

    expect(await deleteAccessory("1")).toEqual({
      type: "NO_DISPATCH_ACTION",
      payload: "Error",
    });
  });

  it(`should return action type: NO_DISPATCH_ACTION on error `, async () => {
    const errObj = { response: { data: { message: "ERROR_FOUND" } } };
    axiosMock.delete.mockReturnValue(Promise.reject({ ...errObj }));
    expect(await deleteAccessory("2")).toEqual({
      type: "NO_DISPATCH_ACTION",
      payload: "ERROR_FOUND",
    });
  });

  it(`should return action type: NO_DISPATCH_ACTION on error `, async () => {
    axiosMock.delete.mockResolvedValueOnce();
    expect(await deleteAccessory("2")).toEqual({
      type: DELETE_ACCESSORY,
      payload: "2",
    });
  });
});
