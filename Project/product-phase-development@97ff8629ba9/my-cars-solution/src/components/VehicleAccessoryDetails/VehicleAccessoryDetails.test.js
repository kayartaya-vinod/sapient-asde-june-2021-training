/**
@Author Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/
import { cleanup, render } from "@testing-library/react";
import configureStore from "redux-mock-store";
import { Provider } from "react-redux";
import { BrowserRouter } from "react-router-dom";
import VehicleAccessoryDetails from "./VehicleAccessoryDetails";

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

describe("VehicleAccessoryDetails Tests", () => {
  const mockStore = configureStore();
  afterEach(cleanup);
  it("should render the component", () => {
    const initialState = {
      vehicleAccessoryReducer: resp,
    };

    let store = mockStore(initialState);
    const { getByTestId } = render(
      <Provider store={store}>
        <BrowserRouter>
          <VehicleAccessoryDetails />
        </BrowserRouter>
      </Provider>
    );
    expect(getByTestId("row1")).not.toBeNull();
  });
});
