/**
@Author Sumitesh Naithani sumitesh.naithani@publicissapient.com
@Author Shubham Chaudhary shubham.chaudhary@publicissapient.com
*/
import React from "react";
import { render } from "@testing-library/react";
import ScrollToTop from "./ScrollToTop";
import { BrowserRouter } from "react-router-dom";

describe("Scroll to top Test", () => {
  it("should render children elements", () => {
    const { container } = render(
      <BrowserRouter>
        <ScrollToTop>
          <div>Testing</div>
        </ScrollToTop>
      </BrowserRouter>
    );

    expect(container.firstChild).not.toBeNull();
  });
});
