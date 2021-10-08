/**
@Author Manvendra Singh
*/

import React from "react";
import AccessoryCard from "../AccessoryCard/AccessoryCard";

export default function AccessoryGrid ({ accessories }) {
  //Getting the accessories list as prop and creating the list of card using the same
  const accessoryList = accessories.map((accessory, index) => (
    <div className="col-12 col-md-4" key={index}><AccessoryCard accessory={accessory} /></div>
  ));
  return <div className="row g-3">{accessoryList}</div>;
}
