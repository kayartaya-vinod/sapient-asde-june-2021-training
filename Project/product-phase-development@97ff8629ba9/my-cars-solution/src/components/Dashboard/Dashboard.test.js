/**
@Author Aditya Gheewala <aditya.gheewala@publicissapient.com>
*/
import { cleanup, fireEvent, render, screen, waitFor } from "@testing-library/react";
import { Provider } from "react-redux";
import { Router } from "react-router";
import Dashboard from "./Dashboard";
import configureStore from "redux-mock-store";
import { createMemoryHistory } from "history";
import { ROLE_CUSTOMER, ROLE_DEALER } from '../../constants';
import axiosMock from "axios";
import { Suspense } from "react";

jest.mock("axios");

describe("Dashboard Test", () => {
  const mockStore = configureStore();
  const history = createMemoryHistory();
  afterEach(cleanup);

  it("should render dashboard for Customer", () => {

    const initialState = {
      authReducer: { isLoggedIn: true, user: { role: ROLE_CUSTOMER } },
    };
    let store = mockStore(initialState);

    render(
      <Provider store={store}>
        <Router history={history}>
          <Dashboard />
        </Router>
      </Provider>
    );
    expect(screen.getByText(/Customer Dashboard/i)).toBeInTheDocument();

    expect(screen.getByText(/My Account/i)).toBeInTheDocument();
    expect(screen.getByText(/Edit Profile/i)).toBeInTheDocument();
    expect(screen.getByText(/Change Password/i)).toBeInTheDocument();
    expect(screen.getByText(/Saved Comparisons/i)).toBeInTheDocument();
    expect(screen.getByText(/Favorite Vehicles/i)).toBeInTheDocument();
  });

  it("should render dashboard for Dealer", () => {

    const initialState = {
      authReducer: { isLoggedIn: true, user: { role: ROLE_DEALER } },
    };
    let store = mockStore(initialState);

    render(
      <Provider store={store}>
        <Router history={history}>
          <Dashboard />
        </Router>
      </Provider>
    );

    expect(screen.getByText(/Dealer Dashboard/i)).toBeInTheDocument();


    expect(screen.getByText(/Change Password/i)).toBeInTheDocument();
    expect(screen.getByText(/Add Vehicles/i)).toBeInTheDocument();
    expect(screen.getByText(/Upload Vehicles/i)).toBeInTheDocument();
  });

  it("should show different options for customer on click", async () => {

    const initialState = {
      authReducer: { isLoggedIn: true, user: { role: ROLE_CUSTOMER } },
      singleCustomerReducer: {
        alternateEmail: "",
        address: {
          default: {
            buildingNo: "",
            street: "",
            landMark: "",
            city: "",
            state: "",
            pinCode: "",
          },
          anotherAddress: {
            buildingNo: "",
            street: "",
            landMark: "",
            city: "",
            state: "",
            pinCode: "",
          },
        },
        contactNo: "",
        alternateContactNo: "",
        wishlist: [],
        isVerified: false,
        userId: "",
      },
      matrixMetadataReducer: { matrix: [], error: "" },
      favoriteVehicleReducer: { vehicles: [], error: null },
    };
    let store = mockStore(initialState);

    render(
      <Provider store={store}>
        <Suspense fallback="loading tests...">
          <Router history={history}>
            <Dashboard />
          </Router>
        </Suspense>
      </Provider>
    );




    await waitFor(() => {

      const myAccount = screen.getByTestId("myAccount");
      const editProfile = screen.getByTestId("editProfile");
      const changePassword = screen.getByTestId("changePassword");
      const savedComparisons = screen.getByTestId("savedComparisons");
      const favoriteVehicles = screen.getByTestId("favoriteVehicles");

      fireEvent.click(myAccount);
      expect(screen.getByText(/your profile/i)).toBeInTheDocument();

      fireEvent.click(editProfile);
      expect(screen.getByText(/update details/i)).toBeInTheDocument();

      fireEvent.click(changePassword);
      expect(screen.getByText(/Old Password/i)).toBeInTheDocument();

      axiosMock.get.mockResolvedValueOnce({ data: { data: [] } });
      fireEvent.click(savedComparisons);

      fireEvent.click(favoriteVehicles);


    });

  });

  it("should show different options for dealer on click", async () => {

    const initialState = {
      authReducer: { isLoggedIn: true, user: { role: ROLE_DEALER } },
      singleCustomerReducer: {
        alternateEmail: "",
        address: {
          default: {
            buildingNo: "",
            street: "",
            landMark: "",
            city: "",
            state: "",
            pinCode: "",
          },
          anotherAddress: {
            buildingNo: "",
            street: "",
            landMark: "",
            city: "",
            state: "",
            pinCode: "",
          },
        },
        contactNo: "",
        alternateContactNo: "",
        wishlist: [],
        isVerified: false,
        userId: "",
      },
      addVehicle: { success: null, message: null },
      addAccessoryReducer: { success: null, message: null},
    };
    let store = mockStore(initialState);
    const historyy = createMemoryHistory();

    render(
      <Provider store={store}>
        <Suspense fallback="loading tests...">
          <Router history={historyy}>
            <Dashboard />
          </Router>
        </Suspense>
      </Provider>
    );


    await waitFor(() => {

      const changePassword = screen.getByTestId("changePassword");
      const addVehicles = screen.getByTestId("addVehicles");
      const uploadVehicles = screen.getByTestId("uploadVehicles");
      const dealerVehicles = screen.getByTestId("dealerVehicles");
      const dealerAccessories = screen.getByTestId("dealerAccessories");
      const feedback = screen.getByTestId("feedback");
      const uploadAccessories = screen.getByTestId("uploadAccessories");
      const uploadSuccess = screen.getByTestId("uploadSuccess");
      const addAccessory = screen.getByTestId("addAccessory");



      fireEvent.click(changePassword);
      expect(screen.getByText(/Old Password/i)).toBeInTheDocument();

      fireEvent.click(addVehicles);
      fireEvent.click(uploadVehicles);
      fireEvent.click(dealerVehicles);
      fireEvent.click(dealerAccessories);
      fireEvent.click(feedback);
      fireEvent.click(uploadAccessories);
      fireEvent.click(uploadSuccess);
      fireEvent.click(addAccessory);

    });


  });


});
