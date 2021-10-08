/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
import { screen, render } from "@testing-library/react";
import RadioButtonInput from "./RadioButtonInput";

describe("RadioButtonInput component test", () => {
  it("should render Input field", () => {
    render(
      <RadioButtonInput
        type="radio"
        attributeName="testing"
        options={["a", "b"]}
        handleChange={null}
      />
    );
    expect(screen.getAllByTestId("fieldInput")).toHaveLength(1);
    expect(screen.getAllByTestId("fieldInputOptions")).toHaveLength(2);
    expect(screen.getByText("a")).toBeInTheDocument();
  });

  it("should render with no Option field", () => {
    render(
      <RadioButtonInput
        type="radio"
        attributeName="testing"
        handleChange={null}
      />
    );
    expect(screen.getAllByTestId("fieldInput")).toHaveLength(1);
  });
});
