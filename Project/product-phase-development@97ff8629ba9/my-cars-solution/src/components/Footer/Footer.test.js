import { render, screen, cleanup } from "@testing-library/react";
import Footer from "./Footer";

describe("Footer test", () => {
  afterEach(cleanup);

  it("should render Footer", () => {
    render(<Footer />);
    expect(screen.getByText(/my car solutions/i)).toBeInTheDocument();
  });
});
