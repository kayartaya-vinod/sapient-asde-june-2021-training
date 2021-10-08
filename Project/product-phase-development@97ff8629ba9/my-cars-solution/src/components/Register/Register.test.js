import { cleanup, fireEvent, screen, waitFor } from "@testing-library/react";
import { renderWithRouter } from "../../utils/testUtils";
import Register from "./Register";

const mockDispatch = jest.fn();
jest.mock("axios");

window.alert = jest.fn();

describe("Register tests", () => {
  afterEach(cleanup);
  it("should render Create Account", () => {
    renderWithRouter(<Register />);
    const el = screen.getByText(/Create Account/i);
    expect(el).toBeInTheDocument();
    expect(el.tagName).toBe("H2");
  });
  it("should give firstname is required error", () => {
    renderWithRouter(<Register />);
    fireEvent.change(screen.getByTestId("lastname"), {
      target: { value: "Doe" },
    });
    fireEvent.change(screen.getByTestId("email"), {
      target: { value: "john@doe.com" },
    });
    fireEvent.change(screen.getByTestId("password"), {
      target: { value: "Welcome#123" },
    });
    fireEvent.change(screen.getByTestId("confirmpassword"), {
      target: { value: "Welcome#123" },
    });
    fireEvent.click(screen.getByTestId("btn-submit"));
    waitFor(() => {
      const el = screen.getByText(/First name is required/i);
      expect(el).toBeInTheDocument();
    });
  });
  it("should give passwords do not match  error", () => {
    renderWithRouter(<Register />);
    fireEvent.change(screen.getByTestId("firstname"), {
      target: { value: "John" },
    });
    fireEvent.change(screen.getByTestId("lastname"), {
      target: { value: "Doe" },
    });
    fireEvent.change(screen.getByTestId("email"), {
      target: { value: "john@doe.com" },
    });
    fireEvent.change(screen.getByTestId("password"), {
      target: { value: "Welcome#123" },
    });
    fireEvent.change(screen.getByTestId("confirmpassword"), {
      target: { value: "Welcome#12" },
    });
    fireEvent.click(screen.getByTestId("btn-submit"));
    waitFor(() => {
      const el = screen.getByText(/Passwords do not match/i);
      expect(el).toBeInTheDocument();
    });
  });
  it("should give Email required error", () => {
    renderWithRouter(<Register />);
    fireEvent.change(screen.getByTestId("firstname"), {
      target: { value: "John" },
    });
    fireEvent.change(screen.getByTestId("lastname"), {
      target: { value: "Doe" },
    });
    fireEvent.change(screen.getByTestId("password"), {
      target: { value: "Welcome#123" },
    });
    fireEvent.change(screen.getByTestId("confirmpassword"), {
      target: { value: "Welcome#123" },
    });
    fireEvent.click(screen.getByTestId("btn-submit"));
    waitFor(() => {
      const el = screen.getByText(/Email required/i);
      expect(el).toBeInTheDocument();
    });
  });
  it("should give password Field is required error", () => {
    renderWithRouter(<Register />);
    fireEvent.change(screen.getByTestId("firstname"), {
      target: { value: "John" },
    });
    fireEvent.change(screen.getByTestId("lastname"), {
      target: { value: "Doe" },
    });
    fireEvent.change(screen.getByTestId("email"), {
      target: { value: "john@doe.com" },
    });
    fireEvent.change(screen.getByTestId("confirmpassword"), {
      target: { value: "Welcome#123" },
    });
    fireEvent.click(screen.getByTestId("btn-submit"));
    waitFor(() => {
      const el = screen.getByText(/Field is required/i);
      expect(el).toBeInTheDocument();
    });
  });
  it("should give password Field is required error", () => {
    renderWithRouter(<Register />);
    fireEvent.change(screen.getByTestId("firstname"), {
      target: { value: "John" },
    });
    fireEvent.change(screen.getByTestId("lastname"), {
      target: { value: "Doe" },
    });
    fireEvent.change(screen.getByTestId("email"), {
      target: { value: "john@doe.com" },
    });
    fireEvent.change(screen.getByTestId("password"), {
      target: { value: "Welcome#123" },
    });
    fireEvent.click(screen.getByTestId("btn-submit"));
    waitFor(() => {
      const el = screen.getByText(/Field is required/i);
      expect(el).toBeInTheDocument();
    });
  });
  it("should give invalid email error", () => {
    renderWithRouter(<Register />);
    fireEvent.change(screen.getByTestId("firstname"), {
      target: { value: "John" },
    });
    fireEvent.change(screen.getByTestId("lastname"), {
      target: { value: "Doe" },
    });
    fireEvent.change(screen.getByTestId("email"), {
      target: { value: "john@doecom" },
    });
    fireEvent.change(screen.getByTestId("password"), {
      target: { value: "Welcome#123" },
    });
    fireEvent.change(screen.getByTestId("confirmpassword"), {
      target: { value: "Welcome#123" },
    });
    fireEvent.click(screen.getByTestId("btn-submit"));
    waitFor(() => {
      const el = screen.getByText(/Email address is invalid/i);
      expect(el).toBeInTheDocument();
    });
  });

  it("should give user type filled must", () => {
    renderWithRouter(<Register />);
    fireEvent.change(screen.getByTestId("firstname"), {
      target: { value: "John" },
    });
    fireEvent.change(screen.getByTestId("lastname"), {
      target: { value: "Doe" },
    });
    fireEvent.change(screen.getByTestId("email"), {
      target: { value: "john@doecom" },
    });

    fireEvent.change(screen.getByTestId("userType"), {
      target: { value: "D" },
    });
    

    fireEvent.change(screen.getByTestId("password"), {
      target: { value: "Welcome#123" },
    });
    fireEvent.change(screen.getByTestId("confirmpassword"), {
      target: { value: "Welcome#123" },
    });
    fireEvent.click(screen.getByTestId("btn-submit"));
    waitFor(() => {
      const el = screen.getByText(/Usertype is required/i);
      expect(el).toBeInTheDocument();
    });
  });


  it("should give password not strong error", () => {
    renderWithRouter(<Register />);
    fireEvent.change(screen.getByTestId("firstname"), {
      target: { value: "John" },
    });
    fireEvent.change(screen.getByTestId("lastname"), {
      target: { value: "Doe" },
    });
    fireEvent.change(screen.getByTestId("email"), {
      target: { value: "john@doe.com" },
    });
    fireEvent.change(screen.getByTestId("password"), {
      target: { value: "Welcome123" },
    });
    fireEvent.change(screen.getByTestId("confirmpassword"), {
      target: { value: "Welcome123" },
    });
    fireEvent.click(screen.getByTestId("btn-submit"));
    waitFor(() => {
      const el = screen.getByText(
        /Please enter password according to instruction/i
      );
      expect(el).toBeInTheDocument();
    });
  });
 
});
