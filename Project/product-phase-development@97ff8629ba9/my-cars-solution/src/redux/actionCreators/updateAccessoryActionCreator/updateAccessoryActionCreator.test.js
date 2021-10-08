import axiosMock from "axios";
import {
  UPDATE_ACCESSORY_SUCCESS,
  UPDATE_ACCESSORY_FAILED,
  UPDATE_ACCESSORY_FAILED_WITH_RESPONSE,
} from "../../actionTypes";
import { updateAccessory } from "./updateAccessoryActionCreator";
/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
jest.mock("axios");

describe("updateAccessoryActionCreator", () => {
  it(`should return success ${UPDATE_ACCESSORY_SUCCESS}  Action Type`, async () => {
    axiosMock.put.mockResolvedValueOnce({
      data: {},
    });

    expect(await updateAccessory()).toEqual({
      type: UPDATE_ACCESSORY_SUCCESS,
    });
  });

  it(`should return action type: ${UPDATE_ACCESSORY_FAILED} on error `, async () => {
    axiosMock.put.mockRejectedValue(new Error("Error"));

    expect(await updateAccessory("dummyVehicle")).toEqual({
      type: UPDATE_ACCESSORY_FAILED,
      payload: "Error",
    });
  });

  it(`should return action type: ${UPDATE_ACCESSORY_FAILED_WITH_RESPONSE} on error `, async () => {
    const errObj = { response: { data: { message: "ERROR_FOUND" } } };
    axiosMock.put.mockReturnValue(Promise.reject({ ...errObj }));
    expect(await updateAccessory("dummyVehicle")).toEqual({
      type: UPDATE_ACCESSORY_FAILED_WITH_RESPONSE,
      payload: "ERROR_FOUND",
    });
  });
});
