import { fireEvent, cleanup, render } from "@testing-library/react";
import FilteredList from "./FilteredList";
import { renderWithReduxAndRouter } from "../../utils/testUtils";
import configureStore from "redux-mock-store";
import { Provider } from "react-redux";
import { BrowserRouter } from "react-router-dom";

jest.mock("axios");

describe("FilteredList Test", () => {
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

  it("should render FilteredList", () => {
    const { container } = renderWithReduxAndRouter(<FilteredList />);
    expect(container).not.toBeNull();
  });
  it("should test filter list conatiner", () => {
    const initialState = {
      fetchFilteredDataReducer: {
        vehicles: vehicles,
        totalPages: 1,
        start: false,
      },
      vehicleCompareListReducer: [["1", "2"], []],
      singleVehicleReducer: { vehicle: vehicle },
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
          <FilteredList />
        </BrowserRouter>
      </Provider>
    );
    expect(getByTestId("filtered-list-container")).not.toBeNull();
  });
  it("should render load more conatiner", async () => {
    const initialState = {
      fetchFilteredDataReducer: {
        vehicles: vehicles,
        totalPages: 1,
        start: false,
      },
      vehicleCompareListReducer: [["1", "2"], []],
      singleVehicleReducer: { vehicle: vehicle },
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
          <FilteredList />
        </BrowserRouter>
      </Provider>
    );
    expect(getByTestId("load-more")).not.toBeNull();
    fireEvent.click(getByTestId("load-btn"));
  });
});
