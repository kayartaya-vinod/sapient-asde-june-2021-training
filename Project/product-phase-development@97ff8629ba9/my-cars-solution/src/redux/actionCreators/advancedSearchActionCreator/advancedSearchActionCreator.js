import {
  ADD_REMOVE_BRAND,
  ADD_REMOVE_AIRBAG_COUNT,
  ADD_REMOVE_VEHICLE_COLOR,
  ADD_REMOVE_VEHICLE_TYPE,
  ADD_REMOVE_FUEL_TYPE,
  UPDATE_PRICE,
  UPDATE_YEAR
} from "../../actionTypes";

export const addOrRemoveBrand = (brand) => {
  return { type: ADD_REMOVE_BRAND, payload: brand };
};
export const addOrRemoveAirBagCount = (airBagCount)=>{
    return {type:ADD_REMOVE_AIRBAG_COUNT,payload:airBagCount};
};
export const addOrRemoveVehicleType = (vehicleType) => {
  return { type: ADD_REMOVE_VEHICLE_TYPE, payload: vehicleType };
};
export const addOrRemoveVehicleColor=(color) =>{
    return { type:ADD_REMOVE_VEHICLE_COLOR,payload: color};
};

export const addOrRemoveFuelType = (fuelType) => {
  return { type: ADD_REMOVE_FUEL_TYPE, payload: fuelType };
};

export const updatePrice =(price)=>{
  return {type: UPDATE_PRICE ,payload:price};
}

export const updateYear =(year)=>{
  return {type: UPDATE_YEAR,payload:year};
}
