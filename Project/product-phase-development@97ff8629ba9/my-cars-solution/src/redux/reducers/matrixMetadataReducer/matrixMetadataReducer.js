/**
@Author <Aditya Gheewala> <aditya.gheewala@publicissapient.com>
*/
import {
    COMPARISON_MATRIX_METADATA,
    DELETE_MATRIX,
    COMPARISON_MATRIX_METADATA_ERROR,
    LOGOUT_SUCCESS
} from "../../actionTypes";

const matrixMetadataReducer = (state = {matrix: [], error: ""}, action = {}) => {
    switch (action.type) {
        case COMPARISON_MATRIX_METADATA:
            let newState = {...state};
            newState["matrix"] = [...action.payload];
            newState.matrix.forEach(m => {
                const newCreatedDate = m.createdDate.split('T')[0];
                m.createdDate = newCreatedDate;
                return {...m};
            });
            newState["error"] = "";
            return { ...newState};
        case DELETE_MATRIX:
            const delState = { ...state };
            delState["error"] = "";
            const matrix = delState["matrix"];
            const index = matrix.findIndex((m) => m.id === action.payload);
            matrix.splice(index, 1);
            delState["matrix"] = matrix;
            return { ...delState};
        case COMPARISON_MATRIX_METADATA_ERROR:
            const errState = { ...state };
            errState["matrix"] = [];
            errState["error"] = action.payload;
            return { ...errState };
        case LOGOUT_SUCCESS: 
            return { matrix: [], error: "" };
        default:
            return state;
    }
};
export default matrixMetadataReducer;