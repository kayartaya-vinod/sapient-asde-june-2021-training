/**
@author Abhishek Singh abhishek.singh8@publicisssapient.com
*/

import { cleanup, fireEvent, render, screen } from "@testing-library/react";
import { createMemoryHistory } from "history";
import { Router } from "react-router";
import PreviousNext from "./PreviousNext";


describe("DealerAccessoriesPagination", () => {


  afterEach(cleanup);

  it("should render Dealer Accessories Pagination", () => {
    const history = createMemoryHistory();

    render(
      <Router history={history}>
        <PreviousNext isFirst={false} isLast={false} page={2} pathname="/" />
      </Router>
    );

    const previousBtn = screen.getByTestId("previous-btn");
    const nextBtn = screen.getByTestId("next-btn");

    expect(previousBtn).toBeInTheDocument();
    expect(nextBtn).toBeInTheDocument();
  });

  it("should click previous and next button", () => {
    const history = createMemoryHistory();
    history.push("/?page=2");


    render(
      <Router history={history}>
        <PreviousNext isFirst={false} isLast={false} page={"2"} pathname="/" />
      </Router>
    );

    const previousBtn = screen.getByTestId("previous-btn");
    const nextBtn = screen.getByTestId("next-btn");

    fireEvent.click(previousBtn);
    fireEvent.click(nextBtn);
  });
});
