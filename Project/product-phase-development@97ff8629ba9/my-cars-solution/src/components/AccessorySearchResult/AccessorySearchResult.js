/**
 @Author Aditya Gheewala aditya.gheewala@publicissapient.com
 */
import React, { useEffect, useState } from "react";
import { fetchSearchAccessories } from "../../redux/actionCreators/searchActionCreator/searchActionCreator";
import AccessoryGrid from "../AccessoryGrid/AccessoryGrid";
import { useLocation } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import * as qs from "query-string";
import SearchBar from "../SearchBar/SearchBar";
import PleaseWait from "../PleaseWait/PleaseWait";

export default function AccessorySearchResult () {
  const { accessories } = useSelector((store) => store.searchReducer);
  const [page, setPage] = useState(1);
  const [pleaseWait, setPleaseWait] = useState(true);

  const location = useLocation();
  const dispatch = useDispatch();

  const handleClick = async () => {
    setPage(page + 1);
  };


  useEffect(() => {
    (async () => {
      if (page === 1) {
        setPleaseWait(true);
      }
      const qp = qs.parse(location.search);
      dispatch(await fetchSearchAccessories(qp.q || "", page));
      setPleaseWait(false);
    })();
  }, [location, dispatch, page]);



  const errorMessage = pleaseWait ? <PleaseWait /> : (
    <h4
      style={{ textAlign: "center" }}
      data-testid="noSearch"
    >
      No Accessories found!
    </h4>);


  return (
    <>
      <div
        className="container"
        style={{
          display: "flex",
          justifyContent: "center",
        }}
      >
        <div className="col-6">
          <SearchBar searchText={qs.parse(location.search).q} type={"accessories"} />
        </div>
      </div>
      <br />
      <h4 data-testid="searchresult" style={{ textAlign: "center" }}>
        Search results for "{qs.parse(location.search).q}" accessories
      </h4>
      <hr />

      {(accessories && accessories.length !== 0 && pleaseWait === false) ?
        (
          <div className="pb-5">
            <AccessoryGrid accessories={accessories} />
            <div data-testid="load-more" className="d-flex mt-3">
              {accessories.length >= 12 && accessories.length % 12 === 0 &&
                <button
                  className="btn btn-primary mx-auto"
                  data-testid="load-more-btn"
                  onClick={handleClick}
                  style={{ marginTop: "5px", marginBottom: "5vh" }}
                >
                  Load More
                </button>
              }
            </div>
          </div>
        ) : (
          <div className="p-2">
            {errorMessage}
          </div>
        )
      }

    </>
  );

}
