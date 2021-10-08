import { combineReducers } from "redux";
import searchReducer from "./searchReducer/searchReducer";
import singleCustomerReducer from "./singleCustomerReducer/singleCustomerReducer";
import authReducer from "./authReducer/authReducer";
import checkEmailReducer from "./checkEmailReducer/checkEmailReducer";
import updatePasswordReducer from "./updatePasswordReducer/updatePasswordReducer";
import singleVehicleReducer from "./singleVehicleReducer/singleVehicleReducer";
import passwordChangeReducer from "./passwordChangeReducer/passwordChangeReducer";
import filterAttributeReducer from "./filterAttributeReducer/filterAttributeReducer";
import fetchFilteredDataReducer from "./filterAttributeReducer/fetchFilteredDataReducer";
import signupReducer from "./signupReducer/signupReducer";
import favoriteVehicleReducer from "./favoriteVehicleReducer/favoriteVehicleReducer";
import vehicleCompareListReducer from "./vehicleCompareListReducer/vehicleCompareListReducer";
import sponsoredVehicleReducer from "./sponsoredVehicleReducer/sponsoredVehicleReducer";
import matrixMetadataReducer from "./matrixMetadataReducer/matrixMetadataReducer";
import vehiclesReducer, { addVehicle } from "./vehiclesReducer/vehiclesReducer";
import displayVehicleComparisonReducer from "./displayVehicleComparisonReducer/displayVehicleComparisonReducer";
import advancedSearchReducer from "./advancedSearchReducer/advancedSearchReducer";
import dealerVehicleReducer from "./dealerVehicleReducer/dealerVehicleReducer";
import vehicleFilterAttributeReducer from "./vehicleFilterAttributeReducer/vehicleFilterAttributeReducer";

import vehicleAccessoryReducer from "./vehicleAccessoryReducer/vehicleAccessoryReducer";
import vehicleFeedbacksReducer from "./vehicleFeedbacksReducer/vehicleFeedbacksReducer";
import vehicleObjectCompareReducer from "./vehicleObjectCompareReducer/vehicleObjectCompareReducer";
import dealerReviewReducer from "./dealerReviewReducer/dealerReviewReducer";
import addAccessoryReducer from "./addAccessoryReducer/addAccessoryReducer";

import dealerAccessoriesReducer from "./dealerAccessoriesReducer/dealerAccessoriesReducer";

export default combineReducers({
  authReducer,
  searchReducer,
  singleCustomerReducer,
  checkEmailReducer,
  updatePasswordReducer,
  singleVehicleReducer,
  passwordChangeReducer,
  filterAttributeReducer,
  fetchFilteredDataReducer,
  signupReducer,
  favoriteVehicleReducer,
  vehicleCompareListReducer,
  sponsoredVehicleReducer,
  matrixMetadataReducer,
  vehiclesReducer,
  displayVehicleComparisonReducer,
  advancedSearchReducer,
  addVehicle,
  dealerVehicleReducer,
  vehicleFeedbacksReducer,
  vehicleObjectCompareReducer,
  vehicleFilterAttributeReducer,
  vehicleAccessoryReducer,
  dealerReviewReducer,
  addAccessoryReducer,
  dealerAccessoriesReducer
});
