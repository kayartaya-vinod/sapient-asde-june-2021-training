import React from "react";
import { fireEvent, render, waitFor, cleanup } from "@testing-library/react";
import { Provider } from "react-redux";
import VehicleAccessoryEdit from "./VehicleAccessoryEdit";
import configureStore from "redux-mock-store";
import { BrowserRouter } from "react-router-dom";
import axiosMock from "axios";

jest.mock("axios");

const id = 1;

const resp = {
  _id: "1",
  name: "Steering Wheel",
  description:
    "A steering wheel (also called a driving wheel or a hand wheel) is a type of steering control in vehicles. ... The steering wheel is the part of the steering system that is manipulated by the driver; the rest of the steering system responds to such driver inputs.",
  price: "15000",
  extraInfo: "additionalAttribute",
  extraInfo2: "additionalAttribute",
  pictureUrls: [
    "https://momo.com/en-us/14010-large_default/momo_street-steering-wheel_competition.jpg",
    "https://dmc.ag/wp-content/uploads/2020/07/BMW_Steering_Wheel_DMC_Forged_Carbon.jpg",
    "https://winecountrymotorsports.com/images/momo-prototipo-6c-carbon-fiber-steering-wheel-a.jpg",
    "https://races-shop.com/476481/steering-wheel-races-carbon-350mm-flat.jpg",
  ],
  dealerId: "dealer",
};

describe("VehicleAccessoryEdit Test", () => {
  const origConsole = console.error;

  beforeEach(() => {
    console.error = jest.fn();
  });

  afterEach(() => {
    console.error = origConsole;
    cleanup();
  });
  it("Should render the edit form ", async () => {
    const mockStore = configureStore();

    axiosMock.get.mockResolvedValueOnce({
      data: resp,
    });

    const initialState = {
      vehicleAccessoryReducer: resp,
    };
    let store = mockStore(initialState);
    const { container, getByTestId } = render(
      <Provider store={store}>
        <BrowserRouter>
          <VehicleAccessoryEdit {...id} />
        </BrowserRouter>
      </Provider>
    );
    waitFor(() => {
      expect(getByTestId("editAccessoryHeading")).toBeInTheDocument();
      fireEvent.click(getByTestId("submitBtn"));

      fireEvent.change(getByTestId("input-price"), {
        target: { value: 123 },
      });
      fireEvent.change(getByTestId("input-description"), {
        target: { value: "Testing Description" },
      });
      expect(container).not.toBeNull();
    });
  });

  it("Should show proper error message", async () => {
    const mockStore = configureStore();

    axiosMock.get.mockResolvedValueOnce({
      data: resp,
    });

    axiosMock.put.mockRejectedValue({
      message: "testing",
    });

    const initialState = {
      vehicleAccessoryReducer: resp,
    };
    let store = mockStore(initialState);
    const { getByText, getByTestId } = render(
      <Provider store={store}>
        <BrowserRouter>
          <VehicleAccessoryEdit {...id} />
        </BrowserRouter>
      </Provider>
    );
    await waitFor(() => {
      fireEvent.click(getByTestId("submitBtn"));
      expect(getByText("testing")).toBeInTheDocument();
    });
  });

  it("Should delete additional attribute properly", async () => {
    const mockStore = configureStore();

    axiosMock.get.mockResolvedValueOnce({
      data: resp,
    });

    axiosMock.put.mockRejectedValue({
      message: "testing",
    });

    const initialState = {
      vehicleAccessoryReducer: resp,
    };
    let store = mockStore(initialState);
    const { getByText, getByTestId } = render(
      <Provider store={store}>
        <BrowserRouter>
          <VehicleAccessoryEdit {...id} />
        </BrowserRouter>
      </Provider>
    );
    waitFor(() => {
      const delBtn = getByTestId("deleteBtn-extraInfo");
      expect(delBtn).toBeInTheDocument();
      fireEvent.click(delBtn);
      expect(getByTestId("deleteBtn-extraInfo")).not.toBeInTheDocument();
    });
  });

  it("should render proper error message received", async () => {
    const mockStore = configureStore();

    axiosMock.get.mockResolvedValueOnce({
      data: resp,
    });

    axiosMock.put.mockRejectedValue({
      response: { data: { message: "testing" } },
    });

    const initialState = {
      vehicleAccessoryReducer: resp,
    };
    let store = mockStore(initialState);
    const { container, getByTestId } = render(
      <Provider store={store}>
        <BrowserRouter>
          <VehicleAccessoryEdit {...id} />
        </BrowserRouter>
      </Provider>
    );
    await waitFor(() => {
      fireEvent.click(getByTestId("submitBtn"));
      expect(container).not.toBeNull();
    });
  });

  it("should render success message submit button is clicked", async () => {
    const testfn = (url) => {
      return;
    };
    const mockStore = configureStore();

    axiosMock.get.mockResolvedValueOnce({
      data: resp,
    });

    const initialState = {
      vehicleAccessoryReducer: resp,
    };
    let store = mockStore(initialState);
    const { getByText, getByTestId } = render(
      <Provider store={store}>
        <BrowserRouter>
          <VehicleAccessoryEdit {...id} />
        </BrowserRouter>
      </Provider>
    );
    await waitFor(() => {
      const doneBtn = getByTestId("doneBtn");
      expect(doneBtn).toBeInTheDocument();
      fireEvent.click(doneBtn);
      expect(getByText(/Success/)).toBeInTheDocument();
    });
  });
});
