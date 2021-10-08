/**
 * @author Sumitesh Naithani sumitesh.naithani@publicissapient.com
 */
import AddAttribute from "./AddAttribute";
import { render, fireEvent, cleanup } from "@testing-library/react";
describe("Add Attributes component tests", () => {
  const origConsole = console.error;
  beforeEach(() => {
    console.error = jest.fn();
  });

  afterEach(() => {
    console.error = origConsole;
    cleanup();
  });

  it("Should render the component", async () => {
    const { getByTestId } = render(<AddAttribute />);
    expect(getByTestId("addBtn")).toBeInTheDocument();
  });

  it("Should click the button", async () => {
    const { getByTestId } = render(<AddAttribute />);
    fireEvent.click(getByTestId("submit-btn"));
    expect(getByTestId("submit-btn")).toBeInTheDocument();
  });

  it("Should check handle change", async () => {
    const { getByTestId } = render(<AddAttribute />);
    fireEvent.change(getByTestId("nameOfAttribute"), {
      target: { value: "attribute" },
    });
    expect(getByTestId("submit-btn")).toBeInTheDocument();
  });

  it("Should check handle change", async () => {
    const { getByTestId } = render(<AddAttribute />);
    fireEvent.change(getByTestId("value"), {
      target: { value: "attribute" },
    });
    fireEvent.click(getByTestId("submit-btn"));
    expect(getByTestId("submit-btn")).toBeInTheDocument();
  });

  it("Should check handle change", async () => {
    const { getByTestId } = render(<AddAttribute />);
    fireEvent.change(getByTestId("value"), {
      target: { value: "attribute" },
    });
    fireEvent.change(getByTestId("nameOfAttribute"), {
      target: { value: "attribute" },
    });
    fireEvent.click(getByTestId("addBtn"));
    fireEvent.click(getByTestId("removeBtn"));
    expect(getByTestId("submit-btn")).toBeInTheDocument();
  });
});
