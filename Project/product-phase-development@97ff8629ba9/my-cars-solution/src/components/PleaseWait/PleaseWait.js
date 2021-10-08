/**
@Author Rohit Bhatt rohit.bhatt1@publicissapient.com
*/

import React from "react";

import "./PleaseWait.css";
import loading from "../../utils/loadingCar.gif";

export default function PleaseWait() {
  return (
    <div className="please-wait">
      <img src={loading} alt="loading" />
    </div>
  );
}
