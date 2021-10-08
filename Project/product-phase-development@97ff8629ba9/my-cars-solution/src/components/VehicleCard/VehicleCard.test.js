/**
@Author Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/
import { fireEvent } from "@testing-library/react";
import { renderWithReduxAndRouter } from "../../utils/testUtils";
import Card from "./VehicleCard";

describe("Card component test", () => {
  it("should render card component", () => {
    const vehicle = {
      id: "",
      description: "",
      price: "",
      brand: "",
      model: "",
      year: "",
      pictureUrls: ["one", "two"],
    };
    renderWithReduxAndRouter(<Card vehicle={vehicle} />);
  });
  it("should route to vehicle detail", () => {
    const vehicle = {
      id: "",
      description: "",
      price: "",
      brand: "",
      model: "",
      year: "",
      pictureUrls: ["one", "two"],
    };
    const { getByTestId } = renderWithReduxAndRouter(<Card vehicle={vehicle} />);
    fireEvent.click(getByTestId("routing-image"));
    expect(getByTestId("routing-image")).toBeInTheDocument();
  });
});
