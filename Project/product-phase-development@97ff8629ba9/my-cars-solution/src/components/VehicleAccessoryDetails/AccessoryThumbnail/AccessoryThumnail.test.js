/**
@Author Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/

import React from "react";
import axiosMock from "axios";
import { renderWithReduxAndRouter } from "../../../utils/testUtils";
import AccessoryThumbnail from "../AccessoryThumbnail/AccessoryThumbnail";
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

describe("AccessoryThumbnail test", () => {
  const mockSetState = jest.fn();
  jest.mock("react", () => ({
    useState: (initial) => [initial, mockSetState],
  }));

  it("Is a test where we want to test useState", async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {
        data: resp,
      },
    });
    renderWithReduxAndRouter(<AccessoryThumbnail />);
    expect(mockSetState).toHaveBeenCalledTimes(0);
  });
});
