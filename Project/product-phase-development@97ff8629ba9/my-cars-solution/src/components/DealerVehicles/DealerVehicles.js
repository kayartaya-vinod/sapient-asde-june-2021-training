/**
@author Abhishek Singh abhishek.singh8@publicisssapient.com
*/

import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from 'react-redux';
import { useLocation } from "react-router-dom";
import { getDealerVehicles, resetDealerVehicles } from "../../redux/actionCreators/dealerVehicleActionCreator/dealerVehicleActionCreator";
import PleaseWait from "../PleaseWait/PleaseWait";
import PreviousNext from "../PreviousNext/PreviousNext";
import DealerVehicleHeader from "./DealerVehicleHeader";
import DealerVehicleListItem from "./DealerVehicleListItem";



function useQuery () {
  return new URLSearchParams(useLocation().search);
}

export default function DealerVehicles () {
  const { vehicles, filteredVehicles, isFirst, isLast } = useSelector((store) => store.dealerVehicleReducer);
  const [pleaseWait, setPleaseWait] = useState(true);
  const dispatch = useDispatch();
  const page = useQuery().get("page") || 1;
  const pathname = "/dealer/dashboard/";

  useEffect(() => {
    (async function () {
      setPleaseWait(true);

      dispatch(await getDealerVehicles(page));
      setPleaseWait(false);
    })();

    return (async function () {
      dispatch(resetDealerVehicles());
    });
  }, [dispatch, page]);

  const vehicleIds = filteredVehicles.map(v => v.id);

  const vehicleList = filteredVehicles.map(v => (
    <DealerVehicleListItem key={v.id} vehicle={v} />
  ));

  const displayMessage = pleaseWait ? <PleaseWait /> : <div className="container"><h2>No Vehicles Found</h2></div>;

  return (
    <>
      <h2>My Vehicles</h2>
      <hr />
      {vehicles.length > 0 && <DealerVehicleHeader vehicleIds={vehicleIds} vehicles={vehicles} />}

      {vehicleList.length === 0 && displayMessage}

      <div className="list-group">
        {vehicleList.length > 0 && vehicleList}
      </div>

      {vehicles.length > 0 && <PreviousNext isFirst={isFirst} isLast={isLast} page={page} pathname={pathname} />}
    </>
  );
}
