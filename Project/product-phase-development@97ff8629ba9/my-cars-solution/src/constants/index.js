const processEnv = typeof process !== "undefined" ? process.env : {};
const injectedEnv = window && window.injectedEnv ? window.injectedEnv : {};
export const env = {
  ...processEnv,
  ...injectedEnv,
};
const host = env.REACT_APP_SERVICES_HOST || "localhost";
// const host = "InternalMicroserviceLB-603246501.us-east-1.elb.amazonaws.com"
// console.log("Service_Host", process.env.REACT_APP_SERVICES_HOST,process.env.SERVICES_HOST)
const port = env.REACT_APP_SERVICES_PORT || 8400;
const DNS = env.REACT_APP_SERVICES_DNS || false;

export let baseUrl;

if (DNS) {
  baseUrl = `https://${DNS}/api`;
} else {
  baseUrl = `http://${host}:${port}/api`;
}

export const authUrl = `${baseUrl}/auth/`;

export const loginUrl = `${baseUrl}/auth/login/`;
export const registerUrl = `${baseUrl}/auth/register/`;
export const customerUrl = `${baseUrl}/customers/`;
export const verifyCustomerUrl = `${authUrl}verify-account`;
export const changePasswordUrl = `${customerUrl}change-password`;

// for snachit
export const resetPasswordUrl = `${customerUrl}reset-password-link`;

// for sumitesh
export const checkEmailUrl = `${baseUrl}/auth/verify`;
export const updatePasswordUrl = `${customerUrl}reset-password/`;
export const vehiclesUrl = `${baseUrl}/vehicles/`;
export const advSearchUrl = `${vehiclesUrl}advanced-search`;
//deepthi
export const favUrl = `${customerUrl}favorites/`;

export const vehicleFeedbacksUrl = `${vehiclesUrl}reviews`;

//for Aditya
export const ComparisonMatrixMetadataUrl = `${vehiclesUrl}matrix-metadata`;
export const ComparisonMatrixDeleteUrl = `${vehiclesUrl}comparison/`;
export const DownloadComparisonMatrixUrl = `${vehiclesUrl}comparison/download/`;
export const GetComparisonMatrixUrl = `/customer/vehicle-comparison/`;

// /api/customers/change-password
// payload -> old_password, password, new_password
// header -> auth token

export const sponsoredVehiclesUrl = `${baseUrl}/vehicles/sponsored`;
export const displayCompareUrl = `${vehiclesUrl}comparison/`;
// keys used in localStorage
export const ID_KEY = "D59CD5C27EB9134AD035";
export const NAME_KEY = "2D719C6D7A8392A1B109";
export const EMAIL_KEY = "083122F3048FA4A029B0";
export const JWT_TOKEN_KEY = "36AF7A19E5D9789B9307";

//  not used in this sprint

export const wiremockUrl = "";
// this needs to be removed
// export const wiremockUrl = '';

export const wmUrl = "";

export const uploadCSVurl = `${baseUrl}/data/upload/csv`;
// Roles
export const ROLE_CUSTOMER = "610d13ba774d3042d588dca6";
export const ROLE_DEALER = "610d13ba774d3042d588dcb8";

export const ROLE_KEY = "610d13ba774d3042d588dca0";

// dealer service
export const dealerUrl = `${baseUrl}/vehicles/dealer/`;
export const updateVehicleDetailsUrl = `${dealerUrl}/update-vehicle`;

export const getDealerVehiclesUrl = `${dealerUrl}`;
export const downloadDealerVehiclesUrl = `${baseUrl}/data/download/csv`;
export const deleteDealerVehiclesUrl = `${dealerUrl}`;
export const addAccessoryUrl = `${baseUrl}/vehicles/add-accessory`;

export const getDealerReviewsUrl = `${dealerUrl}feedbacks`;

// comparison
export const vehicleCompareUrl = `${baseUrl}/vehicles/compare`;
export const FILE_UPLOAD_SUCCESS = "File uploaded successfully";

export const advSearchAttributesUrl = `${baseUrl}/vehicles/search-attributes`;
export const uploadJsonUrl = `${baseUrl}/data/upload/json`;
// for vehicle accesories
export const vehicleAccessoryUrl = `${baseUrl}/vehicles/accessory/`;
//for add new customer
export const addCustomerUrl = `${customerUrl}add-customer/`;
export const addVehicleToCompareUrl = `${vehiclesUrl}comparison`;

export const updateAccessoryUrl = `${baseUrl}/vehicles/accessory/`;
export const getDealerAccessoriesUrl = `${dealerUrl}accessories/`;
export const AccessoryDeleteUrl = `${dealerUrl}delete-accessory`;
