/**
# @Author Yogamber Singh Negi yogamber.negi@publicissapient.com

@Author Aditya Gheewala aditya.gheewala@publicissapient.com [fetchSearchAccessories related tests]
# */
import { fetchSearch, fetchSearchAccessories } from "./searchActionCreator";
import axiosMock from "axios";
import {
  SEARCH,
  SEARCH_ERROR, SEARCH_ACCESSORIES, SEARCH_ACCESSORIES_1, SEARCH_1} from "../../actionTypes";

jest.mock("axios");
describe("SearchCreator test", () => {
  it(`should return action type: ${SEARCH} on resolve `, async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: {
        name: "yogamber",
      },
    });

    expect(await fetchSearch("yogamber")).toEqual({
      type: SEARCH,
      payload: { name: "yogamber" },
    });

    axiosMock.get.mockResolvedValueOnce({
      data: {
        name: "yogamber",
      },
    });

    expect(await fetchSearch("yogamber",1)).toEqual({
      type: SEARCH_1,
      payload: { name: "yogamber" },
    });
  });

  it(`should return action type: ${SEARCH_ACCESSORIES} on resolve `, async () => {
    axiosMock.get.mockResolvedValueOnce({
      data: { data : {
        name: "accessory",
      }
      },
    });
    
    expect(await fetchSearchAccessories("yogamber",1)).toEqual({
      type: SEARCH_ACCESSORIES_1,
      payload: { name: "accessory" },
    });
    
    axiosMock.get.mockResolvedValueOnce({
      data: { data : {
        name: "accessory",
      }
      },
    });

    expect(await fetchSearchAccessories("yogamber")).toEqual({
      type: SEARCH_ACCESSORIES,
      payload: { name: "accessory" },
    });
  });

  it(`should return action type: ${SEARCH_ERROR} on error `, async () => {
    axiosMock.get.mockRejectedValue(new Error([]));

    expect(await fetchSearch("yogamber")).toEqual({
      type: SEARCH_ERROR,
      payload: [],
    });
  });

  it(`should return action type: ${SEARCH_ERROR} on error in fetchSearchAccessories`, async () => {
    axiosMock.get.mockRejectedValue(new Error([]));

    expect(await fetchSearchAccessories("error")).toEqual({
      type: SEARCH_ERROR,
      payload: [],
    });
  });
});
