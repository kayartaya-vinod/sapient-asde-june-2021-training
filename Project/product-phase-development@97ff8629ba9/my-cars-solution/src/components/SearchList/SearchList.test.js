/**
@Author <Hrishant> <hrishant.raj@publicissapient.com>
*/

import { cleanup, render, screen } from "@testing-library/react";
import SearchList from "./SearchList";
import {
  renderWithRedux,
  renderWithReduxAndRouter,
  renderWithRouter,
} from "../../utils/testUtils";
import { Provider } from "react-redux";
import configureStore from "redux-mock-store";
import { Router } from "react-router";
import { createMemoryHistory } from "history";

describe("SearchList tests", () => {
  const history = createMemoryHistory();
  const mockStore = configureStore();

  afterEach(cleanup);
  const initialState = {
    searchReducer: [
      {
        model: "Huracan",
        brand: "Lamborgini",
        vehicle_type: "Coupe",
        model_year: "2008",
        picturesUrl: ["fhjf"],
      },
    ],
  };

  it("should render SearchList component", () => {
    let store = mockStore(initialState);

    render(
      <Provider store={store}>
        <Router history={history}>
          <SearchList />
        </Router>
      </Provider>
    );
    const el = screen.getByTestId("list");
    expect(el).toBeDefined();
    expect(el.tagName).toBe("UL");
  });

  it("should render SearchList with given cars", () => {
    let store = mockStore(initialState);
    const { getByText } = renderWithReduxAndRouter(
      <Provider store={store}>
        <SearchList />
      </Provider>
    );
    expect(getByText(/huracan/i)).toBeInTheDocument();
  });

  it("should render SearchList component", () => {
    const initialState2 = {
      searchReducer: false,
    };
    let store = mockStore(initialState2);

    render(
      <Provider store={store}>
        <Router history={history}>
          <SearchList />
        </Router>
      </Provider>
    );
    const el = screen.getByTestId("list");
    expect(el).toBeDefined();
    expect(el.tagName).toBe("UL");
  });
});
