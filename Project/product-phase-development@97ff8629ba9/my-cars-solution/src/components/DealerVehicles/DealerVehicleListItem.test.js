/**
@author Abhishek Singh abhishek.singh8@publicisssapient.com
*/

import { screen, cleanup, fireEvent, render } from "@testing-library/react";
import { createMemoryHistory } from "history";
import configureStore from "redux-mock-store";
import { Provider } from "react-redux";
import { Router } from "react-router";
import thunk from 'redux-thunk';

import DealerVehicleListItem from './DealerVehicleListItem';

describe('DealerVehicleListItem', () => {
  const middlewares = [thunk];
  const mockStore = configureStore(middlewares);


  afterEach(cleanup);

  it("should render Dealer Vehicle List Item", () => {
    const history = createMemoryHistory();
    const initialState = {
      dealerVehicleReducer: {
        isFirst: true,
        isLast: false,
        vehicles: [],
        selectedVehicles: [],
        filteredVehicles: [],
      }
    };

    const vehicle = {
      "id": "61121ad598f95c2cba086f6",
      "pictureUrls": [
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2019-honda-civic-sedan-1558453497.jpg?crop=1xw:0.9997727789138833xh;center,top&resize=480:*",
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2019-honda-civic-sedan-1558453497.jpg?crop=1xw:0.9997727789138833xh;center,top&resize=480:*"
      ],
      "description": "This is a car",
      "price": 918231.0,
      "brand": "BMW",
      "model": "r100",
      "vehicleType": "deluxe",
    };

    let store = mockStore(initialState);

    render(
      <Provider store={store}>
        <Router history={history}>
          <DealerVehicleListItem vehicle={vehicle} />
        </Router>
      </Provider>
    );

    const checkbox = screen.getByTestId("checkbox");
    const vehicleImg = screen.getByTestId("vehicle-img");

    expect(checkbox).toBeInTheDocument();
    expect(vehicleImg).toBeInTheDocument();
  });

  it("should check Dealer Vehicle List Item", () => {
    const history = createMemoryHistory();
    const initialState = {
      dealerVehicleReducer: {
        isFirst: true,
        isLast: false,
        vehicles: [],
        selectedVehicles: [],
        filteredVehicles: [],
      }
    };

    const vehicle = {
      "id": "61121ad598f95c2cba086f6",
      "pictureUrls": [
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2019-honda-civic-sedan-1558453497.jpg?crop=1xw:0.9997727789138833xh;center,top&resize=480:*",
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2019-honda-civic-sedan-1558453497.jpg?crop=1xw:0.9997727789138833xh;center,top&resize=480:*"
      ],
      "description": "This is a car",
      "price": 918231.0,
      "brand": "BMW",
      "model": "r100",
      "vehicleType": "deluxe",
    };

    let store = mockStore(initialState);

    render(
      <Provider store={store}>
        <Router history={history}>
          <DealerVehicleListItem vehicle={vehicle} />
        </Router>
      </Provider>
    );

    const checkbox = screen.getByTestId("checkbox");
    fireEvent.click(checkbox);
    expect(checkbox).toBeChecked();
    fireEvent.click(checkbox);
    expect(checkbox).not.toBeChecked();
  });


});
