/**
@Author Manvendra Singh
*/
import { fireEvent } from "@testing-library/react";
import { renderWithReduxAndRouter } from "../../utils/testUtils";
import AccessoryCard from "./AccessoryCard"

describe("Card component test", () => {
  it("should render card component", () => {
    const accessory = {
      id: "",
      name:"",
      description: "",
      price: "",
      pictureUrls: ["one", "two"],
    };
    renderWithReduxAndRouter(<AccessoryCard accessory={accessory} />);
  });
  it("should route to accessory detail", () => {
    const accessory = {
      id: "",
      name:"",
      description: "",
      price: "",
      pictureUrls: ["one", "two"],
    };
    const { getByTestId } = renderWithReduxAndRouter(<AccessoryCard accessory={accessory} />);
    fireEvent.click(getByTestId("routing-image"));
    expect(getByTestId("routing-image")).toBeInTheDocument();
  });
});
