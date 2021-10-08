import { render, screen } from "@testing-library/react";
import HeaderMenu from "./HeaderMenu";

describe("Header Menu Test", () => {
  it("should have a Dropdown", () => {
    render(
      <HeaderMenu text="Dropdown">
      </HeaderMenu>
    );
    const element = screen.getByTestId("navbarDropdown");
    expect(element).toBeInTheDocument();
    expect(element.textContent).toBe("Dropdown");
  });
});
