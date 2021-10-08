import { fireEvent, screen } from "@testing-library/react";
import { renderWithReduxAndRouter } from "../../utils/testUtils";
import AddToCompare from "./AddToCompare";
import CompareButton from "./CompareButton";


describe("CompareButton test", () => {
  xit("should render the CompareButton but not on screen", () => {
     renderWithReduxAndRouter(<AddToCompare vehicleId={"id1"} />);
     const checkBox = screen.getByTestId("addToCompareid1");
     fireEvent.click(checkBox);
    renderWithReduxAndRouter(<CompareButton/>); 

    const compareButton = screen.getByTestId("compare");
    fireEvent.click(compareButton);
    expect(screen.getByTestId("compare")).not.toBeNull();
  });
  
});
