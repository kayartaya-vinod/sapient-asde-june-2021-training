/**
@Author Rohit Bhatt rohit.bhatt1@publicissapient.com
*/
import axios from 'axios';
import { VERIFY_CUSTOMER, VERIFY_CUSTOMER_ERROR } from '../../actionTypes';
import { verifyCustomerUrl } from "../../../constants/index";

export const verifyCustomer = async(token) =>{
    try {
        axios.defaults.headers.common["Authorization"] = `JWT ${token}`;
        const resp = await axios.post(verifyCustomerUrl);
        delete axios.defaults.headers.common["Authorization"];
        return {type:VERIFY_CUSTOMER, payload:resp.data };
    } catch (error) {
        delete axios.defaults.headers.common["Authorization"];
        return {type: VERIFY_CUSTOMER_ERROR, payload:error.message};
    }
};
