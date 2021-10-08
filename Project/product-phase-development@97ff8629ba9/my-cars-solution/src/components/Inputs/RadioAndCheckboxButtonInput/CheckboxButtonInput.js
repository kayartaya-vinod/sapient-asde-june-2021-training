/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
import React from "react";
import { capitalizeFirstLetter, splitCamelCase } from "../../../utils/utils";

export default function CheckboxButtonInput({
  type,
  attributeName,
  options = [],
  handleChange,
  uniqueKey,
  dataTestId,
  mandatory = false
}) {
  const availableOptions = options.map((option, index) => (
    <div
      className="form-check form-check-inline"
      key={index}
      data-testid="fieldInputOptions"
    >
      <label className="form-check-label" htmlFor={attributeName + option}>
        {option} 
      </label>
      <input
        className="form-check-input"
        type={type}
        name={attributeName}
        id={attributeName + option}
        value={option}
        data-testid={dataTestId + index}
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
