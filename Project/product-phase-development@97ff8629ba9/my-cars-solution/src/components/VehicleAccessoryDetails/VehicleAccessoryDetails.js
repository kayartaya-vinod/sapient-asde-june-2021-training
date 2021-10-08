/**
@Author Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/

import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router";
import { fetchVehicleAccessoryById } from "../../redux/actionCreators/vehicleAccessoryActionCreator/vehicleAccessoryActionCreator";
import AccessoryThumbnail from "./AccessoryThumbnail/AccessoryThumbnail";
import "./VehicleAccessoryDetails.css";
import { capitalizeFirstLetter, splitCamelCase } from "../../utils/utils";

function VehicleAccessoryDetails() {
  const dispatch = useDispatch();

  const vehicleAccessory = useSelector(
    (store) => store.vehicleAccessoryReducer
  );

  const { id } = useParams();
  useEffect(() => {
    (async function () {
      dispatch(await fetchVehicleAccessoryById(id));
    })();
  }, [dispatch, id]);

  const keys = Object.keys(vehicleAccessory);
  const skip = ["id", "_id", "dealerId", "pictureUrls"];
  const temp = keys.filter((key) => !skip.includes(key));

  const out = temp.map((key, index) => (
    <tr key={key} data-testid={`row${index}`}>
      <td>{splitCamelCase(capitalizeFirstLetter(key))}</td>
      <td>{vehicleAccessory[key]}</td>
    </tr>
  ));

  return (
    <>
      <div className="row">
        <div className="col-xs-12 col-md-6 mb-5">
          <AccessoryThumbnail />
        </div>
        <div className="col-xs-12 col-md-6 details" id="scroll">
          <table className="table table-hover table-striped ">
            <tbody>{out}</tbody>
          </table>
        </div>
      </div>
    </>
  );
}

export default VehicleAccessoryDetails;
