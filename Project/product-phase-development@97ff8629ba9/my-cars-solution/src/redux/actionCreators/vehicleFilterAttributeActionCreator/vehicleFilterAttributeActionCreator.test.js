import {
  GET_VEHICLE_FILTER_ATTRIBUTE,
  GET_VEHICLE_FILTER_ATTRIBUTE_ERROR,
  UPDATE_VEHICLE_FILTER,
} from "../../actionTypes";
import axiosMock from "axios";
import { getFilterAttributes, updateFilters } from "./vehicleFilterAttributeActionCreator";
jest.mock("axios");


describe("VehicleFilterAttributeActionCreator test",()=>{
    it("should return list of attribute ",async()=>{

        axiosMock.get.mockResolvedValueOnce({
          data: {
            data:[]
          },
        });
        expect(await getFilterAttributes()).toEqual({
            type:GET_VEHICLE_FILTER_ATTRIBUTE,
            payload:[]
        });
    })
      it("should give error ", async () => {
   
        axiosMock.get.mockRejectedValue(new Error("Error"));
        expect(await getFilterAttributes()).toEqual({
          type: GET_VEHICLE_FILTER_ATTRIBUTE_ERROR,
          payload: "Error",
        });
      });
      it("should update queryField ",()=>{
        const attribute = "brand";
        const queryField = ["Maruti"];

        expect(updateFilters(attribute, queryField)).toEqual({type: UPDATE_VEHICLE_FILTER, payload: {attribute, queryField}})
      })
})