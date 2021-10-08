/**
@Author Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/

import React from "react";
import VehicleCard from "../VehicleCard/VehicleCard";

export default function VehicleGrid ({ vehicles }) {
  const vehicleList = vehicles.map((vehicle, index) => (
    <div className="col-12 col-md-4" key={index}><VehicleCard vehicle={vehicle} /></div>
  ));
  return <div className="row g-3">{vehicleList}</div>;
}
