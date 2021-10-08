import axiosMock from "axios";
import {
  GET_SPONSORED_VEHICLE,
  GET_SPONSORED_VEHICLE_ERROR,
} from "../../actionTypes";
import { getSponsoredVehicles } from "./SponsoredVehicleActionCreator";

jest.mock("axios");

describe("sponsoredVehicleActionCreator tests",()=>{
    it(`should return action types : ${GET_SPONSORED_VEHICLE}`, async () => {
        const vehicles=[
            {
              id: 1,
              brand: "Daewoo",
              vehicleType: "SUV",
              year: 2001,
              model: "Nubira",
              pictureUrls: [
                "http://dummyimage.com/241x100.png/dddddd/000000",
                "http://dummyimage.com/114x100.png/dddddd/000000",
                "http://dummyimage.com/241x100.png/dddddd/000000",
              ],
              color: "Purple",
              price: "$6565.01",
            },
            {
              id: 1,
              brand: "Daewoo",
              vehicleType: "SUV",
              year: 2001,
              model: "Nubira",
              pictureUrls: [
                "http://dummyimage.com/241x100.png/dddddd/000000",
                "http://dummyimage.com/114x100.png/dddddd/000000",
                "http://dummyimage.com/241x100.png/dddddd/000000",
              ],
              color: "Purple",
              price: "$6565.01",
            },
          ]
        axiosMock.get.mockResolvedValueOnce({
          data: {data:vehicles},
        });

        expect(await getSponsoredVehicles()).toEqual({
            type: GET_SPONSORED_VEHICLE,
            payload: vehicles,
          });
    });
    it(`should return action type: ${GET_SPONSORED_VEHICLE_ERROR} on error`, async () => {
        axiosMock.get.mockRejectedValue(new Error("Error"));
    
        expect(await getSponsoredVehicles()).toEqual({
          type: GET_SPONSORED_VEHICLE_ERROR,
          payload: "Error",
        });
      });
})