import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router";
import { fetchVehiclesForCompare } from "../../redux/actionCreators/vehicleObjectCompareCreator/vehicleObjectCompareCreator";

export default function CompareButton() {
  const compareVehicleIdList = useSelector(
    (store) => store.vehicleCompareListReducer
  );
  const history = useHistory();
  const dispatch = useDispatch();

  const handleClick = async () => {
    let query = "";
    for (var index = 0; index < compareVehicleIdList.length; index++) {
      query += compareVehicleIdList[index];
      if (index !== compareVehicleIdList.length - 1) {
        query += ",";
      }
    }
    dispatch(await fetchVehiclesForCompare("?ids="+query));
    history.push("/vehicle-compare?ids=" + query);
  };

  return (
    <div>
      {compareVehicleIdList.length > 0 && (
        <button
          className="btn btn-primary"
          onClick={handleClick}
          data-testid="btn-compare"
          style={{
            position: "fixed ",
            zIndex: "1000",
            right: 100,
            bottom: 100,
          }}
        >
          Compare | {compareVehicleIdList.length}
        </button>
      )}
    </div>
  );
}
