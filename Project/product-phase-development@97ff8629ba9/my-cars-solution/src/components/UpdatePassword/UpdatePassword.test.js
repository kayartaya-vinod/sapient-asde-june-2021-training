import UpdatePassword from "./UpdatePassword";
import { fireEvent, render, screen } from "@testing-library/react";
import { Provider } from "react-redux";
import { Router } from "react-router";
 
import { createMemoryHistory } from "history";
import configureStore from "redux-mock-store";
describe("Update Password Tests", () => {
  const mockStore = configureStore();
  const history = createMemoryHistory();
  const origConsole = console.error;
    beforeEach(() => {
      console.error = jest.fn();
    });
    afterEach(() => {
      console.error = origConsole;
    });
    
  it("should render error message if token is not valid", () => {
    const initialState = {
      checkEmailReducer: { isValidEmail: false },
      updatePasswordReducer: { isPasswordUpdated: false },
    };
    let store = mockStore(initialState);
 
    const { container } = render(
      <Provider store={store}>
        <Router history={history}>
          <UpdatePassword />
        </Router>
      </Provider>
    );
    expect(container.firstChild).not.toBeNull();
  });
 
  it("should render Form to reset password if token is valid", async () => {
    const initialState = {
      checkEmailReducer: { isValidEmail: true },
      updatePasswordReducer: { isPasswordUpdated: false },
    };
    let store = mockStore(initialState);
 
    const { container, findByText } = render(
      <Provider store={store}>
        <Router history={history}>
          <UpdatePassword />
        </Router>
      </Provider>
    );
    expect(container.firstChild).not.toBeNull();
    expect(await findByText(/Reset password/i)).toBeInTheDocument();
  });
 
  it("should change input value on change", async () => {
    const initialState = {
      checkEmailReducer: { isValidEmail: true },
      updatePasswordReducer: { isPasswordUpdated: false , action: {}},
    };
    let store = mockStore(initialState);
 
    const { findByTestId } = render(
      <Provider store={store}>
        <Router history={history}>
          <UpdatePassword />
        </Router>
      </Provider>
    );
 
    const inputField1 = findByTestId("password-test");
    const inputField2 = findByTestId("confirm-password-test");
    fireEvent.change(await inputField1, {
      target: { value: "NewPassword123@#" },
    });
    fireEvent.change(await inputField2, {
      target: { value: "NewPassword123@#" },
    });
    expect(await inputField1).toHaveValue("NewPassword123@#");
    expect(await inputField2).toHaveValue("NewPassword123@#");
 
    const submitBtn = findByTestId("change-password-test");
    fireEvent.click(await submitBtn);
    expect(await screen.findByText(/Change Password/i)).toBeInTheDocument();
  });

  it("should change input value on change", async () => {
    const initialState = {
      checkEmailReducer: { isValidEmail: true },
      updatePasswordReducer: { isPasswordUpdated: false , action: {}},
    };
    let store = mockStore(initialState);
 
    const { findByTestId } = render(
      <Provider store={store}>
        <Router history={history}>
          <UpdatePassword />
        </Router>
      </Provider>
    );
 
    const inputField1 = findByTestId("password-test");
    const inputField2 = findByTestId("confirm-password-test");
    fireEvent.change(await inputField1, {
      target: { value: "NewPassword123@#" },
    });
    fireEvent.change(await inputField2, {
      target: { value: "NewPassword123@" },
    });
    expect(await inputField1).toHaveValue("NewPassword123@#");
    expect(await inputField2).toHaveValue("NewPassword123@");
 
    const submitBtn = findByTestId("change-password-test");
    fireEvent.click(await submitBtn);
    expect(await screen.findByText(/Passwords do not match/i)).toBeInTheDocument();
  });
});