/**
@author Abhishek Singh abhishek.singh8@publicisssapient.com
*/

import { screen, cleanup, render } from "@testing-library/react";
import { createMemoryHistory } from "history";
import configureStore from "redux-mock-store";
import { Provider } from "react-redux";
import { Router } from "react-router";
import thunk from "redux-thunk";

import axiosMock from "axios";

import DealerVehicles from "./DealerVehicles";

jest.mock("axios");

describe("DealerVehicles", () => {
  const middlewares = [thunk];
  const mockStore = configureStore(middlewares);

  afterEach(cleanup);

  const vehicle = {
    id: "61121ad598f95c2cba086f6",
    pictureUrls: [
      "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2019-honda-civic-sedan-1558453497.jpg?crop=1xw:0.9997727789138833xh;center,top&resize=480:*",
      "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2019-honda-civic-sedan-1558453497.jpg?crop=1xw:0.9997727789138833xh;center,top&resize=480:*",
    ],
    description: "This is a car",
    price: 918231.0,
    brand: "BMW",
    model: "r100",
    vehicleType: "deluxe",
  };

  it("should render Dealer Vehicle Header", () => {
    axiosMock.get.mockResolvedValueOnce();

    const history = createMemoryHistory();
    const initialState = {
      dealerVehicleReducer: {
        isFirst: false,
        isLast: false,
        vehicles: [vehicle],
        selectedVehicles: [],
        filteredVehicles: [vehicle],
      },
    };

    let store = mockStore(initialState);

    render(
      <Provider store={store}>
        <Router history={history}>
          <DealerVehicles />
        </Router>
      </Provider>
    );

    const searchBox = screen.getByTestId("search-box");
    expect(searchBox).toBeInTheDocument();

    const vehicleImg = screen.getByTestId("vehicle-img");
    expect(vehicleImg).toBeInTheDocument();

    const previousBtn = screen.getByTestId("previous-btn");
    const nextBtn = screen.getByTestId("next-btn");
    expect(previousBtn).toBeInTheDocument();
    expect(nextBtn).toBeInTheDocument();
  });
});
