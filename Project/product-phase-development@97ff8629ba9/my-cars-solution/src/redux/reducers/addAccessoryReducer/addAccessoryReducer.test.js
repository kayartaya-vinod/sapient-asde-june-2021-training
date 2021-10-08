/**
@Author Sumitesh Naithani sumitesh.naithani@publicissapient.com
*/
import {
  ADD_ACCESSORY,
  ADD_ACCESSORY_ERROR,
  RESET_ADD_ACCESSORY,
} from "../../actionTypes";
import addAccessoryReducer from "./addAccessoryReducer";
import { cleanup } from "@testing-library/react";
describe("addAccessoryReducer tests", () => {
  const origConsole = console.error;
  beforeEach(() => {
    console.error = jest.fn();
  });

  afterEach(() => {
    console.error = origConsole;
    cleanup();
  });
  it("should return default state", () => {
    const state = addAccessoryReducer({}, {});
    expect(state).toEqual({});
  });
  it("should check ADD_ACCESSORY state", () => {
    const state = addAccessoryReducer(
      {},
      { type: ADD_ACCESSORY, payload: "test" }
    );
    expect(state).toEqual({ 0: "t", 1: "e", 2: "s", 3: "t", success: true });
  });
  it("should check ADD_ACCESSORY_ERROR state", () => {
    const state = addAccessoryReducer(
      {},
      { type: ADD_ACCESSORY_ERROR, payload: "test" }
    );
    expect(state).toEqual({ message: "test", success: false });
  });
  it("should check RESET_ADD_ACCESSORY state", () => {
    const state = addAccessoryReducer(
      {},
      { type: RESET_ADD_ACCESSORY, payload: "test" }
    );
    expect(state).toEqual({ success: null });
  });
});
