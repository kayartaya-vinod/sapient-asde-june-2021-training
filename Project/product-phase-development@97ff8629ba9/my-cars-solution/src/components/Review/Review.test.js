/**
@Author Rohit Bhatt rohit.bhatt1@publicissapient.com
*/
import { renderWithReduxAndRouter } from "./../../utils/testUtils";
import Review from "./Review";
import { fireEvent, screen, waitFor } from "@testing-library/react";
import axiosMock from "axios";
import { act } from "react-dom/test-utils";

jest.mock("axios");

describe("Review Tests", () => {
  it("Should render the review component", () => {
    const { container } = renderWithReduxAndRouter(<Review vehicleId={123} />);
    expect(container).not.toBeNull();
  });
  it("Should test handleChange", () => {
    const { getByTestId } = renderWithReduxAndRouter(
      <Review vehicleId={123} />
    );
    fireEvent.change(getByTestId("test-review"), {
      target: {
        value: "This is a new review",
      },
    });
    expect(getByTestId("test-review")).not.toBeNull();
  });
  it("Should fire submit button", () => {
    const { getByTestId } = renderWithReduxAndRouter(
      <Review vehicleId={123} />
    );
    act(() => {
      fireEvent.submit(getByTestId("test-review-form"));
    });
    expect(getByTestId("test-review-btn")).toBeInTheDocument();
  });
  it("Should fire submit button for less than 10 characters", () => {
    const { getByTestId } = renderWithReduxAndRouter(
      <Review vehicleId={123} />
    );
    fireEvent.change(getByTestId("test-review"), {
      target: {
        value: "Ten",
      },
    });
    fireEvent.click(getByTestId("test-review-btn"));
    expect(
      screen.getByText(/The number of characters should not be less then 10/i)
    ).toBeInTheDocument();
  });
  it("Should fire submit button for more than 500 characters", () => {
    const { getByTestId } = renderWithReduxAndRouter(
      <Review vehicleId={123} />
    );
    fireEvent.change(getByTestId("test-review"), {
      target: {
        value:
          "Ten The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10Ten The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10Ten The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10The number of characters should not be less then 10",
      },
    });
    act(() => {
      fireEvent.submit(getByTestId("test-review-form"));
    });
    expect(
      screen.getByText(/The number of characters cannot be greater than 500/i)
    ).toBeInTheDocument();
  });
  it("Should fire submit button for characters between 10 and 500", async () => {
    axiosMock.post.mockResolvedValueOnce({
      data: {
        success: true,
      },
    });
    const { getByTestId } = renderWithReduxAndRouter(
      <Review vehicleId={123} />
    );
    fireEvent.change(getByTestId("test-review"), {
      target: {
        value: "This is a new review",
      },
    });
    act(() => {
      fireEvent.submit(getByTestId("test-review-form"));
    });
    await waitFor(() => {
      expect(getByTestId("test-review-msg1")).toHaveTextContent(
        "Your review has been added!"
      );
    });
  });
  it("Should mock axios", () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {
        success: true,
        review: "This is the initial review!",
      },
    });
    const { container } = renderWithReduxAndRouter(<Review vehicleId={123} />);
    expect(container).not.toBeNull();
  });
});
