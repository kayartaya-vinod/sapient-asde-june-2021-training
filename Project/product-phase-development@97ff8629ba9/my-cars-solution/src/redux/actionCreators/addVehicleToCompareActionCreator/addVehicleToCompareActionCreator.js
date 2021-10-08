
/**
@Author Yogamber Singh Negi yogamber.negi@publicissapient.com
*/
import axios from "axios";
import { addVehicleToCompareUrl } from "../../../constants";
import { ADD_VEHICLE_TO_COMPARE, ADD_VEHICLE_TO_COMPARE_ERROR } from "../../actionTypes";

export const getAllBrands = async (brand="",model="") => {
    try {
        const  {data}  = await axios.get(`${addVehicleToCompareUrl}?brand=${brand}&&model=${model}`);
        return { type: ADD_VEHICLE_TO_COMPARE, payload: data };
    } catch (error) {
        return { type: ADD_VEHICLE_TO_COMPARE_ERROR, payload: {data:[]} };
    }
};
