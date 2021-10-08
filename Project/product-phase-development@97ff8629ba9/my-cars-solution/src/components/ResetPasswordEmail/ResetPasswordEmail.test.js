/**
@Author <Sanchit Mahto> <sanchit.mahto@publicissapient.com>
*/

import {
  cleanup,
  fireEvent,
  render,
  screen,
  waitFor,
} from "@testing-library/react";
import ResetPasswordEmail from "./ResetPasswordEmail";
import { EMAIL_VERIFIED } from "../../redux/actionTypes";
jest.mock("axios");
const mockDispatch = jest.fn();

jest.mock("react-redux", () => ({
  useDispatch: () => mockDispatch,
}));

describe("ResetPasswordEmail test", () => {
  afterEach(cleanup);

  it("should render ResetPasswordEmail component", () => {
    render(<ResetPasswordEmail />);

    const el = screen.getByText(/Forgot Password/i);
    expect(el).toBeInTheDocument();
    expect(el.tagName).toBe("H3");
  });

  it("should render ResetPasswordEmail component", () => {
    render(<ResetPasswordEmail />);

    const el = screen.getByText(
      /We will be sending a reset password link to your email./i
    );
    expect(el).toBeInTheDocument();
  });

  it("should dispatch EMAIL_VERIFIED action on registered email", () => {
    const { getByTestId } = render(<ResetPasswordEmail />);

    fireEvent.change(getByTestId("email"), {
      target: { value: "sanchit@gmail.com" },
    });

    fireEvent.click(screen.getByTestId("submitbtn"));

    waitFor(() =>
      expect(mockDispatch).toHaveBeenCalledWith({
        type: EMAIL_VERIFIED,
        payload: "sanchit@gmail.com",
      })
    );
  });

  it("should show message on entering registered email", () => {
    const { getByTestId } = render(<ResetPasswordEmail />);

    fireEvent.change(getByTestId("email"), {
      target: { value: "sanchit@gmail.com" },
    });

    fireEvent.click(getByTestId("submitbtn"));
    try {
      waitFor(async () => {
        expect(
          await screen.findByText(/Email sent, please check your inbox/i)
        ).toBeInTheDocument();
      });
    } catch (error) {
      console.log("Error MSg", error);
    }
  });
  it("should show message on entering not registered email", async () => {
    const { getByTestId } = render(<ResetPasswordEmail />);

    fireEvent.change(getByTestId("email"), {
      target: { value: "sanchit12@gmail.com" },
    });

    fireEvent.click(screen.getByTestId("submitbtn"));
    waitFor(async () => {
      const el = screen.getByText(/Email not registered/i);
      expect(el).toBeInTheDocument();
    });
  });
  it("should show message on entering not valid email", () => {
    const { getByTestId, getByText } = render(<ResetPasswordEmail />);

    fireEvent.change(getByTestId("email"), {
      target: { value: "sanchit" },
    });
    try {
      waitFor(async () => {
        const el = getByText(/Email not valid/i);
        expect(el).toBeInTheDocument();
      });
    } catch (error) {
      console.log(error);
    }
  });
  it("should show message on entering not valid  email", () => {
    const { getByTestId, getByText } = render(<ResetPasswordEmail />);

    fireEvent.change(getByTestId("email"), {
      target: { value: "sanchit" },
    });
    fireEvent.click(screen.getByTestId("submitbtn"));
    try {
      waitFor(async () => {
        const el = getByText(/Email not valid/i);
        expect(el).toBeInTheDocument();
      });
    } catch (error) {
      console.log(error);
    }
  });
  it("should show message on entering empty email", () => {
    const { getByTestId, getByText } = render(<ResetPasswordEmail />);

    fireEvent.change(getByTestId("email"), {
      target: { value: "" },
    });
    fireEvent.click(screen.getByTestId("submitbtn"));
    try {
      waitFor(async () => {
        const el = getByText(/Please enter email/i);
        expect(el).toBeInTheDocument();
      });
    } catch (error) {
      console.log(error);
    }
  });
});
