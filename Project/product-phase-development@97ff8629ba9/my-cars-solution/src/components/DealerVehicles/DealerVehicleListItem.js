/**
@author Abhishek Singh abhishek.singh8@publicisssapient.com
*/

import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { useHistory } from "react-router";
import {
  removeDealerVehicles,
  selectDealerVehicles,
} from "../../redux/actionCreators/dealerVehicleActionCreator/dealerVehicleActionCreator";
import { addDefaultSrc, truncate } from "../../utils/utils";

const DealerVehicleListItem = ({ vehicle }) => {
  const { selectedVehicles } = useSelector(
    (store) => store.dealerVehicleReducer
  );
  const dispatch = useDispatch();

  const history = useHistory();

  const [checked, setChecked] = useState(selectedVehicles.includes(vehicle.id));

  const handleChange = (e) => {
    if (e.target.checked === true) {
      dispatch(selectDealerVehicles([vehicle.id]));
      setChecked(true);
    } else {
      dispatch(removeDealerVehicles([vehicle.id]));
      setChecked(false);
    }
  };

  useEffect(() => {
    setChecked(selectedVehicles.includes(vehicle.id));
  }, [selectedVehicles, vehicle.id]);

  return (
    <div className="list-group-item">
      <div className="row g-0">
        <div className="col-11">
          <div className="d-flex w-100 justify-content-between">
            <input
              className="form-check-input me-2"
              type="checkbox"
              value=""
              aria-label="Select"
              checked={checked}
              onChange={handleChange}
              data-testid="checkbox"
              style={{ cursor: "pointer" }}
            />
            <h5
              className="mb-1 me-auto"
              style={{ cursor: "pointer" }}
              onClick={() => {
                history.push(`/vehicles/${vehicle.id}`);
              }}
            >{`${vehicle.brand} ${vehicle.model} ${vehicle.vehicleType}`}</h5>
          </div>
          <div className="d-flex flex-column">
            <div
              style={{ cursor: "pointer" }}
              onClick={() => {
                history.push(`/vehicles/${vehicle.id}`);
              }}
            >
              <p className="mb-1">{truncate(vehicle.description, 90)}</p>
              <small>{`Price: ${vehicle.price} INR`}</small>
            </div>
            <button
              type="button"
              className="btn btn-link btn-sm text-start p-0 mx-0 mt-2 mb-0"
              onClick={() => {
                history.push(`/dealer/edit-vehicle-details/${vehicle.id}`);
              }}
            >
              Edit Details
            </button>
          </div>
        </div>
        <div
          className="col-1 my-auto"
          style={{ cursor: "pointer" }}
          onClick={() => {
            history.push(`/vehicles/${vehicle.id}`);
          }}
        >
          {vehicle.pictureUrls !== null && (
            <img
              src={vehicle.pictureUrls[0]}
              className="img-thumbnail p-0"
              alt={vehicle.model}
              style={{ width: "100px" }}
              data-testid="vehicle-img"
              onError={addDefaultSrc}
            />
          )}
        </div>
      </div>
    </div>
  );
};

export default DealerVehicleListItem;
