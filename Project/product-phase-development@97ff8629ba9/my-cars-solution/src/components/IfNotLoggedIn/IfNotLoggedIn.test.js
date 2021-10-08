import React from "react";
import { render } from "@testing-library/react";
import { Provider } from "react-redux";
import IfNotLoggedIn from "./IfNotLoggedIn";
import { Router } from "react-router";
import { createMemoryHistory } from "history";
import configureStore from "redux-mock-store";

describe("IfNotLoggedIn Test", () => {
  const mockStore = configureStore();
  const history = createMemoryHistory();

  it("should render children elements if not loggedIn", () => {
    const initialState = {
      authReducer: { isLoggedIn: false },
    };
    let store = mockStore(initialState);

    const { container } = render(
      <Provider store={store}>
        <Router history={history}>
          <IfNotLoggedIn>
            <div>Test</div>
          </IfNotLoggedIn>
        </Router>
      </Provider>
    );

    expect(container).not.toBeNull();
  });

  it("should render nothing if logged In", () => {
    const initialState = {
      authReducer: { isLoggedIn: true },
    };
    let store = mockStore(initialState);

    const { container } = render(
      <Provider store={store}>
        <Router history={history}>
          <IfNotLoggedIn>
            <div>Test</div>
          </IfNotLoggedIn>
        </Router>
      </Provider>
    );

    expect(container.firstChild).toBeNull();
  });
});
