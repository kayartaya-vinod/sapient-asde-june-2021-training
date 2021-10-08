/**
@Author Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/
import { fireEvent, cleanup, render } from "@testing-library/react";
import axiosMock from "axios";
import { renderWithReduxAndRouter } from "../../utils/testUtils";
import Vehicles from "./Vehicles";
import configureStore from "redux-mock-store";
import { Provider } from "react-redux";
import { BrowserRouter } from "react-router-dom";

jest.mock("axios");

describe("Vehicle tests", () => {
  const vehicle = {
    _id: "",
    description: "",
    price: "",
    brand: "",
    model: "",
    year: "",
    pictureUrls: ["one", "two"],
  };
  const vehicles = [
    vehicle,
    vehicle,
    vehicle,
    vehicle,
    vehicle,
    vehicle,
    vehicle,
    vehicle,
    vehicle,
    vehicle,
    vehicle,
    vehicle,
  ];
  const mockStore = configureStore();
  afterEach(cleanup);
  it("should render vehicle component", () => {
    axiosMock.get.mockResolvedValueOnce({
      data: { vehicles: vehicles, totalPages: 5 },
    });
    const { container } = renderWithReduxAndRouter(<Vehicles />);
    expect(container).not.toBeNull();
  });

  it("should click load more button", async () => {
    const initialState = {
      vehiclesReducer: { vehicles: vehicles, error: null, totalPages: 2 },
      singleVehicleReducer: { vehicle: vehicle },
      vehicleCompareListReducer: [["1", "2"], []],
      authReducer: {
        isLoggedIn: false,
        token: null,
        user: { id: "", name: "", email: "", role: "" },
      },
    };
    let store = mockStore(initialState);
    const { getByTestId } = render(
      <Provider store={store}>
        <BrowserRouter>
          <Vehicles />
        </BrowserRouter>
      </Provider>
    );
    expect(getByTestId("load-more")).not.toBeNull();
      fireEvent.click(getByTestId("load-more-btn"));
    expect(getByTestId("load-more").firstChild).toBeNull();
  });
  it("should check load more button", async () => {
    const initialState = {
      vehiclesReducer: { vehicles: [], error: null, totalPages: 1 },
    };

    let store = mockStore(initialState);
    const { getByTestId } = render(
      <Provider store={store}>
        <BrowserRouter>
          <Vehicles />
        </BrowserRouter>
      </Provider>
    );
    expect(getByTestId("load-more").firstChild).toBeNull();
  });
  it("should check error", async () => {
    const initialState = {
      vehiclesReducer: { vehicles: [], error: "error", totalPages: 1 },
    };

    let store = mockStore(initialState);
    const { getByTestId } = render(
      <Provider store={store}>
        <BrowserRouter>
          <Vehicles />
        </BrowserRouter>
      </Provider>
    );
    expect(getByTestId("error")).toBeInTheDocument();
  });
});
