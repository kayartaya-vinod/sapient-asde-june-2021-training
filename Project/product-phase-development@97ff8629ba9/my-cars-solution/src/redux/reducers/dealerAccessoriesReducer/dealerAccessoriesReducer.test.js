/**
@author Abhishek Singh abhishek.singh8@publicisssapient.com
*/

import {
  DELETE_ACCESSORY,
  GET_DEALER_VEHICLE_ACCESSORIES,
  RESET_DEALER_VEHICLE_ACCESSORIES_STATE,
  SELECT_DEALER_ACCESSORY,
} from "../../actionTypes";

import dealerAccessoriesReducer from "./dealerAccessoriesReducer";

const initialState = {
  isFirst: true,
  isLast: true,
  accessories: [],
  idToDelete: "",
};

describe("dealerAccessoriesReducer tests", () => {
  it("should return the initial state", () => {
    const state = dealerAccessoriesReducer(initialState, {});
    expect(state).toEqual(initialState);
    expect(dealerAccessoriesReducer(undefined, {})).toEqual(initialState);
  });

  it(`should change the state on ${GET_DEALER_VEHICLE_ACCESSORIES}`, () => {
    const content = ["accessory"];
    const first = true;
    const last = true;
    const action = {
      type: GET_DEALER_VEHICLE_ACCESSORIES,
      payload: {
        content,
        first,
        last,
      },
    };
    const state = dealerAccessoriesReducer(initialState, action);

    expect(state).toEqual({
      ...initialState,
      accessories: content,
      isFirst: first,
      isLast: last,
    });
  });

  it(`should change the state on ${RESET_DEALER_VEHICLE_ACCESSORIES_STATE}`, () => {
    const action = {
      type: RESET_DEALER_VEHICLE_ACCESSORIES_STATE,
    };
    const state = dealerAccessoriesReducer(initialState, action);

    expect(state).toEqual({ ...initialState });
  });

  it(`should change the state on ${SELECT_DEALER_ACCESSORY}`, () => {
    const action = {
      type: SELECT_DEALER_ACCESSORY,
      payload: "1",
    };
    const state = dealerAccessoriesReducer(initialState, action);

    expect(state).toEqual({ ...initialState, idToDelete: "1" });
  });

  it(`should change the state on ${DELETE_ACCESSORY}`, () => {
    const action = {
      type: DELETE_ACCESSORY,
      payload: "1",
    };

    const initialState = {
      isFirst: true,
      isLast: true,
      accessories: [
        {
          id: "1",
          name: "Seat Cover",
          description:
            "3D Custom PU Leather Car Seat Covers For Hyundai Creta (2015-2019) - (HT-505 Mojo",
          pictureUrls: [
            "https://www.autofurnish.com/content/images/thumbs/0191223_3d-custom-pu-leather-car-seat-covers-ht-505-mojo_375.jpeg",
          ],
          price: "6565",
        },
      ],
      idToDelete: "",
    };

    const state = dealerAccessoriesReducer(initialState, action);

    expect(state).toEqual({
      isFirst: true,
      isLast: true,
      accessories: [],
      idToDelete: "",
    });
  });
});
