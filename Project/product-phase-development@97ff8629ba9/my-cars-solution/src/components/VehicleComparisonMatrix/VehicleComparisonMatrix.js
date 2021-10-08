import React from "react";
import { useEffect } from "react";
import { useLocation } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { ROLE_CUSTOMER, ROLE_DEALER } from "../../constants";
import { useHistory } from "react-router";
import VehicleImageColumn from "../VehicleColumn/VehicleImageColumn";
import VehicleDetailsColumn from "../VehicleColumn/VehicleDetailsColumn";
import IfLoggedIn from "../IfLoggedIn/IfLoggedIn";
import SaveComparisonMatrix from "../SaveComparisonMatrix/SaveComparisonMatrix";
import { fetchVehiclesForCompare } from "../../redux/actionCreators/vehicleObjectCompareCreator/vehicleObjectCompareCreator";
export default function VehicleComparisonMatrix() {
  const history = useHistory();
  const location = useLocation();
  const dispatch = useDispatch();
  const compareVehicleIdList = useSelector((store) => store.vehicleCompareListReducer); // vehicle Id
  const {vehicles}= useSelector((store)=> store.vehicleObjectCompareReducer); // vehicle Object
  
  let comparisonName = "";
  let query  = "";

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  useEffect(async () => {
    if(compareVehicleIdList.length===0){

        

        dispatch(await fetchVehiclesForCompare(location.search));
    }
    else{
      for (var index = 0; index < compareVehicleIdList.length; index++) {
        query += compareVehicleIdList[index];
        if (index !== compareVehicleIdList.length - 1) {
          query += ",";
        }
      }
      history.push("/vehicle-compare?ids=" + query);
      dispatch(await fetchVehiclesForCompare("?ids="+query));
    }
    
  }, [dispatch, compareVehicleIdList, location.search]);
  
  const getComparisonName = () =>{
    for (var index = 0; index < vehicles.length; index++) {
      comparisonName +=
        vehicles[index].brand +
        "-" +
        vehicles[index].model;
      if (index !== vehicles.length - 1) {
        comparisonName += " vs ";
      }
    }
    return (
      <h3>
        <u>{comparisonName}</u>
        <IfLoggedIn role={ROLE_DEALER}>
          <SaveComparisonMatrix />
        </IfLoggedIn>
        <IfLoggedIn role={ROLE_CUSTOMER}>
          <SaveComparisonMatrix />
        </IfLoggedIn>
      </h3>
    );
  }

  const vehicleImageList = () => {
    return vehicles.map((vehicle) => (
      <div className="col-6 col-md-3 col-xs-6" key={vehicle.id}>
        <VehicleImageColumn vehicle={vehicle} />
      </div>
    ));
  };

  const vehicleDetailsList = () => {
    return vehicles.map((vehicle) => (
      <div className="col-6 col-md-3 col-xs-6 border-end " key={vehicle.id}>
        <VehicleDetailsColumn vehicle={vehicle} />
      </div>
    ));
  };
  const temp = location.search;
  return (
    <div>
      { (temp)==="?ids=" && vehicles.length === 0 && history.push("/")}
      {vehicles.length !== 0 && (
        <>
          <div className="row text-center">{getComparisonName()}</div>
          <br />
          <div className="row">
            <div className="col-2"></div>
            <div className="col-10">
              <div className="row">{vehicleImageList()}</div>
            </div>
          </div>
          <hr />
          <div className="row">
            <div className="col-2 border-end text-left ">
              <table className="table table-borderless">
                <tbody className="text-muted ">
                  <tr>
                    <td className="h5 text-dark">Basic Information</td>
                  </tr>
                  <tr>
                    <td>Brand Name</td>
                  </tr>
                  <tr>
                    <td>On Road Price</td>
                  </tr>
                  <tr>
                    <td>Vehicle Type</td>
                  </tr>
                  <tr>
                    <td>Year</td>
                  </tr>
                  <tr>
                    <td>Feul Type</td>
                  </tr>
                  <tr>
                    <td>Air Condition</td>
                  </tr>
                </tbody>
              </table>
              <table className="table table-borderless">
                <tbody className="text-muted ">
                  <tr>
                    <td className="h5 text-dark">Performance</td>
                  </tr>
                  <tr>
                    <td>Top Speed</td>
                  </tr>
                  <tr>
                    <td>Milegae</td>
                  </tr>
                  <tr>
                    <td>Tank Capacity</td>
                  </tr>
                  <tr>
                    <td>Wheel Speed</td>
                  </tr>
                  <tr>
                    <td>Ignition Time</td>
                  </tr>
                  <tr>
                    <td>Acceleration</td>
                  </tr>
                  <tr>
                    <td>Power Torque</td>
                  </tr>
                  <tr>
                    <td>Battery Level</td>
                  </tr>
                </tbody>
              </table>
              <table className="table table-borderless">
                <tbody className="text-muted ">
                  <tr>
                    <td className="h5 text-dark">Safety</td>
                  </tr>
                  <tr>
                    <td>Theft Alarm</td>
                  </tr>
                  <tr>
                    <td>Airbag Count</td>
                  </tr>
                  <tr>
                    <td>Night Mode</td>
                  </tr>
                  <tr>
                    <td>Child Lock</td>
                  </tr>
                  <tr>
                    <td>Malfunction Indicator</td>
                  </tr>
                  <tr>
                    <td>ABS</td>
                  </tr>
                </tbody>
              </table>
              <table className="table table-borderless">
                <tbody className="text-muted ">
                  <tr>
                    <td className="h5 text-dark">Interface</td>
                  </tr>
                  <tr>
                    <td>Media Interface</td>
                  </tr>
                  <tr>
                    <td>Trip Meter</td>
                  </tr>
                  <tr>
                    <td>Gear Type</td>
                  </tr>
                  <tr>
                    <td>Steering Type</td>
                  </tr>
                  <tr>
                    <td>Horn</td>
                  </tr>
                  <tr>
                    <td>Wheel Radius</td>
                  </tr>
                  <tr>
                    <td>Odometer</td>
                  </tr>
                  <tr>
                    <td>Mirror</td>
                  </tr>
                  <tr>
                    <td>Washer Fluid</td>
                  </tr>
                  <tr>
                    <td>Language Configuration</td>
                  </tr>
                  <tr>
                    <td>Chime</td>
                  </tr>
                </tbody>
              </table>
              <table className="table table-borderless">
                <tbody className="text-muted ">
                  <tr>
                    <td className="h5 text-dark">Light</td>
                  </tr>
                  <tr>
                    <td>Fog</td>
                  </tr>
                  <tr>
                    <td>Hazard</td>
                  </tr>
                  <tr>
                    <td>Parking</td>
                  </tr>
                  <tr>
                    <td>Dynamic Highbeam</td>
                  </tr>
                  <tr>
                    <td>Automatic Headlights</td>
                  </tr>
                </tbody>
              </table>
              <table className="table table-borderless">
                <tbody className="text-muted ">
                  <tr>
                    <td className="h5 text-dark">Climate</td>
                  </tr>
                  <tr>
                    <td>Climate Control</td>
                  </tr>
                  <tr>
                    <td>Sunroof</td>
                  </tr>
                  <tr>
                    <td>Defrost</td>
                  </tr>
                </tbody>
              </table>
            </div>
            <div className="col-10">
              <div className="row">{vehicleDetailsList()}</div>
            </div>
          </div>
        </>
      )}
    </div>
  );
}
