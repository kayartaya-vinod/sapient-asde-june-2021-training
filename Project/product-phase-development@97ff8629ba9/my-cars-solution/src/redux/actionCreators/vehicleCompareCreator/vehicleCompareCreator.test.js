import { ADD_TO_COMPARE, REMOVE_FROM_COMPARE } from "../../actionTypes";
import {addToCompare, removeFromCompare} from "./vehicleCompareCreator";

describe("vehicleCompareCreator Test", () =>{
    it("should render add cmopare array of vehicleId and vehicle Name",()=>{
        expect(addToCompare("id1")).toEqual({
          type: ADD_TO_COMPARE,
          payload: "id1",
        });
    });
    it("should render remvoe compare array of vehicleId and vehicle Name", () => {
      expect(removeFromCompare("id1")).toEqual({
        type: REMOVE_FROM_COMPARE,
        payload: "id1",
      });
    });
});   