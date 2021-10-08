import { render, screen } from "@testing-library/react";
import { Router } from "react-router";
import MenuItem from "./MenuItem";
import { createMemoryHistory } from "history";

describe("Menu Item Test", () => {
  it("should have a Link", () => {
    const history = createMemoryHistory();
    render(
      <Router history={history}>
        <MenuItem text="Link" linkTo="" />
      </Router>
    );
    const element = screen.getByRole("link", { name: "Link" });
    expect(element).toBeInTheDocument();
    expect(element.textContent).toBe("Link");
  });
});
