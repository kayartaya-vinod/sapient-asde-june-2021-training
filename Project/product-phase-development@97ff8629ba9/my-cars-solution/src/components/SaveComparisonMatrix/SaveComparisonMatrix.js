import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { saveComparisonMatrix } from "../../redux/actionCreators/saveMatrixActionCreator/saveMatrixActionCreator";

export default function SaveComparisonMatrix( {compareName}) {
  const matrix = useSelector((store) => store.vehicleCompareListReducer);
  const [saveMessage, setSaveMessage] = useState("Save Comparison")
  const payload = {
    vehicleIds: matrix, //  _id s of selected vehicles for comparison
    comparisonMatrixName: compareName,
  };

  const dispatch = useDispatch();
  let msg;

  const handleClick = async () => {
    if (
      saveMessage === "Save Comparison" ||
      saveMessage === "Error"
    ) {
      const resp = dispatch(await saveComparisonMatrix(payload));
      if(resp.payload.error){
        setSaveMessage("Unable to save");
      }
      if (resp.payload.message) {
        setSaveMessage(resp.payload.message);
      } else {
        setSaveMessage("Saved");
      }
    } 
    if (saveMessage === "Saved") {
      setSaveMessage("Already saved !!");
    } 
  };
  return matrix && matrix.length ? (
    <>
      <button
        className="btn btn-link"
        data-testid="save"
        id = "saveButton"
        onClick={handleClick}
      >
        {saveMessage}
      </button>
      {msg}
    </>
  ) : null;
}
