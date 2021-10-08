/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
import React from "react";
import { capitalizeFirstLetter, splitCamelCase } from "../../../utils/utils";

export default function RadioButtonInput({
  type,
  attributeName,
  options = [],
  handleChange,
  mandatory=false,
  uniqueKey,
}) {
  const availableOptions = options.map((option, index) => (
    <div
      data-testid="fieldInputOptions"
      className="form-check form-check-inline"
      key={index}
    >
      <label className="form-check-label" htmlFor={attributeName + option}>
        {option}
      </label>
      <input
        className="form-check-input"
        type={type}
        name={attributeName}
        id={attributeName + option}
        value={option === "Yes"}
        onChange={handleChange}
      />
    </div>
  ));
  return (
    <div
      data-testid="fieldInput"
      className="col-md-4 col-sm-6 col-12 mb-4"
      key={uniqueKey}
    >
      <label className="form-check-label mb-2">
        {splitCamelCase(capitalizeFirstLetter(attributeName))} : <strong className="text-danger">{mandatory && "*"}</strong>
      </label>
      <br />
      {availableOptions}
    </div>
  );
}
