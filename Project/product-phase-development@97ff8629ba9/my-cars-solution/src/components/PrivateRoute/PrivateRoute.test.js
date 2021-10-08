import React from "react";
import { render } from "@testing-library/react";
import { Provider } from "react-redux";
import PrivateRoute from "./PrivateRoute";
import { Router } from "react-router";
import { createMemoryHistory } from "history";
import configureStore from "redux-mock-store";
import { ROLE_CUSTOMER } from '../../constants';

describe("PrivateRoute Test", () => {
  const mockStore = configureStore();
  const history = createMemoryHistory();

  it("should return Route if logged In", () => {
    const initialState = {
      authReducer: { isLoggedIn: true, user: { role: ROLE_CUSTOMER } },
    };
    let store = mockStore(initialState);

    const { getByTestId } = render(
      <Provider store={store}>
        <Router history={history}>
          <PrivateRoute />
        </Router>
      </Provider>
    );
    expect(getByTestId("private-route")).toBeInTheDocument();
  });

  it("should redirect if not logged In", () => {
    const initialState = {
      authReducer: { isLoggedIn: false },
    };
    let store = mockStore(initialState);

    const { getByTestId } = render(
      <Provider store={store}>
        <Router history={history}>
          <PrivateRoute />
        </Router>
      </Provider>
    );
    expect(getByTestId("redirect-route")).toBeInTheDocument();
  });


 

});
