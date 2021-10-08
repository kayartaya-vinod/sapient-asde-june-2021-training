import {
  GET_SPONSORED_VEHICLE,
  GET_SPONSORED_VEHICLE_ERROR,
} from "../../actionTypes";
import sponsoredVehicleReducer from "./sponsoredVehicleReducer";

describe("Sponsored vehicle reducer test", () => {
  const initialState = [
    {
      id: "aksdsdad45da5a1sd2",
      brand: "My car",
      vehicleType: "SUV",
      year: 2001,
      model: "Solution",
      pictureUrls: [
        "https://imgd.aeplcdn.com/0x0/n/cw/ec/27074/civic-exterior-right-front-three-quarter-148155.jpeg",
        "http://dummyimage.com/114x100.png/dddddd/000000",
        "http://dummyimage.com/241x100.png/dddddd/000000",
      ],
      description: "contact us for advertising purposes.",
      color: "Purple",
      price: "$6565.01",
    },
  ];
  test("should return initial state", () => {
    expect(sponsoredVehicleReducer(initialState, {})).toEqual(initialState);
  });
  test("should return list of vehicle object", () => {
    expect(
      sponsoredVehicleReducer(initialState, {
        type: GET_SPONSORED_VEHICLE,
        payload: initialState,
      })
    ).toEqual(initialState);
  });
});
