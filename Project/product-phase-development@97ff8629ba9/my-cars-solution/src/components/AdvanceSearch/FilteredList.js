import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import VehicleGrid from "../VehicleGrid/VehicleGrid";
import { fetchFilteredData, resetFetchFilteredData } from "../../redux/actionCreators/filterActionCreator/filterActionCreator";
import { useLocation } from "react-router-dom";
import PleaseWait from "../PleaseWait/PleaseWait";
function FilteredList () {
  const location = useLocation();
  const [page, setPage] = useState(1);
  const [pleaseWait, setPleaseWait] = useState(false);
  const { vehicles, totalPages } = useSelector(
    (store) => store.fetchFilteredDataReducer
  );

  const dispatch = useDispatch();
  useEffect(() => {
    (async function () {
      if (location.search) {
        setPleaseWait(true);
        dispatch(await fetchFilteredData(location.search, page));
        setPleaseWait(false);
      }
    })();

    return (function () {
      dispatch(resetFetchFilteredData());
    })();
  }, [dispatch, page, location.search]);
  const handleClick = () => {
    if (totalPages !== 0) setPage(page + 1);
  };
  const displayMessage = pleaseWait ? (
    <PleaseWait />
  ) : (
    <div className="container">
      <h2 className="text-danger text-center">No Vehicles Found</h2>
    </div>
  );

  if (vehicles.length && vehicles) {
    return (
      <div className="container " data-testid="filtered-list-container">
        <VehicleGrid vehicles={vehicles} />
        <div data-testid="load-more">
          {totalPages && vehicles.length === 12 && (
            <button
              className="btn btn-primary"
              data-testid="load-btn"
              onClick={handleClick}
              style={{ marginTop: "5px" }}
            >
              Load More
            </button>
          )}
        </div>
      </div>
    );
  } else {
    return <>{location.search && displayMessage}</>;
  }
}

export default FilteredList;
