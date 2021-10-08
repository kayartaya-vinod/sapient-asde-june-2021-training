/**
@author Abhishek Singh abhishek.singh8@publicisssapient.com
*/

import { screen, cleanup, fireEvent, render } from "@testing-library/react";
import { createMemoryHistory } from "history";
import configureStore from "redux-mock-store";
import { Provider } from "react-redux";
import { Router } from "react-router";
import thunk from 'redux-thunk';

import axiosMock from "axios";

import DealerVehicleHeader from './DealerVehicleHeader';


jest.mock("axios");

describe('DealerVehicleHeader', () => {
  const middlewares = [thunk];
  const mockStore = configureStore(middlewares);


  afterEach(cleanup);

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


  it("should render Dealer Vehicle Header", () => {
    const history = createMemoryHistory();
    const initialState = {
      dealerVehicleReducer: {
        isFirst: false,
        isLast: false,
        vehicles: [],
        selectedVehicles: ["61121ad598f95c2cba086f6"],
        filteredVehicles: [],
      }
    };


    let store = mockStore(initialState);

    render(
      <Provider store={store}>
        <Router history={history}>
          <DealerVehicleHeader vehicles={[vehicle]} vehicleIds={[vehicle.id]} />
        </Router>
      </Provider>
    );

    const checkbox = screen.getByTestId("checkbox");
    const downloadBtn = screen.getByTestId("download-btn");
    const deleteBtn = screen.getByTestId("delete-btn");
    const searchBox = screen.getByTestId("search-box");

    expect(checkbox).toBeInTheDocument();
    expect(deleteBtn).toBeInTheDocument();
    expect(downloadBtn).toBeInTheDocument();
    expect(searchBox).toBeInTheDocument();
  });

  it("should check the checkbox in the header", () => {
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


    let store = mockStore(initialState);

    render(
      <Provider store={store}>
        <Router history={history}>
          <DealerVehicleHeader vehicles={[vehicle]} vehicleIds={[vehicle.id]} />
        </Router>
      </Provider>
    );

    const searchBox = screen.getByTestId("search-box");
    fireEvent.change(searchBox, {
      target: {
        value: "bmw"
      }
    });

    expect(searchBox).toHaveValue("bmw");

    fireEvent.change(searchBox, {
      target: {
        value: ""
      }
    });

    expect(searchBox).toHaveValue("");

  });


  it("should change the search bar in the header", () => {
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


    let store = mockStore(initialState);

    render(
      <Provider store={store}>
        <Router history={history}>
          <DealerVehicleHeader vehicles={[vehicle]} vehicleIds={[vehicle.id]} />
        </Router>
      </Provider>
    );

    const checkbox = screen.getByTestId("checkbox");
    fireEvent.click(checkbox);
    expect(checkbox).toBeChecked();
    fireEvent.click(checkbox);
    expect(checkbox).not.toBeChecked();
  });

  it("should download", () => {

    axiosMock.get.mockResolvedValueOnce();

    const history = createMemoryHistory();
    const initialState = {
      dealerVehicleReducer: {
        isFirst: true,
        isLast: false,
        vehicles: [],
        selectedVehicles: ["61121ad598f95c2cba086f6"],
        filteredVehicles: [],
      }
    };


    let store = mockStore(initialState);

    render(
      <Provider store={store}>
        <Router history={history}>
          <DealerVehicleHeader vehicles={[vehicle]} vehicleIds={[vehicle.id]} />
        </Router>
      </Provider>
    );

    const downloadBtn = screen.getByTestId("download-btn");

    fireEvent.click(downloadBtn);
  });


  it("should delete", () => {

    axiosMock.delete.mockResolvedValueOnce();

    const history = createMemoryHistory();
    const initialState = {
      dealerVehicleReducer: {
        isFirst: true,
        isLast: false,
        vehicles: [],
        selectedVehicles: ["61121ad598f95c2cba086f6"],
        filteredVehicles: [],
      }
    };


    let store = mockStore(initialState);

    render(
      <Provider store={store}>
        <Router history={history}>
          <DealerVehicleHeader vehicles={[vehicle]} vehicleIds={[vehicle.id]} />
        </Router>
      </Provider>
    );

    const deleteBtn = screen.getByTestId("delete-btn");

    fireEvent.click(deleteBtn);
  });


});
