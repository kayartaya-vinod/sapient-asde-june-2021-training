/**
@Author Rohit Bhatt rohit.bhatt1@publicissapient.com
*/

import { render } from "@testing-library/react";
import { createMemoryHistory } from "history";
import { Router } from "react-router";
import PleaseWait from "./PleaseWait";

describe("test PleaseWait", () => {
  const history = createMemoryHistory();
  it("Should show pplease wait on screen", () => {
    const { container } = render(
      <Router history={history}>
        <PleaseWait />
      </Router>
    );
    expect(container).not.toBeNull();
  });
});
