/**
@Author Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/

import axiosMock from "axios";
import {
  GET_VEHICLE_ACCESSORY,
  GET_VEHICLE_ACCESSORY_ERROR,
} from "../../actionTypes";
import { fetchVehicleAccessoryById } from "./vehicleAccessoryActionCreator";

jest.mock("axios");

const resp = {
  _id: "1",
  name: "Steering Wheel",
  description:
    "A steering wheel (also called a driving wheel or a hand wheel) is a type of steering control in vehicles. ... The steering wheel is the part of the steering system that is manipulated by the driver; the rest of the steering system responds to such driver inputs.",
  price: "15000",
  pictureUrls: [
    "https://momo.com/en-us/14010-large_default/momo_street-steering-wheel_competition.jpg",
    "https://dmc.ag/wp-content/uploads/2020/07/BMW_Steering_Wheel_DMC_Forged_Carbon.jpg",
    "https://winecountrymotorsports.com/images/momo-prototipo-6c-carbon-fiber-steering-wheel-a.jpg",
    "https://races-shop.com/476481/steering-wheel-races-carbon-350mm-flat.jpg",
  ],
  dealerId: "dealer",
};
describe("vehicleAccessoryActionCreator Tests", () => {
  it(`should return action type: ${GET_VEHICLE_ACCESSORY}`, async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: { data: resp },
    });
    expect(await fetchVehicleAccessoryById(1)).toEqual({
      type: GET_VEHICLE_ACCESSORY,
      payload: resp,
    });
  });
  it(`should return action type: ${GET_VEHICLE_ACCESSORY_ERROR}`, async () => {
    axiosMock.get.mockRejectedValue(new Error("Error"));
    expect(await fetchVehicleAccessoryById(1)).toEqual({
      type: GET_VEHICLE_ACCESSORY_ERROR,
      payload: "Error",
    });
  });
});
