
/**
@Author Pritam Patel - pritam.patel@publicissapient.com
*/
import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { useParams } from "react-router";
import DisplayVehicleImageColumn from "../DisplayVehicleColumn/DisplayVehicleImageColumn";
import DisplayVehicleDetailsColumn from "../DisplayVehicleColumn/DisplayVehicleDetailsColumn";
import {fetchComparisonById} from "../../redux/actionCreators/displayVehicleComparisonCreator/displayVehicleComparisonCreator";

export default function DisplayVehicleComparisonMatrix() {
  
  let { id } = useParams();
 
  var comparison = useSelector((store) => store.displayVehicleComparisonReducer);
  const dispatch = useDispatch();
  useEffect(() => {
    (async function () {
      
      dispatch(await fetchComparisonById(id));
    })();
  }, [id, dispatch]);

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  
    const compareList = comparison.vehicleIds;
    
  var comparisonName = comparison.comparisonMatrixName;

  

  const vehicleImageList = () => {
    return compareList.map((vehicleId) => (
      <div className="col-3" key={vehicleId}>
        <DisplayVehicleImageColumn vehicleId={vehicleId} />
      </div>
    ));
  };

  const vehicleDetailsList = () => {
    return compareList.map((vehicleId) => (
      <div className="col-3 border-end " key={vehicleId}>
        <DisplayVehicleDetailsColumn vehicleId={vehicleId} />
      </div>
    ));
  };

  return (
    <div>
      <div className="container">
        <div className="row">
          <div className="col-12 text-center">
            <h3>
              <u>{comparisonName}</u>
            </h3>
            <br />
          </div>
        </div>
        <div className="row">
          <div className="col-2"></div>
          <div className="col-10">
            <div className="row">{vehicleImageList()}</div>
          </div>
        </div>
      </div>
      <hr />
      <div className="row">
        <div className="col-2 border-end text-left ">
          <table className="table table-borderless">
            <tbody>
              <tr>
                <h5>Basic Information</h5>
              </tr>
              <div className="text-muted ">
                <tr>Brand Name</tr>
                <br />
                <tr>On Road Price</tr>
                <br />
                <tr>Vehicle Type</tr>
                <br />
                <tr>Year</tr>
                <br />
                <tr>Feul Type</tr>
                <br />
                <tr>Air Condition</tr>
              </div>
            </tbody>
          </table>
          <table className="table table-borderless">
            <tbody>
              <tr>
                <h5>Performance</h5>
              </tr>
              <div className="text-muted ">
                <tr>Top Speed</tr>
                <br />
                <tr>Milegae</tr>
                <br />
                <tr>Tank Capacity</tr>
                <br />
                <tr>Wheel Speed</tr>
                <br />
                <tr>Ignition Time</tr>
                <br />
                <tr>Acceleration</tr>
                <br />
                <tr>Power Torque</tr>
                <br />
                <tr>Battery Level</tr>
              </div>
            </tbody>
          </table>
          <table className="table table-borderless">
            <tbody>
              <tr>
                <h5>Safety</h5>
              </tr>
              <div className="text-muted ">
                <tr>Theft Alarm</tr>
                <br />
                <tr>Airbag Count</tr>
                <br />
                <tr>Night Mode</tr>
                <br />
                <tr>Child Lock</tr>
                <br />
                <tr>Malfunction Indicator</tr>
                <br />
                <tr>ABS</tr>
              </div>
            </tbody>
          </table>
          <table className="table table-borderless">
            <tbody>
              <tr>
                <h5>Interface</h5>
              </tr>
              <div className="text-muted ">
                <tr>Media Interface</tr>
                <br />
                <tr>Trip Meter</tr>
                <br />
                <tr>Gear Type</tr>
                <br />
                <tr>Steering Type</tr>
                <br />
                <tr>Horn</tr>
                <br />
                <tr>Wheel Radius</tr>
                <br />
                <tr>Odometer</tr>
                <br />
                <tr>Mirror</tr>
                <br />
                <tr>Washer Fluid</tr>
                <br />
                <tr>Language Configuration</tr>
                <br />
                <tr>Chime</tr>
              </div>
            </tbody>
          </table>
          <table className="table table-borderless">
            <tbody>
              <tr>
                <h5>Light</h5>
              </tr>
              <div className="text-muted ">
                <tr>Fog</tr>
                <br />
                <tr>Hazard</tr>
                <br />
                <tr>Parking</tr>
                <br />
                <tr>Dynamic Highbeam</tr>
                <br />
                <tr>Automatic Headlights</tr>
              </div>
            </tbody>
          </table>
          <table className="table table-borderless">
            <tbody>
              <tr>
                <h5>Climate</h5>
              </tr>
              <div className="text-muted ">
                <tr>Climate Control</tr>
                <br />
                <tr>Sunroof</tr>
                <br />
                <tr>Defrost</tr>
              </div>
            </tbody>
          </table>
        </div>
        <div className="col-10">
          <div className="row">{vehicleDetailsList()}</div>
        </div>
      </div>
    </div>
  );
}
