/**
@Author Jay Aditya Nautiyal
*/

import axiosMock from "axios";
import {
    SAVE_COMPARISON_MATRIX, SAVE_COMPARISON_MATRIX_ERROR,
} from "../../actionTypes";
import { saveComparisonMatrix } from "./saveMatrixActionCreator";

jest.mock("axios");
describe("saveMatrixActionCreator test", () => {
    it(`should return action type: ${SAVE_COMPARISON_MATRIX} on resolve `, async () => {
        axiosMock.post.mockResolvedValueOnce({
            data: {
                response: "saved successfully",
            }
        });

        expect(await saveComparisonMatrix()).toEqual({
            type: SAVE_COMPARISON_MATRIX,
            payload: { response: "saved successfully" },
        });
    });

    it(`should return action type: ${SAVE_COMPARISON_MATRIX_ERROR} on error `, async () => {
        axiosMock.post.mockRejectedValue(new Error("Error"));

        expect(await saveComparisonMatrix()).toEqual({
            type: SAVE_COMPARISON_MATRIX_ERROR,
            payload: { error: "Error" },
        });
    });

    // it(`should return action type: ${UPDATE_CUSTOMER} on resolve `, async () => {
    //     axiosMock.put.mockResolvedValueOnce({
    //         data: {
    //             name: "John",
    //         },
    //     });

    //     expect(await updateCustomer(1)).toEqual({
    //         type: UPDATE_CUSTOMER,
    //         payload: { name: "John" },
    //     });
    // });

    // it(`should return action type: ${GET_CUSTOMER_BY_ID_ERROR} on error `, async () => {
    //     axiosMock.put.mockRejectedValue(new Error("Error"));

    //     expect(await updateCustomer(1)).toEqual({
    //         type: UPDATE_CUSTOMER_ERROR,
    //         payload: { error: "Error" },
    //     });
    // });
});
