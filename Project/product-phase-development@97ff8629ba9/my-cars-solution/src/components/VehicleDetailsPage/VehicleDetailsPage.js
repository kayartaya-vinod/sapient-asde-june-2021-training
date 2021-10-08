/**
@Author Sakshi Yadav - sakshi.yadav@publicissapient.com 
*/

import React, { useEffect, useRef, useState } from "react";
import { fetchVehicleById } from "../../redux/actionCreators/vehicleActionCreator/vehicleActionCreator";
import { useParams } from "react-router";
import { useDispatch, useSelector } from "react-redux";
import Review from "../Review/Review";
import Rating from "../Rating/Rating";
import { capitalizeFirstLetter } from "../../utils/utils";
import "./VehicleDetailsPage.css";
import { useHistory } from "react-router-dom";
import Thumbnail from "../Thumbnail/Thumbnail";
import IfLoggedIn from "./../IfLoggedIn/IfLoggedIn";
import VehicleFeedbacks from "../VehicleFeedbacks/VehicleFeedbacks";
import AverageRating from "../AverageRating/AverageRating";
import { ROLE_CUSTOMER } from "../../constants";
import { resetSingleVehicleState } from "../../redux/actionCreators/dealerUploadsActionCreator/dealerUploadsActionCreator";
import PleaseWait from "../PleaseWait/PleaseWait";
import AddRemoveFavorite from "../AddRemoveFavorite/AddRemoveFavorite";
import CompareButton from "../AddToCompare/CompareButton";
import AddToCompare from "../AddToCompare/AddToCompare";
export default function VehicleDetailsPage() {
  let { id } = useParams();
  var vehicle = useSelector((store) => store.singleVehicleReducer);
  const dispatch = useDispatch();
  const history = useHistory();
  const myRef = useRef(null);

  const [pagewait, setPagewait] = useState(false);

  useEffect(() => {
    (async function () {
      setPagewait(false);

      dispatch(await fetchVehicleById(id));
      setPagewait(true);
    })();
    return async function () {
      dispatch(resetSingleVehicleState());
    };
  }, [id, dispatch]);

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  useEffect(() => {
    (function () {
      if (vehicle.error !== undefined) {
        history.push("/vehicles");
      }
    })();
  }, [vehicle, history]);

  function check(val) {
    if (val && val === true) {
      return "Yes";
    } else if (val && val === false) {
      return "No";
    } else if (val) return val;
  }

  function check2(val, str) {
    if (val && val[str] === true) {
      return "Yes";
    } else if (val && val[str] === false) {
      return "No";
    }
  }

  const vehicles = (
    <div className="container">
      <CompareButton />
      <div className="w-100 d-flex">
        <h2
          data-testid="vehicle-details"
          style={{ fontWeight: "bold" }}
          className="d-inline me-auto"
        >
          Vehicle details
        </h2>
        <div className="pt-2">
          <AddToCompare vehicleId={vehicle.id} />
        </div>

        <div className="pt-2">
          <IfLoggedIn role={ROLE_CUSTOMER}>
            <AddRemoveFavorite vehicle={vehicle} />
          </IfLoggedIn>
        </div>
      </div>
      <hr />
      <div className=" row">
        <div className="col-xs-12 col-md-6">
          <Thumbnail />
        </div>
        <div className="col-xs-12 col-md-6">
          <div className="container">
            <br></br>
            <br></br>
            <div className="container-fluid">
              <div>
                <h3 className="p">
                  {check(vehicle.brand)} - {check(vehicle.model)}
                </h3>
              </div>
              <h6 className="p">
                Price - <span>&#8377; </span> {check(vehicle.price)}
              </h6>

              <div className="row ">
                <AverageRating vehicleId={id} />
              </div>
              <div className="row mt-1" style={{ textAlign: "center" }}>
                <p
                  style={{
                    textDecoration: "underline",
                    color: "blue",
                    cursor: "pointer",
                  }}
                  onClick={() => myRef.current.scrollIntoView()}
                >
                  View Reviews
                </p>
              </div>
              <br></br>
              <div className="row">
                <div className="col">
                  <table className="table  table-hover  table-striped  ">
                    <tbody>
                      <tr>
                        <td>Model</td>
                        <td className="q">
                          {capitalizeFirstLetter(check(vehicle.model))}
                        </td>
                      </tr>
                      <tr>
                        <td>Color</td>
                        <td className="q">
                          {capitalizeFirstLetter(check(vehicle.color))}
                        </td>
                      </tr>
                      <tr>
                        <td>Release year</td>
                        <td className="q">{vehicle.year}</td>
                      </tr>
                      <tr>
                        <td>Vehicle Type</td>
                        <td className="q">
                          {capitalizeFirstLetter(check(vehicle.vehicleType))}
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="container-fluid  ">
        <div className="row">
          <div className="col-xs-12 col-md-6">
            <h3 style={{ fontWeight: "bold" }}>Key Features</h3>
            <table className="table  table-hover  table-striped  ">
              <tbody>
                <tr>
                  <td>Transmission</td>
                  <td className="q">
                    {capitalizeFirstLetter(check(vehicle.transmissionGearType))}
                  </td>
                </tr>
                <tr>
                  <td>Steering</td>
                  <td className="q">
                    {capitalizeFirstLetter(check(vehicle.steeringWheelType))}
                  </td>
                </tr>
                <tr>
                  <td>Fuel Type</td>
                  <td className="q">
                    {vehicle.fuelType
                      ? vehicle.fuelType.map((x) => {
                          return capitalizeFirstLetter(x) + " ";
                        })
                      : vehicle.fuelType}
                  </td>
                </tr>
                <tr>
                  <td>Top Speed</td>
                  <td className="q">{check(vehicle.topSpeedLimit)}</td>
                </tr>
                <tr>
                  <td>Fuel Consumption</td>
                  <td className="q">{check(vehicle.unitsFuelConsumption)}</td>
                </tr>
              </tbody>
            </table>
          </div>
          <div className="col-xs-12 col-md-6">
            <h3 style={{ fontWeight: "bold" }}>Light</h3>
            <table className="table table-hover table-striped ">
              <tbody>
                <tr>
                  <td>Fog Light:</td>
                  <td data-testid="fog">{check2(vehicle["light"], "fog")}</td>
                </tr>
                <tr>
                  <td>Hazard:</td>
                  <td>{check2(vehicle["light"], "hazard")}</td>
                </tr>
                <tr>
                  <td>Parking Light:</td>
                  <td> {check2(vehicle["light"], "parking")}</td>
                </tr>
                <tr>
                  <td>Automatic Headlights:</td>
                  <td>{check2(vehicle["light"], "automaticHeadlights")}</td>
                </tr>
                <tr>
                  <td>Dynamic Head Beam:</td>
                  <td>{check2(vehicle["light"], "dynamicHighBeam")} </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div className="container-fluid ">
        <h3 style={{ fontWeight: "bold" }}>Key Specifications</h3>
        <div className="row">
          <div className="col-xs-12 col-md-6">
            <table className="table table-hover table-striped ">
              <tbody>
                <tr>
                  <td>ABS:</td>
                  <td className="q">{check(vehicle.isABS)}&nbsp;&nbsp;</td>
                </tr>
                <tr>
                  <td>Climate Control:</td>
                  <td data-testid="climateControl" className="q">
                    {check2(vehicle["climate"], "climateControl")}
                  </td>
                </tr>
                <tr>
                  <td>Defrost:</td>
                  <td className="q">{check2(vehicle["climate"], "defrost")}</td>
                </tr>
                <tr>
                  <td>Tank Capacity:</td>
                  <td className="q">{vehicle.tankCapacity}</td>
                </tr>
              </tbody>
            </table>
          </div>
          <div className="col-xs-12 col-md-6">
            <table className="table table-hover  table-striped ">
              <tbody>
                <tr>
                  <td>Accelaration:</td>
                  <td className="q">{check(vehicle.accelaration)}&nbsp;</td>
                </tr>
                <tr>
                  <td>Power Train Torque:</td>
                  <td className="q">{check(vehicle.powerTrainTorque)}</td>
                </tr>
                <tr>
                  <td>Ignition Time:</td>
                  <td className="q">{check(vehicle.ignitionTime)}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div className="container-fluid">
        <div className="row">
          <div className="col-xs-12 col-md-6">
            <h3 style={{ fontWeight: "bold" }}>Comfort</h3>
            <table className="table table-hover table-striped ">
              <tbody>
                <tr>
                  <td>Mirror Type:</td>
                  <td className="q">
                    {capitalizeFirstLetter(check(vehicle.mirror))}
                  </td>
                </tr>
                <tr>
                  <td>Night Mode:</td>
                  <td className="q">{check(vehicle.nightMode)}</td>
                </tr>
                <tr>
                  <td>Sunroof:</td>
                  <td data-testid="sunroof" className="q">
                    {check(vehicle.mirror)}
                  </td>
                </tr>
                <tr>
                  <td>Air Conditioning:</td>
                  <td className="q">{check(vehicle.airConditioning)}</td>
                </tr>
                <tr>
                  <td>Media Interface:</td>
                  <td className="q">
                    {capitalizeFirstLetter(check(vehicle.mediaInterface))}
                  </td>
                </tr>
              </tbody>
            </table>
          </div>

          <div className="col-xs-12 col-md-6">
            <h3 style={{ fontWeight: "bold" }}>Safety</h3>
            <table className="table table-hover table-striped ">
              <tbody>
                <tr>
                  <td>AirBags:</td>
                  <td>{vehicle.airBagCount}&nbsp;</td>
                </tr>
                <tr>
                  <td>Child Lock:</td>
                  <td>{check(vehicle.childSafetyLock)}&nbsp;</td>
                </tr>
                <tr>
                  <td>Malfunction Indicator:</td>
                  <td>{check(vehicle.malfunctionIndicator)}</td>
                </tr>
                <tr>
                  <td>Theft Alarm:</td>
                  <td>{check(vehicle.theftAlarm)}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div className="container-fluid">
        <h3 style={{ fontWeight: "bold" }} data-testid="more-specification">
          More Specifications
        </h3>
        <div className="row">
          <div className="col-xs-12 col-md-6">
            <table className="table table-hover table-striped ">
              <tbody>
                <tr>
                  <td>Trip Meter:</td>
                  <td className="q">
                    {capitalizeFirstLetter(check(vehicle.tripMeter))}
                  </td>
                </tr>
                <tr>
                  <td>Odometer:</td>
                  <td className="q">{check(vehicle.odometer)}</td>
                </tr>
                <tr>
                  <td>Washer Fluid:</td>
                  <td className="q">{check(vehicle.washerFluid)}</td>
                </tr>
                <tr>
                  <td>Chime:</td>
                  <td className="q">{check(vehicle.chime)}</td>
                </tr>
              </tbody>
            </table>
          </div>
          <div className="col-xs-12 col-md-6">
            <table className="table table-hover table-striped">
              <tbody>
                <tr>
                  <td>Horn:</td>
                  <td className="q">
                    {" "}
                    {capitalizeFirstLetter(check(vehicle.horn))}
                  </td>
                </tr>
                <tr>
                  <td>Wheel Radius:</td>
                  <td className="q">{check(vehicle.wheelRadius)}</td>
                </tr>
                <tr>
                  <td>Wheel Speed:</td>
                  <td className="q">{check(vehicle.wheelSpeed)}</td>
                </tr>
                <tr>
                  <td>Language Configuration:</td>
                  <td className="q">{check(vehicle.languageConfiguration)}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div className="container-fluid">
        <div className="row">
          <div className="col-sm-12 col-md-6 ps-2">
            <IfLoggedIn role={ROLE_CUSTOMER}>
              <Review vehicleId={id} />
            </IfLoggedIn>
          </div>
          <div className="col-sm-12 col-md-6">
            <IfLoggedIn role={ROLE_CUSTOMER}>
              <Rating vehicleId={id} />
            </IfLoggedIn>
          </div>
        </div>
      </div>
      <div ref={myRef}>
        <VehicleFeedbacks id={id} />
      </div>
    </div>
  );
  if (!pagewait) {
    return (
      <div data-testid="pagewait">
        <PleaseWait />
      </div>
    );
  }
  return { ...vehicles };
}
