/**
@author Abhishek Singh abhishek.singh8@publicisssapient.com
*/

import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import Fuse from "fuse.js";
import { BsTrash } from "react-icons/bs";
import { FiDownload } from "react-icons/fi";

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


import { deleteSelectedVehicles, downloadSelectedVehicles, filterDealerVehicles, getDealerVehicles, removeDealerVehicles, selectDealerVehicles } from '../../redux/actionCreators/dealerVehicleActionCreator/dealerVehicleActionCreator';

const DealerVehicleHeader = ({ vehicleIds, vehicles }) => {
  const { selectedVehicles } = useSelector((store) => store.dealerVehicleReducer);
  const dispatch = useDispatch();

  const fuse = new Fuse(vehicles, { keys: ["brand", "vehicleType", "description", "model", "price"] });

  const [checked, setChecked] = useState(vehicleIds.length > 0 && vehicleIds.every(id => selectedVehicles.includes(id)));

  useEffect(() => {
    setChecked(vehicleIds.length > 0 && vehicleIds.every(id => selectedVehicles.includes(id)));
  }, [selectedVehicles, setChecked, vehicleIds]);


  const handleCheckBox = (e) => {
    if (e.target.checked === true) {
      const unselectedVehicles = vehicleIds.filter(id => !(selectedVehicles.includes(id)));

      dispatch(selectDealerVehicles(unselectedVehicles));
      setChecked(true);
    } else {
      dispatch(removeDealerVehicles(vehicleIds));
      setChecked(false);
    }
  };

  const handleSearch = (e) => {
    e.preventDefault();
    const value = e.target.value;
    if (value.length === 0) {
      dispatch(filterDealerVehicles(vehicles));
    } else {
      const filteredVehicles = fuse.search(value).map(v => v.item);
      dispatch(filterDealerVehicles(filteredVehicles));
    }
  };

  const handleDownload = async () => {
    const resp = dispatch(await downloadSelectedVehicles(selectedVehicles));
    if (resp.payload && resp.payload.error) {
      toast.error(resp.payload.error, {
        position: "bottom-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });
    }
  };


  const handleDelete = async () => {
    const resp = dispatch(await deleteSelectedVehicles(selectedVehicles));

    if (resp.payload && resp.payload.error) {
      toast.error(resp.payload.error, {
        position: "bottom-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });
    } else {
      dispatch(await getDealerVehicles());
    }
  };

  return (
    <>
      <ul className="nav mb-3 p-2">

        <li className="nav-item">
          <button className="btn nav-link" style={{ boxShadow: 'none', cursor: 'default' }}>
            <input className="form-check-input" type="checkbox" value="" aria-label="Select All" style={{ cursor: "pointer" }} checked={checked} data-testid="checkbox" onChange={handleCheckBox} />
          </button>
        </li>
        {
          (selectedVehicles.length > 0) && (
            <>
              <li className="nav-item" >
                <button className="btn nav-link text-danger" style={{ boxShadow: 'none', cursor: 'default' }}>
                  <BsTrash data-bs-toggle="modal" data-bs-target="#alertModal" style={{ cursor: "pointer" }} data-testid="delete-btn" />
                </button>
              </li>

              <li className="nav-item">
                <button className="btn nav-link" style={{ boxShadow: 'none', cursor: 'default' }}>
                  <FiDownload style={{ cursor: "pointer" }} onClick={handleDownload} data-testid="download-btn" />
                </button>
              </li>
            </>
          )
        }

        <li className="nav-item ms-auto w-75">
          <input className="form-control me-sm-2 h-100" type="text" placeholder="Filter" onChange={handleSearch} data-testid="search-box" />
        </li>
      </ul>

      <ToastContainer
        position="bottom-right"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        data-testid="toast"
      />

      <div className="modal fade" id="alertModal" tabIndex="-1" aria-labelledby="alert" aria-hidden="true">
        <div className="modal-dialog modal-dialog-centered" data-testid="alert-modal">
          <div className="modal-content">
            <div className="modal-body">
              Are you sure you want to delete the vehicles?
            </div>
            <div className="modal-footer">
              <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              <button type="button" className="btn btn-danger" onClick={handleDelete} data-bs-dismiss="modal">Delete</button>
            </div>
          </div>
        </div>
      </div>
    </>

  );
};

export default DealerVehicleHeader;
