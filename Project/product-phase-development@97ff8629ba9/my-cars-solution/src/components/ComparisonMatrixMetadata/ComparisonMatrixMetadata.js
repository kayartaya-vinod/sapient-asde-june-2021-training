/**
@Author <Aditya Gheewala> <aditya.gheewala@publicissapient.com>
*/
import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { getMatrixMetadata, deleteMatrix, downloadMatrix } from '../../redux/actionCreators/matrixMetadataActionCreator/matrixMetadataActionCreator';
import { useHistory } from 'react-router-dom';
import { BsTrash } from "react-icons/bs";
import { FiDownload } from "react-icons/fi";
import { GetComparisonMatrixUrl } from '../../constants';
import PleaseWait from "../PleaseWait/PleaseWait";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


export default function ComparisonMatrixMetadata () {

    const { matrix, error } = useSelector((store) => store.matrixMetadataReducer);

    const dispatch = useDispatch();

    const history = useHistory();

    const [pleaseWait, setPleaseWait] = useState(true);

    let [deleteId, setDeleteId] = useState();

    const handleClick = (id) => {
        history.push(GetComparisonMatrixUrl + id);
    };

    const handleDelete = async () => {
        const resp = dispatch(await deleteMatrix(deleteId));
        if (resp.type === 'NO_DISPATCH_ACTION') {
            toast.error(resp.payload, {
                position: "bottom-right",
                autoClose: 2500,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
        }
    };

    const handleDownload = async(id, comparisonMatrixName) => {
        const resp = dispatch(await downloadMatrix(id, comparisonMatrixName));
        if (resp.payload) {
            toast.error(resp.payload, {
                position: "bottom-right",
                autoClose: 2500,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
        }
    };

    useEffect(() => {
        (async function () {
            dispatch(await getMatrixMetadata());
            setPleaseWait(false);
        })();
    }, [dispatch]);


    const list = matrix.map((m, index) => (

        <tr key={m.id} data-testid={'row'}>
            <th scope="row" style={{ cursor: "pointer" }} onClick={() => handleClick(m.id)} data-testid={'index' + index}>{index + 1}</th>
            <td style={{ cursor: "pointer" }} onClick={() => handleClick(m.id)} data-testid={'name' + index}>
                <p>{m.comparisonMatrixName}</p>
            </td>
            <td style={{ cursor: "pointer" }} onClick={() => handleClick(m.id)} data-testid={'date' + index}>{m.createdDate}</td>
            <td style={{ cursor: "pointer" }} onClick={() => handleClick(m.id)} data-testid={'size' + index}>{m.vehicleIds.length}</td>
            <td
                onClick={() => handleDownload(m.id, m.comparisonMatrixName)}
                style={{ cursor: "pointer" }}
            >
                <FiDownload
                    size={20}
                    data-testid={'btn-download' + index}
                />
            </td>
            <td
                onClick={() => setDeleteId(m.id)}
                data-bs-toggle="modal" data-bs-target="#alertModal"
                style={{ cursor: "pointer" }}
            >
                <BsTrash
                    size={20}
                    data-testid={'btn-delete' + index}
                />
            </td>
        </tr>
    ));

    const errorMessage = pleaseWait ? <PleaseWait /> : (error || "No saved comparisons found");

    return (
        <div className='container'>
            <h2>Saved Comparisons</h2>
            <hr /><br />
            <div className='row'>
                <div className='col'>
                    <div className="table-responsive">
                        {matrix.length > 0 ?
                            (
                                <table className="table table-hover" style={{ textAlign: "center" }}>
                                    <thead>
                                        <tr>
                                            <th scope="col">No.</th>
                                            <th scope="col">Name</th>
                                            <th scope="col">Date of creation</th>
                                            <th scope="col">Number of vehicles</th>
                                            <th scope="col">Download</th>
                                            <th scope="col">{" "}</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {list}
                                    </tbody>
                                </table>)
                            : <h4>{errorMessage}</h4>}
                    </div>
                </div>
            </div>


            <div className="modal fade" id="alertModal" tabIndex="-1" aria-labelledby="alert" aria-hidden="true">
                <div className="modal-dialog modal-dialog-centered" data-testid="alert-modal">
                    <div className="modal-content">
                        <div className="modal-body">
                            Are you sure you want to delete the comparison?
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" className="btn btn-danger" onClick={() => handleDelete()} data-bs-dismiss="modal" data-testid={'modalDeleteBtn'}>Delete</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
