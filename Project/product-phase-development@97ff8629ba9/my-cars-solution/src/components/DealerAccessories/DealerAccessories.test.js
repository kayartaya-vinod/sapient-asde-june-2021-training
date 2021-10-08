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

import DealerAccessories from "./DealerAccessories";

jest.mock("axios");

describe("DealerAccessories", () => {
  const middlewares = [thunk];
  const mockStore = configureStore(middlewares);

  afterEach(cleanup);

  const accessory = {
    id: "61121ad598f95c2cba086f6",
    pictureUrls: [
      "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2019-honda-civic-sedan-1558453497.jpg?crop=1xw:0.9997727789138833xh;center,top&resize=480:*",
      "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2019-honda-civic-sedan-1558453497.jpg?crop=1xw:0.9997727789138833xh;center,top&resize=480:*",
    ],
    description: "This is an accessory",
    price: 918231.0,
    name: "BMW"
  };

  it("should render Dealer Accessories", () => {
    axiosMock.get.mockResolvedValueOnce();

    const history = createMemoryHistory();
    const initialState = {
      dealerAccessoriesReducer: {
        isFirst: false,
        isLast: false,
        accessories: [accessory]
      },
    };

    let store = mockStore(initialState);

    render(
      <Provider store={store}>
        <Router history={history}>
          <DealerAccessories />
        </Router>
      </Provider>
    );

    const accessoryDetails = screen.getByTestId("accessory-details");
    const accessoryImg = screen.getByTestId("accessory-img");
    const accessoryLink = screen.getAllByTestId("accessory-link");
    const accessoryEditLink = screen.getAllByTestId("accessory-edit-link");

    accessoryLink.forEach(al => al.click());
    accessoryEditLink.forEach(al => al.click());

    expect(accessoryDetails).toBeInTheDocument();
    expect(accessoryImg).toBeInTheDocument();

    const previousBtn = screen.getByTestId("previous-btn");
    const nextBtn = screen.getByTestId("next-btn");
    expect(previousBtn).toBeInTheDocument();
    expect(nextBtn).toBeInTheDocument();
  });
});
