import matrixMetadataReducer from './matrixMetadataReducer';
import { COMPARISON_MATRIX_METADATA, COMPARISON_MATRIX_METADATA_ERROR, DELETE_MATRIX, LOGOUT_SUCCESS } from '../../actionTypes';

describe("matrixMetadataReducer tests", () => {

    const mockData = [
        {
            "id": "hjasghdakjshdkj",
            "userId": "hgfhzgvcx65c4xc",
            "vehicleIds": ["id1", "id2"],
            "comparisonMatrixName": "Name1",
            "createdDate": "1/1/1"
        },
        {
            "id": "hjasghdakjshdkjk",
            "userId": "hgfhzgvcx65c4xc",
            "vehicleIds": ["id1", "id2"],
            "comparisonMatrixName": "Name2",
            "createdDate": "1/1/1"
        },
        {
            "id": "hjasghdakjshdkjl",
            "userId": "hgfhzgvcx65c4xc",
            "vehicleIds": ["id1", "id2"],
            "comparisonMatrixName": "Name3",
            "createdDate": "1/1/1"
        }
    ];

    it("should return default state", () => {
        const state = matrixMetadataReducer();
        expect(state).toEqual({ matrix: [], error: "" });
    });

    it("should return COMPARISON_MATRIX_METADATA state", () => {
        const state = matrixMetadataReducer({ matrix: [], error: "" }, { type: COMPARISON_MATRIX_METADATA, payload: mockData });
        expect(state).toHaveProperty("matrix");
        expect(state).toEqual({ matrix: mockData, error: ""});
    });

    it("should return DELETE_MATRIX state", () => {
        const state = matrixMetadataReducer({ matrix: [{ "id": "1" }, { "id": "2" }], error: "" }, { type: DELETE_MATRIX, payload: "1" });
        expect(state).toEqual({ matrix: [{ "id": "2" }], error: "" });
    });

    it("should return COMPARISON_MATRIX_METADATA_ERROR state", () => {
        const state = matrixMetadataReducer({ matrix: [], error: "" }, { type: COMPARISON_MATRIX_METADATA_ERROR, payload: "Error" });
        expect(state).toEqual({ matrix: [], error: "Error" });
    });

    it("should return LOGOUT_SUCCESS state", () => {
        const state = matrixMetadataReducer({ matrix: [{ "id": "1" }, { "id": "2" }], error: "" }, { type: LOGOUT_SUCCESS});
        expect(state).toEqual({ matrix: [], error: "" });

    });
});