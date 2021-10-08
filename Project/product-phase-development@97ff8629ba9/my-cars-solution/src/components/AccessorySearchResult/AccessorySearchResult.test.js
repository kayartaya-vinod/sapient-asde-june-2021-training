/**
 @Author Aditya Gheewala aditya.gheewala@publicissapient.com
 */

import { fireEvent, render, screen, waitFor } from "@testing-library/react";
import { Provider } from "react-redux";
import { Router } from "react-router";
import { createMemoryHistory } from "history";
import configureStore from "redux-mock-store";
import AccessorySearchResult from "./AccessorySearchResult";
import { accessoryMockData } from "./MockData";


jest.mock("axios");

describe("AccessorySearchResult tests", () => {
  const mockStore = configureStore();
  const history = createMemoryHistory();

  const mockData = accessoryMockData;

  it("should render AccessorySearchResult", async () => {

    const initialState = {
      searchReducer: { vehicles: [], accessories: mockData, error: null },
      vehicleCompareListReducer: [[], []],
    };

    let store = mockStore(initialState);

    await waitFor(() => {
      render(
        <Provider store={store}>
          <Router history={history}>
            <AccessorySearchResult />
          </Router>
        </Provider>
      );
    });
    expect(screen.getByText(/Search results for/i)).toBeInTheDocument();
    fireEvent.click(screen.getByText(/Load more/i));
  });

  it("should render error message", async () => {
    const initialState = {
      searchReducer: { vehicles: [], accessories: [], error: null },
    };

    let store = mockStore(initialState);

    await waitFor(() => {
      render(
        <Provider store={store}>
          <Router history={history}>
            <AccessorySearchResult />
          </Router>
        </Provider>
      );
    });


    expect(screen.getByText(/No Accessories found!/i)).toBeInTheDocument();
  });

});
