/**
@Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
*/
import axios from "axios";
import { uploadJsonUrl } from "../../../constants";
import { UPLOAD_JSON, UPLOAD_JSON_ERROR } from "../../actionTypes";

export const uploadFile = async (data1) => {
    try {
        const { data } = await axios.post(uploadJsonUrl, data1);
        return { type: UPLOAD_JSON, payload: data };
    } catch (error) {
        if (!error.response) {
            return { type: UPLOAD_JSON_ERROR, payload: error.message };
        }
        return { type: UPLOAD_JSON_ERROR, payload: error.response.data };
    }
};
