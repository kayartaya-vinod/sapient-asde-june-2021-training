/**
@Author Sakshi Yadav - sakshi.yadav@publicissapient.com 
*/

import { GET_VEHICLE_BY_ID, GET_VEHICLE_BY_ID_ERROR, RESET_SINGLE_VEHICLE_STATE } from '../../actionTypes';

const initialState = {
  dealerId: "",
  tankCapacity: "",
  mediaInterface: "",
  theftAlarm: "",
  pictureUrls: [],
  tripMeter: "",
  airBagCount: "",
  description: "",
  price: "",
  color: "",
  vin: "",
  brand: "",
  model: "",
  year: "",
  vehicleType: "",
  fuelType: [],
  unitsFuelConsumption: "",
  transmissionGearType: "",
  steeringWheelType: "",
  vehicleSpeed: "",
  horn: "",
  powerTrainTorque: "",
  accelaration: "",
  nightMode: "",
  isABS: false,
  wheelRadius: "",
  wheelSpeed: "",
  light: {
    fog: false,
    hazard: false,
    parking: false,
    dynamicHighBeam: false,
    automaticHeadlights: false,
  },
  ignitionTime: "",
  odometer: false,
  washerFluid: false,
  malfunctionIndicator: false,
  batteryLevel: "",
  airConditioning: false,
  languageConfiguration: "",
  mirror: "automatic",
  childSafetyLock: false,
  topSpeedLimit: "",
  climate: {
    climateControl: false,
    sunroof: false,
    defrost: false,
  },
  chime: false,
};

const singleVehicleReducer = (state = initialState, action = {}) => {
  switch (action.type) {
    case GET_VEHICLE_BY_ID: {
      return { ...action.payload };
    }

    case GET_VEHICLE_BY_ID_ERROR: {
      return { ...action.payload };
    }

    case RESET_SINGLE_VEHICLE_STATE: {
      return initialState;
    }
    default:
      return state;
  }
};

export default singleVehicleReducer;
