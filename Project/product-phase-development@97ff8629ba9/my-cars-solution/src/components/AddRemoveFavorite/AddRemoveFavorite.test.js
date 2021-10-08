import { fireEvent, screen, cleanup, waitFor } from "@testing-library/react";
import { renderWithReduxAndRouter } from "../../utils/testUtils";
import AddRemoveFavorite from "./AddRemoveFavorite";
import axiosMock from "axios";

jest.mock("axios");

describe("AddRemoveFavoriteTest", () => {
  afterEach(cleanup);
  const vehicle = {
    model: "Huracan",
    brand: "Lamborgini",
    vehicle_type: "Coupe",
    price: "23000000",
  };
  it("should render favourite indicator", () => {
    renderWithReduxAndRouter(<AddRemoveFavorite vehicle={vehicle} />);
    expect(screen.getByTestId(/hearticon/i)).toBeInTheDocument();
  });

  it("should click the heart and colour must change", async () => {
    renderWithReduxAndRouter(<AddRemoveFavorite vehicle={vehicle} />);
    const color = (element) => window.getComputedStyle(element).color;
    axiosMock.put.mockResolvedValueOnce({
      data: {
        success: true,
      },
    });
    fireEvent.click(screen.getByTestId("heartIcon"));
    await waitFor(() => {
      expect(color(screen.getByTestId(/hearticon/i))).toBe("rgb(255, 0, 0)");
    });
  });
  it("should click the heart and give toast error", async () => {
    renderWithReduxAndRouter(<AddRemoveFavorite vehicle={vehicle} />);

    axiosMock.put.mockResolvedValueOnce({
      data: {
        success: false,
      },
    });
    fireEvent.click(screen.getByTestId("heartIcon"));
    await waitFor(() => {
      expect(
        screen.getByText(/Something went wrong! Try later/i)
      ).toBeInTheDocument();
    });
  });
});
