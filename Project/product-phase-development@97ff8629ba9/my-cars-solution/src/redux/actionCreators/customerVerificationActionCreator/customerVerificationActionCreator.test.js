/**
@Author Rohit Bhatt rohit.bhatt1@publicissapient.com
*/
import axiosMock from "axios";
import { VERIFY_CUSTOMER, VERIFY_CUSTOMER_ERROR } from "../../actionTypes";
import { verifyCustomer } from "./customerVerificationActionCreator";

jest.mock("axios");
describe("testing customer verification's action creator", () => {
    it("show give success: true", async() => {
        axiosMock.post.mockResolvedValueOnce({
          data: {
            success: true,
            name: "Rohit Bhatt",
          },
        });
        expect(await verifyCustomer("token")).toEqual({
            type: VERIFY_CUSTOMER, payload:{success: true, name: "Rohit Bhatt"}
        });
    })

    it("should return error", async () => {
      axiosMock.get.mockRejectedValue(new Error("Error"));

      expect(await verifyCustomer()).toEqual({
        type: VERIFY_CUSTOMER_ERROR,
        payload: "Cannot read property 'data' of undefined",
      });
    });


});