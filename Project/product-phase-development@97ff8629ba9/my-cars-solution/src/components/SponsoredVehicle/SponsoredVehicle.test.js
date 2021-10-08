import { cleanup, render, screen, fireEvent } from "@testing-library/react";
import { renderWithReduxAndRouter } from "../../utils/testUtils";
import SponsoredVehicle from "./SponsoredVehicle";

describe("Sponsored vehicle tests", () => {
  afterEach(cleanup);
  const vehicles = [
    {
      id: "aksdsdad45da5a1sd2",
      brand: "My car",
      vehicleType: "SUV",
      year: 2001,
      model: "Solution",
      pictureUrls: [
        "https://imgd.aeplcdn.com/0x0/n/cw/ec/27074/civic-exterior-right-front-three-quarter-148155.jpeg",
        "http://dummyimage.com/114x100.png/dddddd/000000",
        "http://dummyimage.com/241x100.png/dddddd/000000",
      ],
      description: "contact us for advertising purposes.",
      color: "Purple",
      price: "$6565.01",
    },
  ];

  it("should render SponsoredVehicle", () => {
    renderWithReduxAndRouter(<SponsoredVehicle />);
    expect(screen.getByText(/ferrari f8/i)).toBeInTheDocument();
  });

  it("should change the content when click on prev or next button", () => {
    const { getByTestId } = renderWithReduxAndRouter(<SponsoredVehicle />);
    fireEvent.click(getByTestId("prev_vehicle"));
    fireEvent.click(getByTestId("next_vehicle"));
  });

  it("should navigate to view details page", () => {
    const { getByTestId } = renderWithReduxAndRouter(
      <SponsoredVehicle />
    );
    fireEvent.click(getByTestId("view_detail"));
  });
});
