/**
@Author1 Mutharasan E - mutharasan.e@publicissapient.com 
@Author2 Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/

import { fetchCustomerById, updateCustomer } from "./customerActionCreator";
import axiosMock from "axios";
import {
  GET_CUSTOMER_BY_ID,
  GET_CUSTOMER_BY_ID_ERROR,
  UPDATE_CUSTOMER,
  UPDATE_CUSTOMER_ERROR,
} from "../../actionTypes";

jest.mock("axios");
describe("customerActionCreator test", () => {
  it(`should return action type: ${GET_CUSTOMER_BY_ID} on resolve `, async () => {
    axiosMock.get.mockResolvedValueOnce({
     data:{ data: {
        name: "Muthu",
      }},
    });

    expect(await fetchCustomerById(1)).toEqual({
      type: GET_CUSTOMER_BY_ID,
      payload: { name: "Muthu" },
    });
  });

  it(`should return action type: ${GET_CUSTOMER_BY_ID_ERROR} on error `, async () => {
    axiosMock.get.mockRejectedValue(new Error("Error"));

    expect(await fetchCustomerById(1)).toEqual({
      type: GET_CUSTOMER_BY_ID_ERROR,
      payload: { error: "Error" },
    });
  });

  it(`should return action type: ${UPDATE_CUSTOMER} on resolve `, async () => {
    axiosMock.put.mockResolvedValueOnce({
      data: {
        name: "John",
      },
    });

    expect(await updateCustomer(1)).toEqual({
      type: UPDATE_CUSTOMER,
      payload: { name: "John" },
    });
  });

  it(`should return action type: ${GET_CUSTOMER_BY_ID_ERROR} on error `, async () => {
    axiosMock.put.mockRejectedValue(new Error("Error"));

    expect(await updateCustomer(1)).toEqual({
      type: UPDATE_CUSTOMER_ERROR,
      payload: { error: "Error" },
    });
  });
});
