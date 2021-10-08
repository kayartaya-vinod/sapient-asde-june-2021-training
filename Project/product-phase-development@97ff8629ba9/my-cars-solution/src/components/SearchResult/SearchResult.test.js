/**
 @Author Hrishant Raj hrishant.raj@publicissapient.com 
 */

import { renderWithReduxAndRouter } from "../../utils/testUtils";
import axiosMock from "axios";
import SearchResult from "./SearchResult";
import { render } from "@testing-library/react";
import { Provider } from "react-redux";
import { Router } from "react-router";
import { createMemoryHistory } from "history";
import configureStore from "redux-mock-store";
import {MemoryRouter} from "react-router-dom";

jest.mock("axios");

describe("SearchResult tests", () => {
  const mockStore = configureStore();
  const history = createMemoryHistory();
  it("should render SearchResult", () => {
    axiosMock.get.mockResolvedValueOnce({
      data: ["abc"],
    });
    const { container } = renderWithReduxAndRouter(<SearchResult />);
    expect(container).not.toBeNull();
  });

  it("should render error message", () => {
    const initialState = {
      searchReducer: [],
    };
    let store = mockStore(initialState);
    axiosMock.get.mockResolvedValueOnce({
      data: [],
    });
    const {getByTestId} = render(
      <Provider store={store}>
        <Router history={history}>
          <SearchResult />
        </Router>
      </Provider>
    );
    expect(getByTestId("pagewait")).toBeInTheDocument();
  });

  it("should render SearchResult component", () => {
    const initialState = {
      searchReducer: [],
    };
    let store = mockStore(initialState);
    axiosMock.get.mockResolvedValueOnce({
      data: ["a","b","c"],
    });
    const {getByTestId} = render(
      <Provider store={store}>
        <MemoryRouter initialEntries={["/search?q=Honda"]}>
          <SearchResult />
        </MemoryRouter>
      </Provider>
    );
    expect(getByTestId("pagewait")).toBeInTheDocument();
  });
});
