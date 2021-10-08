/**
@author Abhishek Singh abhishek.singh8@publicisssapient.com
*/


import axios from 'axios';
import * as fileSaver from 'file-saver';
import { FILTER_DEALER_VEHICLES, GET_DEALER_VEHICLES, NO_DEALER_VECHICLES_DISPATCH_ACTION, REMOVE_SELECTED_DEALER_VEHICLES, RESET_DEALER_VEHICLE_STATE, SELECT_DEALER_VEHICLES } from "../../actionTypes";
import { JWT_TOKEN_KEY, getDealerVehiclesUrl, deleteDealerVehiclesUrl, downloadDealerVehiclesUrl } from '../../../constants';

axios.defaults.headers.common[
  'Authorization'
] = `JWT ${localStorage[JWT_TOKEN_KEY]}`;

export const getDealerVehicles = async (page = 1) => {
  try {
    const { data } = await axios.get(`${getDealerVehiclesUrl}?page=${page}`);
    return { type: GET_DEALER_VEHICLES, payload: data.data };
  } catch (error) {
    if (!error.response) {
      return { type: GET_DEALER_VEHICLES, error: error.message, payload: { content: [], first: true, last: true } };
    } else {
      return { type: GET_DEALER_VEHICLES, error: error.response.data.message, payload: { content: [], first: true, last: true } };
    }
  }
};

export const selectDealerVehicles = (vehicleIds) => {
  return { type: SELECT_DEALER_VEHICLES, payload: vehicleIds };
};

export const removeDealerVehicles = (vehicleIds) => {
  return { type: REMOVE_SELECTED_DEALER_VEHICLES, payload: vehicleIds };
};

export const deleteSelectedVehicles = async (vehicleIds) => {
  try {
    if (vehicleIds.length === 0) {
      throw new Error("No vehicles selected");
    }
    const ids = vehicleIds.join(',');
    await axios.delete(`${deleteDealerVehiclesUrl}?ids=${ids}`);
    return { type: REMOVE_SELECTED_DEALER_VEHICLES, payload: vehicleIds };
  } catch (error) {
    if (!error.response) {
      return { type: NO_DEALER_VECHICLES_DISPATCH_ACTION, payload: { error: error.message } };
    } else {
      return { type: NO_DEALER_VECHICLES_DISPATCH_ACTION, payload: { error: error.response.data.message } };
    }
  }
};


export const downloadSelectedVehicles = async (vehicleIds) => {
  const filename = "vehicles";
  try {
    if (vehicleIds.length === 0) {
      throw new Error("No vehicles selected");
    }
    const ids = vehicleIds.join(',');
    const response = await axios.get(`${downloadDealerVehiclesUrl}?ids=${ids}`,
      {
        responseType: 'blob',
      }
    );
    fileSaver.saveAs(response.data, filename + '.csv');
    return { type: NO_DEALER_VECHICLES_DISPATCH_ACTION };
  } catch (error) {
    if (!error.response) {
      return { type: NO_DEALER_VECHICLES_DISPATCH_ACTION, payload: { error: error.message } };
    } else {
      return { type: NO_DEALER_VECHICLES_DISPATCH_ACTION, payload: { error: error.response.data.message } };
    }
  }
};

export const filterDealerVehicles = (vehicles) => {
  return { type: FILTER_DEALER_VEHICLES, payload: vehicles };
};

export const resetDealerVehicles = () => {
  return { type: RESET_DEALER_VEHICLE_STATE };
};