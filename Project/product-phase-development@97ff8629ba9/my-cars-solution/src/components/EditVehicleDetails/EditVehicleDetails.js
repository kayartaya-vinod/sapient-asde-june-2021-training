/**
@Author Sumitesh Naithani - sumitesh.naithani@publicissapient.com 
*/
import React, { useState, useEffect } from "react";
import {
  fetchVehicleById,
  updateVehicleDetails,
} from "../../redux/actionCreators/editVehicleDetailsActionCreator/editVehicleDetailsActionCreator";
import { useParams, useHistory } from "react-router";
import { capitalizeFirstLetter } from "../../utils/utils";
import "./EditVehicleDetails.css";
import sanitize from "mongo-sanitize";
import PictureUrlsField from "../Inputs/TextAndNumberInput/PictureUrlsField";
import PleaseWait from "../PleaseWait/PleaseWait";

let data = {
  payload: {
    pictureUrls: [],
    fuelType: [],
    light: {
      fog: "",
      hazard: "",
      parking: "",
      dynamicHighBeam: "",
      automaticHeadlights: "",
    },
    climate: {
      climateControl: "",
      sunroof: "",
      defrost: "",
    },
  },
};
export default function EditVehicleDetails() {
  let { id } = useParams();
  const [veh, setveh] = useState(0);
  const [message, setmessage] = useState("");
  const [messagef, setmessagef] = useState("");
  const history = useHistory();
  useEffect(() => {
    (async function () {
      data = await fetchVehicleById(sanitize(id));
      if (data.payload.error) {
        history.push("/dealer/dashboard");
      }
      setveh(data.payload);
    })();
  }, [id, history]);

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  function check(val) {
    if (val === undefined) return false;
    return val;
  }

  const handleChange = ({ target }) => {
    const v = { ...veh };
    v[target.name] = sanitize(target.value);
    setveh(v);
    setmessage("");
  };

  const handleRadio = ({ target }) => {
    const v = { ...veh };
    v[target.name] = !v[target.name];
    setveh(v);
    setmessage("");
  };

  const handleLight = ({ target }) => {
    const v = { ...veh };
    v["light"][target.name] = !v["light"][target.name];
    setveh(v);
    setmessage("");
  };

  const handleClimate = ({ target }) => {
    const v = { ...veh };
    v["climate"][target.name] = !v["climate"][target.name];
    setveh(v);
    setmessage("");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const resp = await updateVehicleDetails(veh);
    if (resp.payload.success) {
      setmessage("Updated successfully , redirecting to dashboard!");
      window.scrollTo(0, 0);
      setTimeout(() => {
        history.push("/dealer/dashboard");
      }, 2000);
    } else {
      setmessagef("Not updated successfully!");
      window.scrollTo(0, 0);
    }
  };

  const handleFuel = ({ target }) => {
    const v = { ...veh };
    const index = v["fuelType"].indexOf(target.name);
    if (index > -1) v["fuelType"].splice(index, 1);
    else v["fuelType"].push(sanitize(target.name));
    setveh(v);
    setmessage("");
  };

  const handlePictureUrls = (urls) => {
    const newVehicle = { ...veh };
    let pictures = [];
    for (let i = 0; i < 5; ++i) {
      urls["input-" + i] && pictures.push(urls["input-" + i]);
    }
    newVehicle["pictureUrls"] = pictures;
    setveh(newVehicle);
  };

  if (veh === 0) return <PleaseWait />;

  const vehicles = (
    <div>
      <div className="text-success">{message}</div>
      <div className="text-danger">{messagef}</div>
      <form onSubmit={handleSubmit}>
        <div className="container">
          <h2 data-testid="vehicle-details1" style={{ fontWeight: "bold" }}>
            Edit Vehicle details
          </h2>
          <hr />
          <div classname="container-fluid">
            <div className=" row">
              <div className="col-xs-12 col-md-6">
                <h3 style={{ fontWeight: "bold" }}>{veh.brand}</h3>
                <table className="table  table-hover  table-striped  ">
                  <tbody>
                    <tr>
                      <td>Model</td>
                      <td className="q">
                        <input
                          type="text"
                          data-testid="handle-change"
                          name="model"
                          value={capitalizeFirstLetter(check(veh.model))}
                          onChange={handleChange}
                        />
                      </td>
                    </tr>
                    <tr>
                      <td>
                        Price <span>&#8377; </span>
                      </td>
                      <td className="q">
                        <input
                          type="number"
                          name="price"
                          value={check(veh.price)}
                          onChange={handleChange}
                        />
                      </td>
                    </tr>
                    <tr>
                      <td>Color</td>
                      <td className="q">
                        <input
                          type="text"
                          name="color"
                          value={capitalizeFirstLetter(check(veh.color))}
                          onChange={handleChange}
                        />
                      </td>
                    </tr>
                    <tr>
                      <td>Release year</td>
                      <td className="q">
                        <input
                          type="number"
                          name="year"
                          value={veh.year}
                          onChange={handleChange}
                        />
                      </td>
                    </tr>
                    <tr>
                      <td>Vehicle Type</td>
                      <td className="q">
                        <input
                          type="text"
                          name="vehicleType"
                          value={capitalizeFirstLetter(check(veh.vehicleType))}
                          onChange={handleChange}
                        />
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <div className="col-xs-12 col-md-6">
                <h3 style={{ fontWeight: "bold" }}>Picture URL's</h3>
                <PictureUrlsField
                  attributeName="Pictures"
                  onDone={handlePictureUrls}
                  initialValues={veh["pictureUrls"]}
                />
              </div>
            </div>
          </div>
          <div className="container-fluid  ">
            <div className="row">
              <div className="col-xs-12 col-md-6">
                <table className="table  table-hover  table-striped  ">
                  <tbody>
                    <h3 style={{ fontWeight: "bold" }}>Key Features</h3>
                    <tr>
                      <td>Transmission</td>
                      <td className="q">
                        <input
                          type="text"
                          name="transmissionGearType"
                          value={veh.transmissionGearType}
                          onChange={handleChange}
                        />
                      </td>
                    </tr>
                    <tr>
                      <td>Steering</td>
                      <td className="q">
                        <input
                          type="text"
                          name="steeringWheelType"
                          value={veh.steeringWheelType}
                          onChange={handleChange}
                        />
                      </td>
                    </tr>
                    <tr>
                      <td>Top Speed</td>
                      <td className="q">
                        <input
                          type="text"
                          name="topSpeedLimit"
                          value={veh.topSpeedLimit}
                          onChange={handleChange}
                        />
                      </td>
                    </tr>
                    <tr>
                      <td>Fuel Consumption</td>
                      <td className="q">
                        <input
                          type="text"
                          name="unitsFuelConsumption"
                          value={veh.unitsFuelConsumption}
                          onChange={handleChange}
                        />
                      </td>
                    </tr>
                    <tr>
                      <td>Fuel Type</td>
                      <td className="q">
                        <input
                          className="form-check-input ms-2"
                          type="checkbox"
                          value=""
                          data-testid="handle-fuel"
                          id="flexCheckDefault"
                          name="diesel"
                          checked={veh["fuelType"].indexOf("diesel") > -1}
                          onChange={handleFuel}
                        />
                        <label
                          className="form-check-label ms-1"
                          for="flexCheckDefault"
                        >
                          diesel
                        </label>

                        <input
                          className="form-check-input ms-2"
                          type="checkbox"
                          value=""
                          id="flexCheckDefault"
                          name="petrol"
                          checked={veh["fuelType"].indexOf("petrol") > -1}
                          onChange={handleFuel}
                        />
                        <label
                          className="form-check-label ms-1"
                          for="flexCheckDefault"
                        >
                          petrol
                        </label>

                        <input
                          className="form-check-input ms-2"
                          data-testid="handle-fuel1"
                          type="checkbox"
                          value=""
                          id="flexCheckDefault"
                          name="cng"
                          checked={veh["fuelType"].indexOf("cng") > -1}
                          onChange={handleFuel}
                        />
                        <label
                          className="form-check-label ms-1"
                          for="flexCheckDefault"
                        >
                          cng
                        </label>

                        <input
                          className="form-check-input ms-2"
                          type="checkbox"
                          value=""
                          id="flexCheckDefault"
                          name="electric"
                          checked={veh["fuelType"].indexOf("electric") > -1}
                          onChange={handleFuel}
                        />
                        <label
                          className="form-check-label ms-1"
                          for="flexCheckDefault"
                        >
                          electric
                        </label>
                      </td>
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
                      <td data-testid="fog">
                        <input
                          className="form-check-input"
                          type="radio"
                          name="fog"
                          id="fogYes"
                          checked={veh["light"].fog}
                          onChange={handleLight}
                          data-testid="handle-light-yes"
                        />
                        <label
                          className="form-check-label me-3 ms-2"
                          for="fogYes"
                        >
                          {" "}
                          Yes
                        </label>
                        <input
                          className="form-check-input"
                          data-testid="handle-light"
                          type="radio"
                          name="fog"
                          id="fogNo"
                          checked={!veh["light"].fog}
                          onChange={handleLight}
                        />
                        <label className="form-check-label ms-2" for="fogNo">
                          {" "}
                          No{" "}
                        </label>
                      </td>
                    </tr>
                    <tr>
                      <td>Hazard:</td>
                      <td>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="hazard"
                          id="hazardYes"
                          checked={veh["light"].hazard}
                          onChange={handleLight}
                        />
                        <label
                          className="form-check-label me-3 ms-2"
                          for="hazardYes"
                        >
                          {" "}
                          Yes
                        </label>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="hazard"
                          id="hazardNo"
                          checked={!veh["light"].hazard}
                          onChange={handleLight}
                        />
                        <label className="form-check-label ms-2" for="hazardNo">
                          {" "}
                          No{" "}
                        </label>
                      </td>
                    </tr>
                    <tr>
                      <td>Parking Light:</td>
                      <td>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="parking"
                          id="parkingYes"
                          checked={veh["light"].parking}
                          onChange={handleLight}
                        />
                        <label
                          className="form-check-label me-3 ms-2"
                          for="parkingYes"
                        >
                          {" "}
                          Yes
                        </label>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="parking"
                          id="parkingNo"
                          checked={!veh["light"].parking}
                          onChange={handleLight}
                        />
                        <label
                          className="form-check-label ms-2"
                          for="parkingNo"
                        >
                          {" "}
                          No{" "}
                        </label>
                      </td>
                    </tr>
                    <tr>
                      <td>Automatic Headlights:</td>
                      <td>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="automaticHeadLights"
                          id="automaticHeadLightsYes"
                          checked={veh["light"].automaticHeadLights}
                          onChange={handleLight}
                        />
                        <label
                          className="form-check-label me-3 ms-2"
                          for="automaticHeadLightsYes"
                        >
                          {" "}
                          Yes
                        </label>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="automaticHeadLights"
                          id="automaticHeadLightsNo"
                          checked={!veh["light"].automaticHeadLights}
                          onChange={handleLight}
                        />
                        <label
                          className="form-check-label ms-2"
                          for="automaticHeadLightsNo"
                        >
                          {" "}
                          No{" "}
                        </label>
                      </td>
                    </tr>
                    <tr>
                      <td>Dynamic Head Beam:</td>
                      <td>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="dynamicHighBeam"
                          id="dynamicHighBeamYes"
                          checked={veh["light"].dynamicHighBeam}
                          onChange={handleLight}
                        />
                        <label
                          className="form-check-label me-3 ms-2"
                          for="dynamicHighBeamYes"
                        >
                          {" "}
                          Yes
                        </label>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="dynamicHighBeam"
                          id="dynamicHighBeamNo"
                          checked={!veh["light"].dynamicHighBeam}
                          onChange={handleLight}
                        />
                        <label
                          className="form-check-label ms-2"
                          for="dynamicHighBeamNo"
                        >
                          {" "}
                          No{" "}
                        </label>
                      </td>
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
                      <td className="q">
                        <input
                          className="form-check-input"
                          type="radio"
                          name="isABS"
                          id="isABSYes"
                          checked={veh.isABS}
                          onChange={handleRadio}
                        />
                        <label
                          className="form-check-label me-3 ms-2"
                          for="isABSYes"
                        >
                          {" "}
                          Yes
                        </label>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="isABS"
                          data-testid="handle-radio"
                          id="isABSNo"
                          checked={!veh.isABS}
                          onChange={handleRadio}
                        />
                        <label className="form-check-label ms-2" for="isABSNo">
                          {" "}
                          No{" "}
                        </label>
                      </td>
                    </tr>
                    <tr>
                      <td>Climate Control:</td>
                      <td data-testid="climateControl" className="q">
                        <input
                          className="form-check-input"
                          type="radio"
                          name="climateControl"
                          id="climateControlYes"
                          checked={veh["climate"].climateControl}
                          onChange={handleClimate}
                          data-testid="climateControlYes"
                        />
                        <label
                          className="form-check-label me-3 ms-2"
                          for="climateControlYes"
                        >
                          {" "}
                          Yes
                        </label>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="climateControl"
                          id="climateControlNo"
                          data-testid="climateControlNo"
                          checked={!veh["climate"].climateControl}
                          onChange={handleClimate}
                        />
                        <label
                          className="form-check-label ms-2"
                          for="climateControlNo"
                        >
                          {" "}
                          No{" "}
                        </label>
                      </td>
                    </tr>
                    <tr>
                      <td>Defrost:</td>
                      <td className="q">
                        <input
                          className="form-check-input"
                          type="radio"
                          name="defrost"
                          id="defrostYes"
                          checked={veh["climate"].defrost}
                          onChange={handleClimate}
                        />
                        <label
                          className="form-check-label me-3 ms-2"
                          for="defrostYes"
                        >
                          {" "}
                          Yes
                        </label>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="defrost"
                          id="defrostNo"
                          checked={!veh["climate"].defrost}
                          onChange={handleClimate}
                        />
                        <label
                          className="form-check-label ms-2"
                          for="defrostNo"
                        >
                          {" "}
                          No{" "}
                        </label>
                      </td>
                    </tr>
                    <tr>
                      <td>Tank Capacity:</td>
                      <td className="q">
                        <input
                          type="text"
                          name="tankCapacity"
                          value={veh.tankCapacity}
                          onChange={handleChange}
                        />
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <div className="col-xs-12 col-md-6">
                <table className="table table-hover  table-striped ">
                  <tbody>
                    <tr>
                      <td>Accelaration:</td>
                      <td className="q">
                        <input
                          type="text"
                          name="accelaration"
                          value={check(veh.accelaration)}
                          onChange={handleChange}
                        />
                      </td>
                    </tr>
                    <tr>
                      <td>Power Train Torque:</td>
                      <td className="q">
                        <input
                          type="text"
                          name="powerTrainTorque"
                          value={check(veh.powerTrainTorque)}
                          onChange={handleChange}
                        />
                      </td>
                    </tr>
                    <tr>
                      <td>Ignition Time:</td>
                      <td className="q">
                        <input
                          type="text"
                          name="ignitionTime"
                          value={check(veh.ignitionTime)}
                          onChange={handleChange}
                        />
                      </td>
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
                      <td>Night Mode:</td>
                      <td className="q">
                        <input
                          className="form-check-input"
                          type="radio"
                          name="nightMode"
                          id="nightModeYes"
                          checked={veh.nightMode}
                          onChange={handleRadio}
                        />
                        <label
                          className="form-check-label me-3 ms-2"
                          for="nightModeYes"
                        >
                          {" "}
                          Yes
                        </label>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="nightMode"
                          id="nightModeNo"
                          checked={!veh.nightMode}
                          onChange={handleRadio}
                        />
                        <label
                          className="form-check-label ms-2"
                          for="nightModeNo"
                        >
                          {" "}
                          No{" "}
                        </label>
                      </td>
                    </tr>
                    <tr>
                      <td>Sunroof:</td>
                      <td data-testid="sunroof" className="q">
                        <input
                          className="form-check-input"
                          type="radio"
                          name="sunroof"
                          id="sunroofYes"
                          checked={veh["climate"].sunroof}
                          onChange={handleClimate}
                        />
                        <label
                          className="form-check-label me-3 ms-2"
                          for="sunroofYes"
                        >
                          {" "}
                          Yes
                        </label>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="sunroof"
                          id="sunroofNo"
                          checked={!veh["climate"].sunroof}
                          onChange={handleClimate}
                        />
                        <label
                          className="form-check-label ms-2"
                          for="sunroofNo"
                        >
                          {" "}
                          No{" "}
                        </label>
                      </td>
                    </tr>
                    <tr>
                      <td>Air Conditioning:</td>
                      <td className="q">
                        <input
                          className="form-check-input"
                          type="radio"
                          name="airConditioning"
                          id="airConditioningYes"
                          checked={veh.airConditioning}
                          onChange={handleRadio}
                        />
                        <label
                          className="form-check-label me-3 ms-2"
                          for="airConditioningYes"
                        >
                          {" "}
                          Yes
                        </label>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="airConditioning"
                          id="airConditioningNo"
                          checked={!veh.airConditioning}
                          onChange={handleRadio}
                        />
                        <label
                          className="form-check-label ms-2"
                          for="airConditioningNo"
                        >
                          {" "}
                          No{" "}
                        </label>
                      </td>
                    </tr>
                    <tr>
                      <td>Media Interface:</td>
                      <td className="q">
                        <input
                          type="text"
                          name="mediaInterface"
                          value={capitalizeFirstLetter(
                            check(veh.mediaInterface)
                          )}
                          onChange={handleChange}
                        />
                      </td>
                    </tr>
                    <tr>
                      <td>Mirror Type:</td>
                      <td className="q">
                        <input
                          type="text"
                          name="mirror"
                          value={capitalizeFirstLetter(check(veh.mirror))}
                          onChange={handleChange}
                        />
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
                      <td>
                        <input
                          type="number"
                          name="airBagCount"
                          value={veh.airBagCount}
                          onChange={handleChange}
                        />
                      </td>
                    </tr>
                    <tr>
                      <td>Child Lock:</td>
                      <td>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="childSafetyLock"
                          id="childSafetyLockYes"
                          checked={veh.childSafetyLock}
                          onChange={handleRadio}
                        />
                        <label
                          className="form-check-label me-3 ms-2"
                          for="childSafetyLockYes"
                        >
                          {" "}
                          Yes
                        </label>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="childSafetyLock"
                          id="childSafetyLockNo"
                          checked={!veh.childSafetyLock}
                          onChange={handleRadio}
                        />
                        <label
                          className="form-check-label ms-2"
                          for="childSafetyLockNo"
                        >
                          {" "}
                          No{" "}
                        </label>
                      </td>
                    </tr>
                    <tr>
                      <td>Malfunction Indicator:</td>
                      <td>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="malfunctionIndicator"
                          id="malfunctionIndicatorYes"
                          checked={veh.malfunctionIndicator}
                          onChange={handleRadio}
                        />
                        <label
                          className="form-check-label me-3 ms-2"
                          for="malfunctionIndicatorYes"
                        >
                          {" "}
                          Yes
                        </label>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="malfunctionIndicator"
                          id="malfunctionIndicatorNo"
                          checked={!veh.malfunctionIndicator}
                          onChange={handleRadio}
                        />
                        <label
                          className="form-check-label ms-2"
                          for="malfunctionIndicatorNo"
                        >
                          {" "}
                          No{" "}
                        </label>
                      </td>
                    </tr>
                    <tr>
                      <td>Theft Alarm:</td>
                      <td>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="theftAlarm"
                          id="theftAlarmYes"
                          checked={veh.theftAlarm}
                          onChange={handleRadio}
                        />
                        <label
                          className="form-check-label me-3 ms-2"
                          for="theftAlarmYes"
                        >
                          {" "}
                          Yes
                        </label>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="theftAlarm"
                          id="theftAlarmNo"
                          checked={!veh.theftAlarm}
                          onChange={handleRadio}
                        />
                        <label
                          className="form-check-label ms-2"
                          for="automaticHeadLightsNo"
                        >
                          {" "}
                          No{" "}
                        </label>
                      </td>
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
                      <td>Odometer:</td>
                      <td className="q">
                        <input
                          className="form-check-input"
                          type="radio"
                          name="odometer"
                          id="odometerYes"
                          checked={veh.odometer}
                          onChange={handleRadio}
                        />
                        <label
                          className="form-check-label me-3 ms-2"
                          for="odometerYes"
                        >
                          {" "}
                          Yes
                        </label>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="odometer"
                          id="odometerNo"
                          checked={!veh.odometer}
                          onChange={handleRadio}
                        />
                        <label
                          className="form-check-label ms-2"
                          for="automaticHeadLightsNo"
                        >
                          {" "}
                          No{" "}
                        </label>
                      </td>
                    </tr>
                    <tr>
                      <td>Washer Fluid:</td>
                      <td className="q">
                        <input
                          className="form-check-input"
                          type="radio"
                          name="washerFluid"
                          id="washerFluidYes"
                          checked={veh.washerFluid}
                          onChange={handleRadio}
                        />
                        <label
                          className="form-check-label me-3 ms-2"
                          for="washerFluidYes"
                        >
                          {" "}
                          Yes
                        </label>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="washerFluid"
                          id="washerFluidNo"
                          checked={!veh.washerFluid}
                          onChange={handleRadio}
                        />
                        <label
                          className="form-check-label ms-2"
                          for="automaticHeadLightsNo"
                        >
                          {" "}
                          No{" "}
                        </label>
                      </td>
                    </tr>
                    <tr>
                      <td>Chime:</td>
                      <td className="q">
                        <input
                          className="form-check-input"
                          type="radio"
                          name="chime"
                          id="chimeYes"
                          checked={veh.chime}
                          onChange={handleRadio}
                        />
                        <label
                          className="form-check-label me-3 ms-2"
                          for="chimeYes"
                        >
                          {" "}
                          Yes
                        </label>
                        <input
                          className="form-check-input"
                          type="radio"
                          name="chime"
                          id="chimeNo"
                          checked={!veh.chime}
                          onChange={handleRadio}
                        />
                        <label className="form-check-label ms-2" for="chimeNo">
                          {" "}
                          No{" "}
                        </label>
                      </td>
                    </tr>
                    <tr>
                      <td>Trip Meter:</td>
                      <td className="q">
                        <input
                          type="text"
                          name="tripMeter"
                          value={capitalizeFirstLetter(check(veh.tripMeter))}
                          onChange={handleChange}
                        />
                      </td>
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
                        <input
                          type="text"
                          name="vehicleHorn"
                          value={capitalizeFirstLetter(check(veh.horn))}
                          onChange={handleChange}
                        />
                      </td>
                    </tr>
                    <tr>
                      <td>Wheel Radius:</td>
                      <td className="q">
                        <input
                          type="text"
                          name="wheelRadius"
                          value={check(veh.wheelRadius)}
                          onChange={handleChange}
                        />
                      </td>
                    </tr>
                    <tr>
                      <td>Wheel Speed:</td>
                      <td className="q">
                        <input
                          type="text"
                          name="wheelSpeed"
                          value={check(veh.wheelSpeed)}
                          onChange={handleChange}
                        />
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          <button
            data-testid="update-vehicleDetails-test"
            className="btn btn-primary w-20 my-3 center"
          >
            Update Details
          </button>
        </div>
      </form>
    </div>
  );
  return { ...vehicles };
}
