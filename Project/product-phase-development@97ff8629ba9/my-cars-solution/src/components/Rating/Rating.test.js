/**
@Author Jaswant Arya - jaswant.arya@publicissapient.com
*/

import {
  cleanup,
  fireEvent,
  waitFor,
  screen,
} from "@testing-library/react";
import { renderWithReduxAndRouter } from "../../utils/testUtils";
import Rating from "./Rating";
import axiosMock from "axios";
import { act } from "react-dom/test-utils";

jest.mock("axios");

describe("CustomerProfile Test", () => {
  afterEach(cleanup);
  const vehicleId = 1;
 
  it("should render Rating component", async () => {
    renderWithReduxAndRouter(<Rating {...vehicleId} />);

    await waitFor(() => {
      expect(screen.getAllByText(/Rating/i)).toBeInTheDocument;
     
    });
  });

  it("should click the star", async () => {
    const { getByTestId } = renderWithReduxAndRouter(<Rating {...vehicleId} />);

    fireEvent.click(getByTestId("star1"));
    expect(screen.getAllByText(/Rating/i)).toBeInTheDocument;
  });

  it("should not submit", async () => {
    const { getByTestId } = renderWithReduxAndRouter(<Rating {...vehicleId} />);

    fireEvent.click(getByTestId("rating-btn"));
    expect(screen.getAllByText(/oops/i)).toBeInTheDocument;
  });

  it("should submit", async () => {
    const { getByTestId } = renderWithReduxAndRouter(<Rating {...vehicleId} />);
    
      axiosMock.post.mockResolvedValueOnce({
        data: {
          success: true,
        },
      });

    fireEvent.click(getByTestId("star1"));


    act(() => {
      fireEvent.click(getByTestId("rating-btn"));
    });

    await waitFor(() => {
      expect(screen.getAllByText(/Your/i)).toBeInTheDocument;
    });

   

  });

  it("should test hover", async () => {
    const { getByTestId } = renderWithReduxAndRouter(<Rating {...vehicleId} />);

    fireEvent.mouseOver(getByTestId("star1"));
    expect(screen.getAllByText(/Rating/i)).toBeInTheDocument;
  });


  
});
