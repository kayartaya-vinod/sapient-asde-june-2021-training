import React from "react";
import { render } from "@testing-library/react";
import { Provider } from "react-redux";
import IfLoggedIn from "./IfLoggedIn";
import { Router } from "react-router";
import { createMemoryHistory } from "history";
import configureStore from "redux-mock-store";
import { ROLE_CUSTOMER,ROLE_DEALER } from '../../constants';

describe("IfLoggedIn Test", () => {
  const mockStore = configureStore();
  const history = createMemoryHistory();

  it("should render children elements if loggedIn and role is customer", () => {
    const initialState = {
      authReducer: { isLoggedIn: true, user: { role: ROLE_CUSTOMER } },
    };
    let store = mockStore(initialState);

    const { container } = render(
      <Provider store={store}>
        <Router history={history}>
          <IfLoggedIn role ={ROLE_CUSTOMER}>
            <div>Testing</div>
          </IfLoggedIn>
        </Router>
      </Provider>
    );

    expect(container.firstChild).not.toBeNull();
  });

  it("should render children elements if loggedIn and role is dealer", () => {
    const initialState = {
      authReducer: { isLoggedIn: true, user: { role: ROLE_DEALER } },
    };
    let store = mockStore(initialState);

    const { container } = render(
      <Provider store={store}>
        <Router history={history}>
          <IfLoggedIn role={ROLE_DEALER}>
            <div>Testing</div>
          </IfLoggedIn>
        </Router>
      </Provider>
    );

    expect(container.firstChild).not.toBeNull();
  });


  it("should render nothing if not logged In", () => {
    const initialState = {
      authReducer: { isLoggedIn: false },
    };
    let store = mockStore(initialState);

    const { container } = render(
      <Provider store={store}>
        <Router history={history}>
          <IfLoggedIn>
            <div>Testing</div>
          </IfLoggedIn>
        </Router>
      </Provider>
    );

    expect(container.firstChild).toBeNull();
  });
});
