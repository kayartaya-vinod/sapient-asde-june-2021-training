import axiosMock from "axios";
import { COMPARISON_MATRIX_METADATA, COMPARISON_MATRIX_METADATA_ERROR, DELETE_MATRIX } from "../../actionTypes";
import { deleteMatrix, getMatrixMetadata, downloadMatrix} from './matrixMetadataActionCreator'

jest.mock("axios");

describe("matrixMetadataActionCreator", () => {
    it("should get matrix metadata", async () => {
        axiosMock.get.mockResolvedValueOnce({
            data: {data: []},
        });

        expect(await getMatrixMetadata()).toEqual({
            type: COMPARISON_MATRIX_METADATA,
            payload: [],
        });
    });

    it("should return error Action Type", async () => {
        axiosMock.get.mockRejectedValue(new Error("Error"));

        expect(await getMatrixMetadata()).toEqual({
            type: COMPARISON_MATRIX_METADATA_ERROR,
            payload: "Error",
        });
    });

    it("should get delete action", async () => {
        axiosMock.get.mockResolvedValueOnce({
            data: {},
        });

        expect(await deleteMatrix(5)).toEqual({
            type: DELETE_MATRIX,
            payload: 5,
        });
    });

    it("should return NO_DISPATCH_ACTION Action Type on download", async () => {
        axiosMock.get.mockResolvedValueOnce("");
        URL.createObjectURL = jest.fn(() => '');
        expect(await downloadMatrix("id", "filename")).toEqual({
            type: "NO_DISPATCH_ACTION"
        });
    });

    it("should return COMPARISON_MATRIX_METADATA_ERROR Action Type on download error", async () => {
        axiosMock.get.mockRejectedValue(new Error("Error"));
        expect(await downloadMatrix("id", "filename")).toEqual({
            type: "NO_DISPATCH_ACTION",
            payload: "Error",
        });
    });

    it("should return COMPARISON_MATRIX_METADATA_ERROR Action Type on delete error", async () => {
        axiosMock.delete.mockRejectedValue(new Error("Error"));
        expect(await deleteMatrix("id")).toEqual({
            type: "NO_DISPATCH_ACTION",
            payload: "Error",
        });
    });
});