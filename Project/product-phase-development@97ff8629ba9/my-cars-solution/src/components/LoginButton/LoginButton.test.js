import {screen } from "@testing-library/react";
import { renderWithRouter } from "../../utils/testUtils";
import LoginButton from "./LoginButton";

describe("Login Button test", () => {
  
    it("should render Login button", () => {
      renderWithRouter(<LoginButton />);
      expect(screen.getByText(/login/i)).toBeInTheDocument();
    });
  
  });