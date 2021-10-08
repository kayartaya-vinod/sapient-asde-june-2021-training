/**
@Author1 Mutharasan E - mutharasan.e@publicissapient.com 
@Author2 Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/

import {
  GET_CUSTOMER_BY_ID,
  GET_CUSTOMER_BY_ID_ERROR,
  LOGOUT_SUCCESS,
  UPDATE_CUSTOMER,
  UPDATE_CUSTOMER_ERROR,
} from "../../actionTypes";

const initialState = {
  alternateEmail: "",
  address: {
    default: {
      buildingNo: "",
      street: "",
      landMark: "",
      city: "",
      state: "",
      pinCode: "",
    },
    anotherAddress: {
      buildingNo: "",
      street: "",
      landMark: "",
      city: "",
      state: "",
      pinCode: "",
    },
  },
  contactNo: "",
  alternateContactNo: "",
  wishlist: [],
  isVerified: false,
  userId: "",
};
const singleCustomerReducer = (state = initialState, action = {}) => {
  switch (action.type) {
    case GET_CUSTOMER_BY_ID: {
      return { ...state, ...action.payload };
    }
    case UPDATE_CUSTOMER: {
      return { ...state, ...action.payload };
    }
    case GET_CUSTOMER_BY_ID_ERROR: {
      return { ...state, ...action.payload };
    }
    case UPDATE_CUSTOMER_ERROR: {
      return { ...state, ...action.payload };
    }
    case LOGOUT_SUCCESS: {
      return { ...initialState };
    }
    default:
      return state;
  }
};

export default singleCustomerReducer;
