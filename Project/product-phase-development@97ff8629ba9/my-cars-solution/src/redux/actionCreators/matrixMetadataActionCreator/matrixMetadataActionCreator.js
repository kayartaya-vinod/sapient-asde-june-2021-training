/**
@Author <Aditya Gheewala> <aditya.gheewala@publicissapient.com>
*/
import axios from 'axios';
import {
    COMPARISON_MATRIX_METADATA,
    DELETE_MATRIX,
    COMPARISON_MATRIX_METADATA_ERROR
} from '../../actionTypes';
import { ComparisonMatrixMetadataUrl, JWT_TOKEN_KEY, ComparisonMatrixDeleteUrl, DownloadComparisonMatrixUrl } from '../../../constants';
import * as fileSaver from 'file-saver';

axios.defaults.headers.common[
    'Authorization'
] = `JWT ${localStorage[JWT_TOKEN_KEY]}`;

export const getMatrixMetadata = async () => {
    try {
        const resp = await axios.get(ComparisonMatrixMetadataUrl);
        return { type: COMPARISON_MATRIX_METADATA, payload: resp.data.data };
    } catch (error) {
        if(!error.response) {
            return { type: COMPARISON_MATRIX_METADATA_ERROR, payload: error.message };
        } else {
            return { type: COMPARISON_MATRIX_METADATA_ERROR, payload: error.response.data.message };
        }
    }
};

export const deleteMatrix = async (id) => {
    try {
        await axios.delete(ComparisonMatrixDeleteUrl + id);
        return { type: DELETE_MATRIX, payload: id };
    } catch (error) {
        if (!error.response) {
            return { type: 'NO_DISPATCH_ACTION', payload: error.message };
        } else {
            return { type: 'NO_DISPATCH_ACTION', payload: error.response.data.message ? error.response.data.message : "Sorry! Delete failed" };
        }
    }
};

export const downloadMatrix = async (id, filename) => {
    try {
        const response = await axios.get(DownloadComparisonMatrixUrl + id,
            {
                responseType: 'arraybuffer',
            }
        );
        var blob = new Blob([response.data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
        fileSaver.saveAs(blob, filename+'.xlsx');
        return { type: 'NO_DISPATCH_ACTION' };
    } catch (error) {
        if (!error.response) {
            return { type: 'NO_DISPATCH_ACTION', payload: error.message };
        } else {
            return { type: 'NO_DISPATCH_ACTION', payload: error.response.data.message ? error.response.data.message : "Sorry! Download failed" };
        }
    }
}
