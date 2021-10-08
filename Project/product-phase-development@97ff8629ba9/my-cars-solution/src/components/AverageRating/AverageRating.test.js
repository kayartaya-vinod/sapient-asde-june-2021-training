/**
@Author Manvendra Singh manvendra.singh@publicissapient.com
*/

import {
    cleanup,
    waitFor,
    screen,
    act
  } from "@testing-library/react";
  import { renderWithReduxAndRouter } from "../../utils/testUtils";
  import axiosMock from "axios";
 
import AverageRating from "./AverageRating";
  
  jest.mock("axios");
  
  describe("Average Rating Test", () => {
    afterEach(cleanup);
    const vehicleId = 1;
    const origConsole = console.error;
    beforeEach(() => {
      console.error = jest.fn();
    });
    afterEach(() => {
      console.error = origConsole;
    });
    it("should render Average Rating component", () => {
      act(() => {
        axiosMock.get.mockResolvedValueOnce({
          data: {  success: true,averageRating: 4.5,totalCustomer:200 },
        });
       renderWithReduxAndRouter(<AverageRating {...vehicleId} />);
       
      });
      expect(screen.getAllByText(/Rating/i)).toBeInTheDocument;
    });
    it("should render Average Rating component for no data ", () => {
      act(() => {
        axiosMock.get.mockResolvedValueOnce({
          data: {  success: false },
        });
       renderWithReduxAndRouter(<AverageRating {...vehicleId} />);
       
      });
      expect(screen.getAllByText(/No Ratings Yet/i)).toBeInTheDocument;
    });

    });
  