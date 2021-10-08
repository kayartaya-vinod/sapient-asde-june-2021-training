/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
import { screen, render } from "@testing-library/react";
import TextAndNumberInput from "./TextAndNumberInput";

describe("TextAndNumberInput component test", () => {
  it("should render Input field", () => {
    render(<TextAndNumberInput type="text" attributeName="testing" />);
    expect(screen.getByTestId(/fieldInput/i)).toBeInTheDocument();
  });
});
