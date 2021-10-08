/**
@Author Sakshi Yadav sakshi.yadav@publicissapient.com
*/

import axiosMock from "axios";
import {
  GET_DEALER_UPLOADS,
  GET_DEALER_UPLOADS_ERROR,
} from "../../actionTypes";
import { waitFor } from "@testing-library/react";
import { fetchDealeruploads } from "./dealerUploadsActionCreator";

jest.mock("axios");

describe("dealerUploadsActionCreator tests", () => {
  const origConsole = console.error;
  beforeEach(() => {
    console.error = jest.fn();
  });
  afterEach(() => {
    console.error = origConsole;
  });

  it(`should return action types : ${GET_DEALER_UPLOADS}`, async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {
        data: [
          {
            fileName: "Honda cars",
          },
        ],
      },
    });
    // eslint-disable-next-line testing-library/await-async-utils
    waitFor(async () => {
      expect(await fetchDealeruploads("d123")).toEqual({
        type: GET_DEALER_UPLOADS,
        payload: { data: [{ fileName: "Honda cars" }] },
      });
    });
  });

  it(`should return action type: ${GET_DEALER_UPLOADS_ERROR} on error `, async () => {
    axiosMock.get.mockRejectedValue({
      response: {
        data: {
          message: "Error",
        },
        success: false,
      },
    });
    expect(await fetchDealeruploads("d123")).toEqual({
      type: GET_DEALER_UPLOADS_ERROR,
      payload: { message: "Error" },
    });
  });

});
