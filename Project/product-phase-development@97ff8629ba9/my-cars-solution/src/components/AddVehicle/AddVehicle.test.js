/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
import { render, fireEvent } from "@testing-library/react";
import AddVehicle from "./AddVehicle";
import configureStore from "redux-mock-store";
import { Provider } from "react-redux";
import { Router } from "react-router";
import { createMemoryHistory } from "history";

describe("AddVehicle component test", () => {
  const mockStore = configureStore();
  const history = createMemoryHistory();

  it("should render page properly", () => {
    const initialState = {
      addVehicle: { success: null, message: null },
    };
    let store = mockStore(initialState);

    const { getByText } = render(
      <Provider store={store}>
        <Router history={history}>
          <AddVehicle />
        </Router>
      </Provider>
    );
    expect(getByText(/Add New Vehicle/)).toBeInTheDocument();
  });

  it("should render success message on successful submission", () => {
    const initialState = {
      addVehicle: { success: true, message: null },
    };
    let store = mockStore(initialState);

    const { getByText } = render(
      <Provider store={store}>
        <Router history={history}>
          <AddVehicle />
        </Router>
      </Provider>
    );
    expect(
      getByText(
        /Added Successfully, You will be redirected to dashboard shortly/
      )
    ).toBeInTheDocument();
    setTimeout(() => {
      expect(getByText(/my car solutions/)).toBeInTheDocument();
    }, 4000);
  });

  it("should render error message on un-successfull submission", () => {
    const initialState = {
      addVehicle: { success: false, message: "error" },
    };
    let store = mockStore(initialState);

    const { getByText } = render(
      <Provider store={store}>
        <Router history={history}>
          <AddVehicle />
        </Router>
      </Provider>
    );
    expect(getByText(/Error: error/)).toBeInTheDocument();
  });

  it("should render correctly on submission", () => {
    const initialState = {
      addVehicle: { success: false, message: "error" },
    };
    let store = mockStore(initialState);

    const { getByTestId } = render(
      <Provider store={store}>
        <Router history={history}>
          <AddVehicle />
        </Router>
      </Provider>
    );
    const submitBtn = getByTestId(/submitBtn/);
    expect(submitBtn).toBeInTheDocument();

    const brandInput = getByTestId("field-brand");
    const descriptionInput = getByTestId("field-description");
    const colorInput = getByTestId("field-color");
    const modelInput = getByTestId("field-model");
    const yearInput = getByTestId("field-year");
    const fuelTypeInput0 = getByTestId("field-fuelType0");
    const pictureUrlsInput0 = getByTestId("Testinput-0");
    const doneBtn = getByTestId("doneBtn");
    expect(brandInput).toBeInTheDocument();
    expect(descriptionInput).toBeInTheDocument();
    expect(colorInput).toBeInTheDocument();
    expect(modelInput).toBeInTheDocument();
    expect(yearInput).toBeInTheDocument();
    expect(fuelTypeInput0).toBeInTheDocument();
    expect(pictureUrlsInput0).toBeInTheDocument();
    expect(doneBtn).toBeInTheDocument();

    fireEvent.change(brandInput, { target: { value: "testBrand" } });
    fireEvent.change(descriptionInput, {
      target: { value: "testdescriptionInput" },
    });
    fireEvent.change(colorInput, { target: { value: "testcolorInput" } });
    fireEvent.change(modelInput, { target: { value: "testmodelInput" } });
    fireEvent.change(yearInput, { target: { value: "1234" } });
    fireEvent.change(pictureUrlsInput0, { target: { value: "testPicUrl" } });
    fireEvent.blur(pictureUrlsInput0);
    fireEvent.click(doneBtn);
    fireEvent.click(fuelTypeInput0);
    fireEvent.click(fuelTypeInput0);

    fireEvent.click(submitBtn);
  });
});
