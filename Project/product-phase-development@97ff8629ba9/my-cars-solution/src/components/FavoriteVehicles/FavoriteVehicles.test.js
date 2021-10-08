/**
@Author Aravind M Krishnan - aravind.m.krishnan@publicissapient.com 
*/
import { screen, cleanup, waitFor } from "@testing-library/react";
import axiosMock from "axios";
import { renderWithReduxAndRouter } from "../../utils/testUtils";
import FavoriteVehicles from "./FavoriteVehicles";
jest.mock("axios");

describe("Favorite Vehicle tests", () => {
  const vehicle = {
    _id: "",
    description: "",
    price: "",
    brand: "",
    model: "",
    year: "",
    pictureUrls: ["one", "two"],
  };
  const vehicles = [vehicle, vehicle];
  afterEach(cleanup);

  it("should render favorite vehicle component", () => {
    axiosMock.get.mockResolvedValueOnce({
      data: { vehicles: vehicles },
    });
    const { container } = renderWithReduxAndRouter(<FavoriteVehicles />);
    expect(container).not.toBeNull();
  });

  xit("should show no favorite vehicles", async () => {
    axiosMock.get.mockResolvedValueOnce({ data: { data: [] } });

    renderWithReduxAndRouter(<FavoriteVehicles />);

    await waitFor(() => {
      expect(screen.getAllByText(/No favorite vehicles./i)).toBeInTheDocument;
    });
  });
});
