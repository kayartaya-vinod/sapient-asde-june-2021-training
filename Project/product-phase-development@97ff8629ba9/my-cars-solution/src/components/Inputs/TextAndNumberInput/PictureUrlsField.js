/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
import React, { useState } from "react";
import { capitalizeFirstLetter, splitCamelCase } from "../../../utils/utils";
import { BsPlusSquare, BsFillTrashFill, BsCheck } from "react-icons/bs";

export default function PictureUrlsField({
  type,
  attributeName,
  mandatory=false,
  uniqueKey = null,
  onDone = () => null,
  dataTestId,
  initialValues = [],
}) {
  
  const initialInputs = ["input-0"];
  for (let i = 1; i < initialValues.length; i++) {
    initialInputs.push("input-" + i);
  }

  const [inputs, setInputs] = useState(initialInputs);
  const [err, setErr] = useState("");
  const [saveMsg, setSaveMsg] = useState("");
  const [urls, setUrls] = useState({
    "input-0": initialValues[0] || "",
    "input-1": initialValues[1] || "",
    "input-2": initialValues[2] || "",
    "input-3": initialValues[3] || "",
    "input-4": initialValues[4] || "",
  });

  const handleAdd = () => {
    if (urls[`input-${inputs.length - 1}`]) {
      let newInput = `input-${inputs.length}`;
      setInputs(inputs.concat([newInput]));
      return;
    }
    setErr("Please fill all fields first to add more!");
    setTimeout(() => {
      setErr("");
    }, 2000);
  };

  const handleDelete = () => {
    setSaveMsg("");
    let deleteInput = `input-${inputs.length - 1}`;
    let newInputs = [...inputs];
    let index = newInputs.indexOf(deleteInput);
    if (index !== -1) newInputs.splice(index, 1);
    let newUrls = { ...urls };
    newUrls[deleteInput] = "";
    setInputs(newInputs);
    setUrls(newUrls);
  };

  const handleOnBlur = ({ target: { name, value } }) => {
    let newUrls = { ...urls };
    newUrls[name] = value;
    setUrls(newUrls);
  };

  const handleSubmit = (evt) => {
    evt.preventDefault();
    if (urls[`input-${inputs.length - 1}`]) {
      onDone(urls);
      setSaveMsg("Saved Successfully");
      setTimeout(() => {
        setSaveMsg("");
      }, 2000);
      return;
    }
    setErr("Please fill all fields or delete extra one");
    setTimeout(() => {
      setErr("");
    }, 2000);
  };

  return (
    <div data-testid="fieldInput" key={uniqueKey}>
      <label className="form-label">
        {splitCamelCase(capitalizeFirstLetter(attributeName))} :{" "}
        <strong className="text-danger">{mandatory && "*"}</strong>
      </label>
      <div className="d-inline " style={{ textAlign: "right" }}>
        {inputs.length <= 4 && (
          <button
            data-testid="addBtn"
            type="button"
            className="btn m-1 btn-success"
            onClick={handleAdd}
          >
            <BsPlusSquare />
          </button>
        )}
        {inputs.length > 1 && (
          <button
            type="button"
            data-testid="deleteBtn"
            className="btn m-1 btn-danger"
            onClick={handleDelete}
          >
            <BsFillTrashFill />
          </button>
        )}
        <button
          className="btn m-1 btn-primary"
          data-testid="doneBtn"
          onClick={handleSubmit}
        >
          <BsCheck />
        </button>
      </div>
      {inputs.map((i) => (
        <input
          required={true}
          onBlur={handleOnBlur}
          key={i}
          data-testid={`Test${i}`}
          name={i}
          defaultValue={urls[i]}
          className={`form-control mb-1`}
        />
      ))}
      <div className={`text-danger`}>{err}</div>
      <div className={`text-success`}>{saveMsg}</div>
      <div className="fw-bold">
        Please click <BsCheck /> to save images.
      </div>
    </div>
  );
}
