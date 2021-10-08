/**
@Author Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/
import React from "react";
import { useHistory } from "react-router";
import AddRemoveFavorite from "../AddRemoveFavorite/AddRemoveFavorite";
import AddToCompare from "../AddToCompare/AddToCompare";
import IfLoggedIn from "./../IfLoggedIn/IfLoggedIn";
import { ROLE_CUSTOMER } from "../../constants";
import "./VehicleCard.css";
import { addDefaultSrc, truncate } from "../../utils/utils";
function VehicleCard ({ vehicle = {} }) {
  const history = useHistory();

  return (
    <div
      className="card vehicle-card mx-auto"
    >
      <img
        onClick={() => history.push("/vehicles/" + vehicle.id)}
        className="card-img-top"
        src={vehicle.pictureUrls[0]}
        alt={vehicle.brand}
        data-testid="routing-image"
        onError={addDefaultSrc}
      />
      <div className="card-body">
        <div className="d-flex justify-content-center">
          <h5 className="card-title fw-bold">
            {vehicle.brand} {vehicle.model}
          </h5>
          <IfLoggedIn role={ROLE_CUSTOMER}>
            <AddRemoveFavorite vehicle={vehicle} />
          </IfLoggedIn>
        </div>
        <p className="card-text">{truncate(vehicle.description)}</p>
        <p className="card-text">
          <small className="text-muted">
            Rs {parseFloat(vehicle.price).toFixed(2)}
          </small>
        </p>
        <AddToCompare vehicleId={vehicle.id} />
      </div>
    </div>
  );
}

export default VehicleCard;
