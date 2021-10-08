import { fireEvent, screen } from "@testing-library/react";
import { renderWithReduxAndRouter } from "../../utils/testUtils";
import AddToCompare from "./AddToCompare";


describe("AddToCompare test",()=>{ 
    it("should render the AddToCompare checkbox",()=>{
        renderWithReduxAndRouter(<AddToCompare vehicleId={"id1"}/>);
        const checkBox = screen.getByTestId("addToCompareid1");
        fireEvent.click(checkBox);
        expect(checkBox.checked).toEqual(true);
        fireEvent.click(checkBox);
        expect(checkBox.checked).toEqual(false); 
    });
    it("should not add more than 4 AddToCompare checkbox", () => {
      renderWithReduxAndRouter(<AddToCompare vehicleId={"id1"} />);
      const checkBox = screen.getByTestId("addToCompareid1");
      fireEvent.click(checkBox);
      renderWithReduxAndRouter(<AddToCompare vehicleId={"id2"} />);
      const checkBox2 = screen.getByTestId("addToCompareid2");
      fireEvent.click(checkBox2);
      renderWithReduxAndRouter(<AddToCompare vehicleId={"id3"} />);
      const checkBox3 = screen.getByTestId("addToCompareid3");
      fireEvent.click(checkBox3);
      renderWithReduxAndRouter(<AddToCompare vehicleId={"id4"} />);
      const checkBox4 = screen.getByTestId("addToCompareid4");
      fireEvent.click(checkBox4);
      renderWithReduxAndRouter(<AddToCompare vehicleId={"id5"} />);
      const checkBox5 = screen.getByTestId("addToCompareid5");
      fireEvent.click(checkBox5);
       expect(checkBox5.checked).toEqual(false);  
    });
});  