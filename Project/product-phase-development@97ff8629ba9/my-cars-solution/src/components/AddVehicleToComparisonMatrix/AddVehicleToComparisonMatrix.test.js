import {
    cleanup, fireEvent, render, screen
  } from "@testing-library/react";
  
  import { renderWithRedux } from "../../utils/testUtils";
  import AddVehicleToComparisonMatrix from "./AddVehicleToComparisonMatrix";
  
  import axiosMock from "axios"
var mockDispatch = jest.fn();

  jest.mock("axios");

  describe("Add Vehicle to comparison", () => {
    afterEach(cleanup);
    const origConsole = console.error;
    beforeEach(() => {
      console.error = jest.fn();
    });
    afterEach(() => {
      console.error = origConsole;
    });
  
    it('should render component', async () => {

        renderWithRedux(<AddVehicleToComparisonMatrix/>);
       
        fireEvent.click(screen.getByTestId('addbutton'));
        const el = screen.getByText(/Select Brand/);
        expect(el).toBeInTheDocument()
    });
   

    });
  