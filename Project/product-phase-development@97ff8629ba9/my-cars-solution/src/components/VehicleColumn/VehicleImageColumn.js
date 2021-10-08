import "./VehicleImageColumn.css"
import React from 'react';
import { useLocation } from "react-router-dom";
import { useDispatch, useSelector } from 'react-redux';
import { useHistory } from 'react-router';
import { removeFromCompare } from '../../redux/actionCreators/vehicleCompareCreator/vehicleCompareCreator';
import { addDefaultSrc } from '../../utils/utils';
export default function VehicleImageColumn({ vehicle }) {
  const compareVehicleIdList = useSelector(
    (store) => store.vehicleCompareListReducer
  ); // vehicle Id

  const dispatch = useDispatch();
  const history = useHistory();
  const location  = useLocation();
  var vehicleName = vehicle.brand + " " + vehicle.model;
  const handleClick = async () => {
    if(compareVehicleIdList.length===0){
      let currLocation = location.search;
      currLocation = currLocation.split("=")[1].split(",");
      
      currLocation.splice(currLocation.indexOf(vehicle.id),1);
      let query = "";
      for (var index = 0; index < currLocation.length; index++) {
        query += currLocation[index];
        if (index !== currLocation.length - 1) {
          query += ",";
        }
      }
      dispatch(await fetchVehiclesForCompare(query));
      history.push("/vehicle-compare?ids=" + query);

    }
    else{
      dispatch(removeFromCompare(vehicle.id));
      if(compareVehicleIdList.length===1){
        history.push("/vehicle-compare?ids=");
      }
    }
  };
  return (
    <div>
      <div className="card img-wrapper rounded ">
        <img
          onClick={() => history.push("/vehicles/" + vehicle.id)}
          src={vehicle.pictureUrls[0]}
          className="card-img-top img-fluid rounded embed-responsive hover-zoom"
          title={vehicleName}
          data-testid="img-button"
          alt={vehicle.brand}
          onError={addDefaultSrc}
          style = {{cursor:"pointer", width : "100%", height:"170px"}}
        />
        <button
          className="btn btn-outline-danger"
          data-testid="remove"
          onClick={handleClick}
        >
          Remove
        </button>
      </div>
    </div>
  );
}
