import { cleanup, fireEvent, render, screen } from "@testing-library/react";
import { createMemoryHistory } from "history";
import { Provider } from "react-redux";
import { Router } from "react-router";
import configureStore from "redux-mock-store";
import thunk from "redux-thunk";
import VehicleSearchAttribute from "./VehicleSearchAttribute";

jest.mock("axios");
jest.setTimeout(30000);
describe("VehicleSearchAttribute test", () => {
  const middlewares = [thunk];
  const mockStore = configureStore(middlewares);
  afterEach(cleanup);
  const vehicleFilter = [
    {
      attribute: "brands",
      displayName: "Search By Brands",
      componentType: "check",
      query: "brand={}",
      fields: [
        {
          field: "Maruti",
          displayName: "Maruti",
        },
        {
          field: "Hyundai",
          displayName: "Hyundai",
        },
        {
          field: "Honda",
          displayName: "Honda",
        },
        {
          field: "Tata",
          displayName: "Tata",
        },
        {
          field: "Mahindra",
          displayName: "Mahindra",
        },
        {
          field: "BMW",
          displayName: "BMW",
        },
        {
          field: "AUDI",
          displayName: "AUDI",
        },
        {
          field: "Ferrari",
          displayName: "Ferrari",
        },
      ],
    },
    {
      attribute: "priceRanges",
      displayName: "Search By Price",
      componentType: "radio",
      query: "min_price={}&max_price={}",
      fields: [
        {
          displayName: "Any",
          field: ["", ""],
        },
        {
          displayName: "1-5 Lakh",
          field: [100000, 500000],
        },
        {
          displayName: "5-10 Lakh",
          field: [500000, 1000000],
        },
        {
          displayName: "10-15 Lakh",
          field: [1000000, 1500000],
        },
        {
          displayName: "15-25 Lakh",
          field: [1500000, 2500000],
        },
        {
          displayName: "25-35 Lakh",
          field: [2500000, 3500000],
        },
        {
          displayName: "35-50 Lakh",
          field: [3500000, 5000000],
        },
        {
          displayName: "50 Lakh-1 Crore",
          field: [5000000, 10000000],
        },
        {
          displayName: "Above 1 Crore",
          field: [10000000, 10000000000],
        },
      ],
    },
    {
      attribute: "vehicleType",
      displayName: "Search By Vehicle Type",
      componentType: "check",
      query: "vehicle_type={}",
      fields: [
        {
          displayName: "HatchBack",
          field: "HatchBack",
        },
        {
          displayName: "Mini",
          field: "mini",
        },
        {
          displayName: "SUV",
          field: "SUV",
        },
        {
          displayName: "MUV",
          field: "MUV",
        },
        {
          displayName: "Convertible",
          field: "Convertible",
        },
        {
          displayName: "Deluxe",
          field: "deluxe",
        },
        {
          displayName: "Electric",
          field: "Electric",
        },
        {
          displayName: "Macro",
          field: "macro",
        },
      ],
    },
    {
      attribute: "fuelType",
      displayName: "Search By Fuel Type",
      componentType: "check",
      query: "fuel_type={}",
      fields: [
        {
          displayName: "Petrol",
          field: "Petrol",
        },
        {
          displayName: "Disel",
          field: "Disel",
        },
        {
          displayName: "CNG",
          field: "CNG",
        },
      ],
    },
    {
      attribute: "vehicleColor",
      displayName: "Search By Vehicle Color",
      componentType: "check",
      query: "color={}",
      fields: [
        {
          displayName: "Red",
          field: "red",
        },
        {
          displayName: "Blue",
          field: "blue",
        },
        {
          displayName: "Green",
          field: "green",
        },
        {
          displayName: "Yellow",
          field: "yellow",
        },
        {
          displayName: "Grey",
          field: "grey",
        },
        {
          displayName: "Black",
          field: "black",
        },
      ],
    },
    {
      attribute: "airBagCount",
      displayName: "Search By Airbag Count",
      componentType: "check",
      query: "air_bag={}",
      fields: [
        {
          displayName: "2",
          field: 2,
        },
        {
          displayName: "4",
          field: 4,
        },
        {
          displayName: "6",
          field: 6,
        },
        {
          displayName: "8",
          field: 8,
        },
      ],
    },
    {
      attribute: "afterYear",
      displayName: "Released After Year",
      componentType: "radio",
      query: "year={}",
      fields: [
        {
          displayName: "Any",
          field: [""],
        },
        {
          displayName: "2000",
          field: [2000],
        },
        {
          displayName: "2010",
          field: [2010],
        },
        {
          displayName: "2013",
          field: [2013],
        },
        {
          displayName: "2015",
          field: [2015],
        },
        {
          displayName: "2017",
          field: [2017],
        },
        {
          displayName: "2020",
          field: [2020],
        },
      ],
    },
  ];
  it("should do check the check boxes", () => {
    const history = createMemoryHistory();
    const initialState = {
      vehicleFilterAttributeReducer: vehicleFilter,
    };
    let store = mockStore(initialState);
    render(
      <Provider store={store}>
        <Router history={history}>
          <VehicleSearchAttribute searchAttribute={vehicleFilter[0]} />
        </Router>
      </Provider>
    );
  });
  it("should do check the radio buttons", () => {
    const history = createMemoryHistory();
    const initialState = {
      vehicleFilterAttributeReducer: vehicleFilter,
    };
    let store = mockStore(initialState);
    render(
      <Provider store={store}>
        <Router history={history}>
          <VehicleSearchAttribute searchAttribute={vehicleFilter[1]} />
        </Router>
      </Provider>
    );
  });

  it("should handle click on checkboxes", () => {
    const history = createMemoryHistory();
    const initialState = {
      vehicleFilterAttributeReducer: vehicleFilter,
    };
    let store = mockStore(initialState);
    render(
      <Provider store={store}>
        <Router history={history}>
          <VehicleSearchAttribute searchAttribute={vehicleFilter[0]} />
        </Router>
      </Provider>
    );
    const checkbox1 = screen.getByTestId("Maruti");
    const checkbox2 = screen.getByTestId("Tata");
    expect(checkbox1).toBeInTheDocument();
    expect(checkbox2).toBeInTheDocument();
    fireEvent.click(checkbox1);
    fireEvent.click(checkbox2);

    expect(checkbox1).toBeChecked();
    expect(checkbox2).toBeChecked();
    fireEvent.click(checkbox1);
    fireEvent.click(checkbox2);
    expect(checkbox1).not.toBeChecked();
    expect(checkbox2).not.toBeChecked();
  });
  it("should handle click on radio", () => {
    const history = createMemoryHistory();
    const initialState = {
      vehicleFilterAttributeReducer: vehicleFilter,
    };
    let store = mockStore(initialState);
    render(
      <Provider store={store}>
        <Router history={history}>
          <VehicleSearchAttribute searchAttribute={vehicleFilter[6]} />
        </Router>
      </Provider>
    );
    const radio = screen.getByTestId("2000");
    expect(radio).toBeInTheDocument();
    fireEvent.click(radio);
  });
    it("should handle click on checkbox", async () => {
      const history = createMemoryHistory();
      const initialState = {
        vehicleFilterAttributeReducer: vehicleFilter,
      };
      let store = mockStore(initialState);
      render(
        <Provider store={store}>
          <Router history={history}>
            <VehicleSearchAttribute searchAttribute={vehicleFilter[0]} />
          </Router>
        </Provider>
      );
      const checkbox1 = screen.getByTestId("Maruti");
      
      const checkbox2 = screen.getByTestId("Tata");
      
      expect(checkbox1).toBeInTheDocument();
      
      expect(checkbox2).toBeInTheDocument();
      fireEvent.click(checkbox1);

      await new Promise((r) => setTimeout(r, 2000));
      
      fireEvent.click(checkbox2);
      
      await new Promise((r) => setTimeout(r, 2000));

      expect(checkbox1).toBeChecked();
      expect(checkbox2).toBeChecked();
 
    });
 
});
