/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
import React from "react";
import { capitalizeFirstLetter, splitCamelCase } from "../../../utils/utils";

export default function TextAndNumberInput({
  type,
  attributeName,
  handleChange,
  mandatory = false,
  uniqueKey,
  dataTestId,
}) {
  return (
    <div
      data-testid="fieldInput"
      className="col-md-4 col-sm-6 col-12"
      key={uniqueKey}
    >
      <label htmlFor={attributeName} className="form-label">
        {splitCamelCase(capitalizeFirstLetter(attributeName))} :{" "}
        <strong className="text-danger">{mandatory && "*"}</strong>
      </label>
      <input
        data-testid={dataTestId}
        required={mandatory}
        onChange={handleChange}
        type={type}
        id={attributeName}
        name={attributeName}
        className="form-control"
      />
      <br />
    </div>
  );
}
