import React from "react";
import SearchBar from "../SearchBar/SearchBar";
import SponsoredVehicle from "../SponsoredVehicle/SponsoredVehicle";
import Vehicles from "../Vehicles/Vehicles";
import CompareButton from "../AddToCompare/CompareButton";

export default function Home () {
  return (
    <>
      <div
        className="container"
        style={{
          display: "flex",
          justifyContent: "center",
          paddingBottom: "30px",
        }}
        data-testid="customer-page"
      >
        <CompareButton />
        <div className="col-12 col-md-8">
          <SearchBar />
        </div>
      </div>
      <SponsoredVehicle />
      <br />
      <Vehicles />
    </>
  );
}
