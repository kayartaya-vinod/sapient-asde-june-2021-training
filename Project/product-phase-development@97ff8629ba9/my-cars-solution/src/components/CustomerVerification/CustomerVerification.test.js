/**
@Author Rohit Bhatt rohit.bhatt1@publicissapient.com
*/

import { render, waitFor } from "@testing-library/react";
import CustomerVerification from "./CustomerVerification";
import { createMemoryHistory } from "history";
import configureStore from "redux-mock-store";
import { Provider } from "react-redux";
import { Router } from "react-router";
import axiosMock from "axios";

jest.mock("axios");
describe("Testing customer verification page", () => {
  const history = createMemoryHistory();
  const mockStore = configureStore();
 
  it("Should show link expired message ", async () => {
      axiosMock.post.mockResolvedValueOnce({
        data: {
          success: false,
        },
      });

      const initialState = {
        authReducer: { isLoggedIn: false },
      };
      let store = mockStore(initialState);
    const { container, getByTestId } = render(
      <Provider store={store}>
        <Router history={history}>
          <CustomerVerification />
        </Router>
      </Provider>
    );
    await waitFor(() => {
      expect(container.firstChild).not.toBeNull();
      expect(getByTestId("expired-message")).toHaveTextContent(
        /This links seems to be expired or invalid, please try with a new link!/i
      );
    });
  });
  it("Should show success message", async () => {
    axiosMock.post.mockResolvedValueOnce({
      data: {
        success: true,
        name: "Rohit Bhatt",
      },
    });

    const initialState = {
      authReducer: { isLoggedIn: false },
    };
    let store = mockStore(initialState);

    const { getByTestId } = render(
      <Provider store={store}>
        <Router history={history}>
          <CustomerVerification />
        </Router>
      </Provider>
    );
    await waitFor(() => {
      expect(getByTestId("success-message")).toHaveTextContent(
        /your account has been verified! Login to your account/i
      );
    });
  });
});
