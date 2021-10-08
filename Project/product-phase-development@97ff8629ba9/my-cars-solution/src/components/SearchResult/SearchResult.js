/**
 @Author Hrishant Raj hrishant.raj@publicissapient.com 
 */
import React, { useEffect, useState } from "react";
import { fetchSearch } from "../../redux/actionCreators/searchActionCreator/searchActionCreator";
import VehicleGrid from "../VehicleGrid/VehicleGrid";
import { useLocation } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import * as qs from "query-string";
import SearchBar from "../SearchBar/SearchBar";
import PleaseWait from "../PleaseWait/PleaseWait";
import CompareButton from "../AddToCompare/CompareButton";
export default function SearchResult() {
  const { vehicles } = useSelector((store) => store.searchReducer);
  const [page, setPage] = useState(1);
  const [pagewait, setPagewait] = useState(false);
  const location = useLocation();
  const dispatch = useDispatch();

  const handleClick = () => {
    setPage(page + 1);
  };

  useEffect(() => {
    return () => {
      setPagewait(false);
    };
  }, []);
  useEffect(() => {
    setPage(1);
  }, [location]);

  useEffect(() => {
    (async () => {
      const qp = qs.parse(location.search);
      if (page === 1) setPagewait(false);
      dispatch(await fetchSearch(qp.q || "", page));
      setPagewait(true);
    })();
  }, [location, dispatch, page]);

  if (!pagewait) {
    return (
      <div data-testid="pagewait">
        <PleaseWait />
      </div>
    );
  }

  if (vehicles && vehicles.length !== 0) {
    return (
      <>
        <div
          className="container"
          style={{
            display: "flex",
            justifyContent: "center",
          }}
        >
          <CompareButton />
          <div className="col-6">
            <SearchBar
              searchText={qs.parse(location.search).q}
              type={"vehicles"}
            />
          </div>
        </div>
        <h1 style={{ textAlign: "center" }} data-testid="searchresult">
          Your searched vehicles
        </h1>
        <hr />
        <VehicleGrid vehicles={vehicles} />
        <div data-testid="load-more" className="d-flex mt-3">
          {vehicles.length >= 12 && (
            <button
              className="btn btn-primary mx-auto"
              data-testid="load-more-btn"
              onClick={handleClick}
              style={{ marginTop: "5px", marginBottom: "60px" }}
            >
              Load More
            </button>
          )}
        </div>
      </>
    );
  }

  return (
    <>
      <CompareButton />
      <div
        className="container"
        style={{
          display: "flex",
          justifyContent: "center",
          paddingBottom: "10px",
        }}
      >
        <div className="col-6">
          <SearchBar
            searchText={qs.parse(location.search).q}
            type={"vehicles"}
          />
        </div>
      </div>
      <h1
        className="text-danger"
        style={{ textAlign: "center" }}
        data-testid="noSearch"
      >
        No Vehicles found!!!
      </h1>
      <hr />
    </>
  );
}
