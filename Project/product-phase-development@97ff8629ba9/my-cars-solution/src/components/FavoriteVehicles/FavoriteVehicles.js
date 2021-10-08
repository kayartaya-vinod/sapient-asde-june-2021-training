/**
@Author Aravind M Krishnan - aravind.m.krishnan@publicissapient.com 
*/

import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getFavoriteVehicles } from "../../redux/actionCreators/favoriteVehicleActionCreator/favoriteVehicleActionCreator";
import VehicleGrid from "../VehicleGrid/VehicleGrid";
import PleaseWait from "../PleaseWait/PleaseWait";

export default function FavoriteVehicles() {
  const { vehicles, error } = useSelector(
    (store) => store.favoriteVehicleReducer
  );
  const dispatch = useDispatch();
  const [pleaseWait, setPleaseWait] = useState(true);

  useEffect(() => {
    (async function () {
      dispatch(await getFavoriteVehicles());
      setPleaseWait(false);
    })();
  }, [dispatch]);

  const errorMessage = pleaseWait ? (
    <PleaseWait />
  ) : (
    error || "No favorite vehicles."
  );

  return (
    <>
      <div className="col">
        <h2>Favorite Vehicles</h2>
        <hr />
        <br />
        <div>
          {vehicles.length > 0 ? (
            <VehicleGrid vehicles={vehicles} />
          ) : (
            <h4>{errorMessage}</h4>
          )}
        </div>
      </div>
    </>
  );
}
