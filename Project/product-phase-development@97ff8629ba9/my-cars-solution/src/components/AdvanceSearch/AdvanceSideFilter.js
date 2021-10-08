import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getFilterAttributes } from "../../redux/actionCreators/vehicleFilterAttributeActionCreator/vehicleFilterAttributeActionCreator";
import VehicleSearchAttribute from "./VehicleSearchAttribute";
import { useHistory } from "react-router";
import { fetchFilteredData } from "../../redux/actionCreators/filterActionCreator/filterActionCreator";
const createQuery = (query, values) => {
  values.forEach((v) => {
    query = query.replace("{}", v);
  });

  return query;
};


function AdvanceSideFilter () {
  const searchAttributes = useSelector(
    (store) => store.vehicleFilterAttributeReducer
  );

  const dispatch = useDispatch();

  useEffect(() => {
    (async function () {
      dispatch(await getFilterAttributes());
    })();
  }, [dispatch]);

  const history = useHistory();

  const handleSubmit = async () => {

    let queryList = [];
    searchAttributes.forEach((attr) => {
      let { query, queryField } = attr;
      if (typeof queryField === "undefined") {
        queryField = [""];
      }
      if (

        queryField.length > 1 ||
        (queryField.length === 1 && queryField[0] !== "")
      ) {
        queryList.push(createQuery(query, queryField));
      }
    });

    queryList = queryList.join("&");
    let q = "?" + queryList;
    dispatch(await fetchFilteredData(q));
    history.push(history.location.pathname + q);

  };
  const searchAttributesList = searchAttributes.map(
    (searchAttribute, index) => (
      <VehicleSearchAttribute
        searchAttribute={searchAttribute}
        keyIndex={index}
        key={index}
      />
    )
  );

  return (
    <div className="container" data-testid="main-container">
      <div className="text-center">
        <h3 data-testid="filter-header">Select Filters</h3>
      </div>
      <hr />

      <div className="accordion w-100">
        {searchAttributesList}
      </div>
      <br />
      <button
        data-testid="btn-filter"
        className="btn btn-success w-100"
        onClick={handleSubmit}
      >
        FILTER
      </button>
      <div className="row">
      </div>
      <br />
    </div>
  );
}

export default AdvanceSideFilter;
