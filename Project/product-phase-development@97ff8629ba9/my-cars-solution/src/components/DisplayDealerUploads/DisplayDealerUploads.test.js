/**
@Author Sakshi Yadav sakshi.yadav@publicissapient.com
*/
import DisplayDealerUploads from "./DisplayDealerUploads";
import { screen, waitFor, act } from "@testing-library/react";
import React from "react";
import axiosMock from "axios";
import { renderWithReduxAndRouter } from "../../utils/testUtils";

jest.mock("axios");
describe("Display dealer uploads", () => {
  const dealerId = "611df9590872066a152dad73";
  const origConsole = console.error;
  beforeEach(() => {
    console.error = jest.fn();
  });
  afterEach(() => {
    console.error = origConsole;
  });

  it("Is a test should render display dealer uploads component", async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {
        data: [
          {
            id: "611c8decbc3e3424e4ffc97c",
            dealerId: "611df9590872066a152dad73",
            fileName: "Honda cars",
            dateAndTime: "2021-08-18 10:30:00",
            noOfVehicles: 50,
            successRatio: 95.0,
          },
        ],
        success: true,
      },
    });

    renderWithReduxAndRouter(<DisplayDealerUploads {...dealerId} />);

    await waitFor(() => {
      expect(screen.getByTestId("S")).toBeInTheDocument;
      expect(screen.getByTestId("File")).toBeInTheDocument;
      expect(screen.getByText(/File Name/i)).toBeInTheDocument;
      expect(screen.getByText(/Date-Time/i)).toBeInTheDocument;
    });
  });

  it("Is a test display files yet to be uploaded ", async () => {
    act(() => {
      axiosMock.get.mockResolvedValueOnce({
        data: {
          data: [],
          success: true,
        },
      });
    });
    renderWithReduxAndRouter(<DisplayDealerUploads {...dealerId} />);

    await waitFor(() => {
      expect(screen.getByTestId("msg")).toBeInTheDocument;
    });
  });

  it("Is a test should display error message", async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {
        success: false,
        message: "Error",
      },
    });

    renderWithReduxAndRouter(<DisplayDealerUploads {...dealerId} />);

    await waitFor(() => {
      expect(screen.getByTestId("msg")).toBeInTheDocument;
    });
  });
});
