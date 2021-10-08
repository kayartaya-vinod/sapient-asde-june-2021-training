import React, { useEffect, useState } from "react";
import { fetchVehicleById } from "../../redux/actionCreators/vehicleActionCreator/vehicleActionCreator";

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
export default function DisplayVehicleDetailsColumn({ vehicleId }) {
  const [vehicle, setVehicle] = useState(initialState);
  useEffect(() => {
    (async () => {
      setVehicle((await fetchVehicleById(vehicleId)).payload);
    })();
  }, [vehicleId]);

  var vehicleName = vehicle.brand + " " + vehicle.model;
  var vehicleFuelType = "";

  const addFuelType = () => {
    if (vehicle.fuelType) {
      for (var index = 0; index < vehicle.fuelType.length; index++) {
        vehicleFuelType += vehicle.fuelType[index];
        if (index !== vehicle.fuelType.length - 1) {
          vehicleFuelType += ", ";
        }
      }
    }
    return <>{vehicleFuelType}</>;
  };

  const checkBool = (valueToCheck) => {
    return (
      <>
        {valueToCheck && (
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="green"
            class="bi bi-check-lg"
            viewBox="0 0 16 16"
          >
            <path d="M13.485 1.431a1.473 1.473 0 0 1 2.104 2.062l-7.84 9.801a1.473 1.473 0 0 1-2.12.04L.431 8.138a1.473 1.473 0 0 1 2.084-2.083l4.111 4.112 6.82-8.69a.486.486 0 0 1 .04-.045z" />
          </svg>
        )}

        {!valueToCheck && (
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="red"
            class="bi bi-x-lg"
            viewBox="0 0 16 16"
          >
            <path d="M1.293 1.293a1 1 0 0 1 1.414 0L8 6.586l5.293-5.293a1 1 0 1 1 1.414 1.414L9.414 8l5.293 5.293a1 1 0 0 1-1.414 1.414L8 9.414l-5.293 5.293a1 1 0 0 1-1.414-1.414L6.586 8 1.293 2.707a1 1 0 0 1 0-1.414z" />
          </svg>
        )}
      </>
    );
  };

  return (
    <div>
      <table className="table table-borderless  text-center">
        <tbody>
          <tr>
            <h5>{vehicleName}</h5>
          </tr>
          <div className="text-center px-5">
            <tr>{vehicle.brand}</tr>
            <br />
            <tr>
              <span>&#8377;</span> &nbsp;
              {vehicle.price}/-
            </tr>
            <br />
            <tr>{vehicle.vehicleType}</tr>
            <br />
            <tr>{vehicle.year}</tr>
            <br />
            <tr>{addFuelType()}</tr>
            <br />
            <tr>{checkBool(vehicle.airConditioning)}</tr>
          </div>
        </tbody>
      </table>
      <table className="table table-borderless  text-center">
        <tbody>
          <tr>
            <h5>
              <br />
            </h5>
          </tr>
          <div className="text-center px-5">
            <tr>{vehicle.topSpeedLimit}</tr>
            <br />
            <tr>{vehicle.unitsFuelConsumption}</tr>
            <br />
            <tr>{vehicle.tankCapacity}</tr>
            <br />
            <tr>{vehicle.wheelSpeed}</tr>
            <br />
            <tr>{vehicle.ignitionTime}</tr>
            <br />
            <tr>{vehicle.accelaration}</tr>
            <br />
            <tr>{vehicle.powerTrainTorque}</tr>
            <br />
            <tr>{vehicle.batteryLevel}</tr>
          </div>
        </tbody>
      </table>
      <table className="table table-borderless  text-center">
        <tbody>
          <tr>
            <h5>
              <br />
            </h5>
          </tr>
          <div className="text-center px-5">
            <tr>{checkBool(vehicle.theftAlarm)}</tr>
            <br />
            <tr>{vehicle.airBagCount}</tr>
            <br />
            <tr>{checkBool(vehicle.nightMode)}</tr>
            <br />
            <tr>{checkBool(vehicle.childSafetyLock)}</tr>
            <br />
            <tr>{checkBool(vehicle.malfunctionIndicator)}</tr>
            <br />
            <tr>{checkBool(vehicle.isABS)}</tr>
          </div>{" "}
        </tbody>
      </table>
      <table className="table table-borderless  text-center">
        <tbody>
          <tr>
            <h5>
              <br />
            </h5>
          </tr>
          <div className="text-center px-5">
            <tr>{vehicle.mediaInterface}</tr>
            <br />
            <tr>{vehicle.tripMeter}</tr>
            <br />
            <tr>{vehicle.transmissionGearType}</tr>
            <br />
            <tr>{vehicle.steeringWheelType}</tr>
            <br />
            <tr>{vehicle.horn}</tr>
            <br />
            <tr>{vehicle.wheelRadius}</tr>
            <br />
            <tr>{checkBool(vehicle.odometer)}</tr>
            <br />
            <tr>{vehicle.mirror}</tr>
            <br />
            <tr>{checkBool(vehicle.washerFluid)}</tr>
            <br />
            <tr>{vehicle.languageConfiguration}</tr>
            <br />
            <tr>{checkBool(vehicle.chime)}</tr>
          </div>{" "}
        </tbody>
      </table>
      <table className="table table-borderless  text-center">
        <tbody>
          <tr>
            <h5>
              <br />
            </h5>
          </tr>
          <div className="text-center px-5">
            <tr>{checkBool(vehicle.light.fog)}</tr>
            <br />
            <tr>{checkBool(vehicle.light.hazard)}</tr>
            <br />
            <tr>{checkBool(vehicle.light.parking)}</tr>
            <br />
            <tr>{checkBool(vehicle.light.dynamicHighBeam)}</tr>
            <br />
            <tr>{checkBool(vehicle.light.automaticHeadlights)}</tr>
          </div>{" "}
        </tbody>
      </table>
      <table className="table table-borderless  text-center">
        <tbody>
          <tr>
            <h5>
              <br />
            </h5>
          </tr>
          <div className="text-center px-5">
            <tr>{checkBool(vehicle.climate.climateControl)}</tr>
            <br />
            <tr>{checkBool(vehicle.climate.sunroof)}</tr>
            <br />
            <tr>{checkBool(vehicle.climate.defrost)}</tr>
          </div>{" "}
        </tbody>
      </table>
    </div>
  );
}
