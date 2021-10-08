/**
@Author Sumitesh Naithani sumitesh.naithani@publicissapient.com
*/
import axiosMock from "axios";
jest.mock("axios");
import {cleanup } from "@testing-library/react";

import { ADD_ACCESSORY, ADD_ACCESSORY_ERROR } from "../../actionTypes";
import { addAccessory } from "./addAccessoryActionCreator";

describe("vehiclesActionCreator test", () => {
  const origConsole = console.error;
  beforeEach(() => {
    console.error = jest.fn();
  });

  afterEach(() => {
    console.error = origConsole;
    cleanup();
  });

  it(`should return action type: ${ADD_ACCESSORY} on resolve `, async () => {
    axiosMock.post.mockResolvedValueOnce({
      data: {
        name: "An accessory",
      },
    });

    expect(await addAccessory("dummyAccessory")).toEqual({
      type: ADD_ACCESSORY,
      payload: { name: "An accessory" },
    });
  });

  it(`should return action type: ${ADD_ACCESSORY_ERROR} on error `, async () => {
    axiosMock.post.mockRejectedValue(new Error("Error"));

    expect(await addAccessory("dummyAccessory")).toEqual({
      type: ADD_ACCESSORY_ERROR,
      payload: "Error",
    });
  });

  it(`should return action type: ${ADD_ACCESSORY_ERROR} on error `, async () => {
    const errObj = { response: { data: { message: "ERROR_FOUND" } } };
    axiosMock.post.mockReturnValue(Promise.reject({ ...errObj }));
    expect(await addAccessory("dummyAccessory")).toEqual({
      type: ADD_ACCESSORY_ERROR,
      payload: "ERROR_FOUND",
    });
  });
});
