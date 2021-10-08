import axios from "axios";
import { uploadCSVurl } from "../../../constants";
import { UPLOAD_CSV, UPLOAD_CSV_ERROR } from "../../actionTypes";

export const uploadFile = async (data1) => {
 try {
    const {data} = await axios.post(uploadCSVurl,data1);
    return { type: UPLOAD_CSV, payload: data };
 } catch (error) {
    return { type: UPLOAD_CSV_ERROR, payload:error.response.data };
  }
};
