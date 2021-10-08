/**
@Author Mutharasan E - mutharasan.e@publicissapient.com 
*/

import { screen, cleanup, waitFor } from "@testing-library/react";
import CustomerProfile from "./CustomerProfile";
import axiosMock from "axios";
import { renderWithReduxAndRouter } from "../../utils/testUtils";

jest.mock("axios");

describe("CustomerProfile Test", () => {
  afterEach(cleanup);
  const id = 1;
  it("should render Customer Profile", async () => {
    axiosMock.get.mockResolvedValueOnce({
     data:{ data: {
        _id: 1,
        firstName: "Rohit",
        lastName: "Singh",
        email: "rohit@gmail.com",
        alternateEmail: "rohit2@gmail.com",
        password: "4578!@#",
        address: {
          default: {
            buildingNo: "55",
            street: "kali gali",
            landMark: "near tripathi bhawan",
            city: "mirzapur",
            state: "uttar pradesh",
            pinCode: "831313",
          },
          anotherAddress: {
            buildingNo: "55",
            street: "kali gali",
            landMark: "near tripathi bhawan",
            city: "mirzapur",
            state: "uttar pradesh",
            pinCode: "831313",
          },
        },
        contactNo: "7898789878",
        alternateContactNo: "78987898752",
        wishlist: ["carid", "carid2"],
      },
    }});

    renderWithReduxAndRouter(<CustomerProfile {...id} />);

    await waitFor(() => {
      expect(screen.getAllByText(/address/i)).toBeInTheDocument;
      expect(screen.getAllByText(/email/i)).toBeInTheDocument;
    });
  });

  it("should render Profile Error", async () => {
    axiosMock.get.mockResolvedValueOnce({
      data:{data: {
        error: "Network Error",
      }},
    });

    renderWithReduxAndRouter(<CustomerProfile {...id} />);

    await waitFor(() => {
      expect(screen.getAllByText(/error/i)).toBeInTheDocument;
    });
  });

});
