/**
 * @author Sumitesh Naithani sumitesh.naithani@publicissapient.com
 */

import AddAccessory from "./AddAccessory";
import { render, fireEvent, waitFor, cleanup } from "@testing-library/react";
import configureStore from "redux-mock-store";
import { Provider } from "react-redux";
import { Router } from "react-router";
import { createMemoryHistory } from "history";
import { BrowserRouter } from "react-router-dom";

describe("Add accessory component test", () => {
  const mockStore = configureStore();
  const history = createMemoryHistory();
  const origConsole = console.error;
  beforeEach(() => {
    console.error = jest.fn();
  });

  afterEach(() => {
    console.error = origConsole;
    cleanup();
  });

  it("should render the react component properly", () => {
    const initialState = {
      addAccessoryReducer: { success: true, message: true },
    };
    let store = mockStore(initialState);
    const { getByText } = render(
      <Provider store={store}>
        <Router history={history}>
          <AddAccessory />
        </Router>
      </Provider>
    );
    expect(getByText(/Add New Accessory/)).toBeInTheDocument();
  });

  it("should render success message on successful submission of accessory", () => {
    const initialState = {
      addAccessoryReducer: { success: true, message: null },
    };
    let store = mockStore(initialState);

    const { getByText } = render(
      <Provider store={store}>
        <Router history={history}>
          <AddAccessory />
        </Router>
      </Provider>
    );
    expect(getByText(/Added Successfully/)).toBeInTheDocument();
    setTimeout(() => {
      expect(getByText(/my car solutions/)).toBeInTheDocument();
    }, 4000);
  });

  it("should render error message on unsuccessfull submission of accessory", () => {
    const initialState = {
      addAccessoryReducer: { success: false, message: "error" },
    };
    let store = mockStore(initialState);

    const { getByText } = render(
      <Provider store={store}>
        <Router history={history}>
          <AddAccessory />
        </Router>
      </Provider>
    );
    expect(getByText(/Error: error/)).toBeInTheDocument();
  });

  it("should render correctly on submission", () => {
    const initialState = {
      addAccessoryReducer: { success: false, message: "error" },
    };
    let store = mockStore(initialState);

    const { getByTestId } = render(
      <Provider store={store}>
        <Router history={history}>
          <AddAccessory />
        </Router>
      </Provider>
    );
    const submitBtn = getByTestId(/submitBtn/);
    expect(submitBtn).toBeInTheDocument();

    const nameInput = getByTestId("field-name");
    const descriptionInput = getByTestId("field-description");
    const priceInput = getByTestId("field-price");
    expect(nameInput).toBeInTheDocument();
    expect(descriptionInput).toBeInTheDocument();
    expect(priceInput).toBeInTheDocument();

    fireEvent.change(nameInput, { target: { value: "testName" } });
    fireEvent.change(descriptionInput, {
      target: { value: "testdescriptionInput" },
    });
    fireEvent.change(priceInput, { target: { value: "123" } });
    fireEvent.click(submitBtn);
  });
  it("Should render picutreUrls correctly", async () => {
    const initialState = {
      addAccessoryReducer: { success: null, message: null },
    };
    let store = mockStore(initialState);
    const { getByText, getByTestId } = render(
      <Provider store={store}>
        <BrowserRouter>
          <AddAccessory />
        </BrowserRouter>
      </Provider>
    );
    await waitFor(() => {
      const doneBtn = getByTestId("doneBtn");
      expect(doneBtn).toBeInTheDocument();

      const firstInputBox = getByTestId("Testinput-0");
      fireEvent.change(firstInputBox, { target: { value: "testing" } });
      fireEvent.blur(firstInputBox);
      fireEvent.click(doneBtn);

      expect(getByText(/Saved/)).toBeInTheDocument();
    });
  });
});
