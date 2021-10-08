import {  cleanup } from "@testing-library/react";
import AccessoryGrid from "./AccessoryGrid";
import { renderWithReduxAndRouter } from "../../utils/testUtils";
import configureStore from "redux-mock-store";


describe("Accessory tests", () => {
  const accessory = {
    _id: "adfdaa",
    name:"Seat Cover",
    description: "Nice seat cover",
    price: "1000",
    pictureUrls: ["one", "two"],
  };
  const accessories = [
    accessory,
    accessory,
    accessory,
    accessory,
    accessory,
    accessory,
    accessory,
    accessory,
    accessory,
    accessory,
    accessory,
    accessory,
  ];
  afterEach(cleanup);
  it("should render accessory component", () => {
    const { container } = renderWithReduxAndRouter(<AccessoryGrid accessories={accessories}/>);
    expect(container).not.toBeNull();
  });
});