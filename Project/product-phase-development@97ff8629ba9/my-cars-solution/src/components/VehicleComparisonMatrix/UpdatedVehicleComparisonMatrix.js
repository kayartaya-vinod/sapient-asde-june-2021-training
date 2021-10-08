/**
@Author Pritam Patel - pritam.Patel@publicissapient.com 
*/

import React, { useEffect, useState } from "react";
import "./UpdatedVehicleComparisonMatrix.css";
import { useLocation } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { BsCheck, BsX } from "react-icons/bs";
import { useHistory } from "react-router";
import { capitalizeFirstLetter, splitCamelCase } from "../../utils/utils";
import { removeFromCompare } from "../../redux/actionCreators/vehicleCompareCreator/vehicleCompareCreator";
import { fetchVehiclesForCompare } from "../../redux/actionCreators/vehicleObjectCompareCreator/vehicleObjectCompareCreator";
import IfLoggedIn from "../IfLoggedIn/IfLoggedIn";
import SaveComparisonMatrix from "../SaveComparisonMatrix/SaveComparisonMatrix";
import { ROLE_CUSTOMER, ROLE_DEALER } from "../../constants";
import AddVehicleToComparisonMatrix from "../AddVehicleToComparisonMatrix/AddVehicleToComparisonMatrix";
import CompareButton from "../AddToCompare/CompareButton";

function Row({ content }) {
  const keys = Object.keys(content);
  return (
    <>
      {keys.map((k) => (
        <tr>
          <td>{splitCamelCase(capitalizeFirstLetter(k))}</td>
          {content[k].map((d) => {
            if (d === null) {
              return <td>--</td>;
            }
            if (typeof d !== "boolean") {
              return <td>{d}</td>;
            }
            return (
              <td>
                {d ? (
                  <BsCheck size="2em" color="green" />
                ) : (
                  <BsX size="2em" color="red" />
                )}
              </td>
            );
          })}
        </tr>
      ))}
    </>
  );
}
const design={top: "10px",width:"240px", height: "240px",display: "flex",
  alignItems: "center",justifyContent: "center"}
function UpdatedVehicleComparisonMatrix() {
  const [keys, setKeys] = useState([]);

  const history = useHistory();
  const location = useLocation();
  const dispatch = useDispatch();
  const compareVehicleIdList = useSelector(
    (store) => store.vehicleCompareListReducer
  ); // vehicle Id

  const { content, images, ids } = useSelector(
    (store) => store.vehicleObjectCompareReducer
  );

  let comparisonName = "";

  useEffect(() => {
    window.scrollTo(0, 0);

    setKeys(Object.keys(content));
  }, [content]);

  useEffect(() => {
    (async () => {
      if (compareVehicleIdList.length === 0) {
        dispatch(await fetchVehiclesForCompare(location.search));
      } else {
        let query = "";

        for (let index = 0; index < compareVehicleIdList.length; index++) {
          query += compareVehicleIdList[index];
          if (index !== compareVehicleIdList.length - 1) {
            query += ",";
          }
        }
        history.push("/vehicle-compare?ids=" + query);
        dispatch(await fetchVehiclesForCompare("?ids=" + query));
      }
    })();
  }, [dispatch, compareVehicleIdList, location.search, history]);

  const getComparisonName = () => {
    if (keys.length !== 0) {
      let brandsArray = content[keys[0]].brand;
      let modelsArray = content[keys[0]].model;

      for (var index = 0; index < brandsArray.length; index++) {
        comparisonName +=
          brandsArray[index] + "-" + modelsArray[index];
        if (index !== brandsArray.length - 1) {
          comparisonName += " vs ";
        }
      }

      return (
        <h3>
          <u>{comparisonName}</u>
          <IfLoggedIn role={ROLE_DEALER}>
            <SaveComparisonMatrix compareName={comparisonName} />
          </IfLoggedIn>
          <IfLoggedIn role={ROLE_CUSTOMER}>
            <SaveComparisonMatrix compareName={comparisonName} />
          </IfLoggedIn>
        </h3>
      );
    }
  };

  const handleClick = async (idx) => {
    let currVehicleId = ids[idx];
    if (compareVehicleIdList.length === 0) {
      let currLocation = location.search;
      currLocation = currLocation.split("=")[1].split(",");

      currLocation.splice(currLocation.indexOf(currVehicleId), 1);

      let query2 = "";
      for (var index = 0; index < currLocation.length; index++) {
        query2 += currLocation[index];
        if (index !== currLocation.length - 1) {
          query2 += ",";
        }
      }
      dispatch(await fetchVehiclesForCompare("?ids=" + query2));
      history.push("/vehicle-compare?ids=" + query2);
    } else {
      dispatch(removeFromCompare(ids[idx]));
      if (compareVehicleIdList.length === 1) {
        history.push("/vehicle-compare?ids=");
      }
    }
  };

  const temp = location.search;
  return (
    <>
      {temp === "?ids=" &&
        compareVehicleIdList.length === 0 &&
        history.push("/")}

      <div className="row text-center">{getComparisonName()}</div>

      <div className="container">
        <CompareButton />
        {keys.length ? (
          <>
            <table className="table table-nonfluid">
              <tbody>
                <tr>
                  <td></td>
                  {images.map((img, idx) => (
                    <td key={idx}>
                      <div className="card img-wrapper rounded ">
                        <img
                          onClick={() => history.push("/vehicles/" + ids[idx])}
                          src={img}
                          className="img-thumbnail"
                          alt="asd"
                          style={{
                            height: "200px",
                          }}
                        />
                        <button
                          className="btn btn-outline-danger"
                          data-testid={`remove${idx}`}
                          onClick={() => handleClick(idx)}
                        >
                          Remove
                        </button>
                      </div>
                    </td>
                  ))}
                  {images.length < 4 ? (
                    <div className="card img-wrapper rounded " style={design}>
                      <AddVehicleToComparisonMatrix />
                    </div>
                  ) : (
                    <></>
                  )}
                </tr>
                {keys.map((k) => (
                  <>
                    <tr key={k}>
                      <th colSpan={images.length + 1}>
                        {splitCamelCase(capitalizeFirstLetter(k))} :{/* {k} */}
                      </th>
                    </tr>
                    <Row content={content[k]} />
                  </>
                ))}
              </tbody>
            </table>
          </>
        ) : (
          <h3 className="text-center">
            Please wait while loading vehicle data...
          </h3>
        )}
      </div>
    </>
  );
}

export default UpdatedVehicleComparisonMatrix;
