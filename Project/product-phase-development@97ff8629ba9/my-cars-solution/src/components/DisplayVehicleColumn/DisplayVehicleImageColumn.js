/**
@Author Pritam Patel - pritam.patel@publicissapient.com
*/
import { useHistory } from "react-router";

import React, {useEffect, useState } from "react";
import { fetchVehicleById } from "../../redux/actionCreators/vehicleActionCreator/vehicleActionCreator";
import { addDefaultSrc } from "../../utils/utils";
const initialState = {
  dealerId: "",
  tankCapacity: "",
  mediaInterface: "",
  theftAlarm: "",
  pictureUrls: [],
  tripMeter: "",
  airBagCount: "",
  description: "",
  price: "",
  color: "",
  vin: "",
  brand: "",
  model: "",
  year: "",
  vehicleType: "",
  fuelType: [],
  unitsFuelConsumption: "",
  transmissionGearType: "",
  steeringWheelType: "",
  vehicleSpeed: "",
  horn: "",
  powerTrainTorque: "",
  accelaration: "",
  nightMode: "",
  isABS: false,
  wheelRadius: "",
  wheelSpeed: "",
  light: {
    fog: false,
    hazard: false,
    parking: false,
    dynamicHighBeam: false,
    automaticHeadlights: false,
  },
  ignitionTime: "",
  odometer: false,
  washerFluid: false,
  malfunctionIndicator: false,
  batteryLevel: "",
  airConditioning: false,
  languageConfiguration: "",
  mirror: "automatic",
  childSafetyLock: false,
  topSpeedLimit: "",
  climate: {
    climateControl: false,
    sunroof: false,
    defrost: false,
  },
  chime: false,
};
export default function DisplayVehicleImageColumn({ vehicleId }) {
   const history = useHistory();

  const [vehicle, setVehicle] = useState(initialState);
  useEffect(() => {
    (async () => {
      setVehicle((await fetchVehicleById(vehicleId)).payload);
    })();
  }, [vehicleId]);

  var vehicleName = vehicle.brand + " " + vehicle.model;

  return (
    <div>
      <div className="card">
        <img
          onClick={() => history.push("/vehicles/" + vehicle.id)}
          src={vehicle.pictureUrls[0]}
          className="card-img-top img-fluid embed-responsive"
          title={vehicleName}
          data-testid="img-button"
          alt={vehicle.brand}
          style={{ width: "100%", height: "170px", cursor: "pointer" }}
          onError={addDefaultSrc}
        />
      </div>
    </div>
  );
}
