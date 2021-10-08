/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
import { screen, render } from "@testing-library/react";
import CheckboxButtonInput from "./CheckboxButtonInput";

describe("CheckboxButtonInput component test", () => {
  it("should render Input field", () => {
    render(
      <CheckboxButtonInput
        type="checkbox"
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
      <CheckboxButtonInput
        type="checkbox"
        attributeName="testing"
        handleChange={null}
      />
    );
    expect(screen.getAllByTestId("fieldInput")).toHaveLength(1);
  });
});
