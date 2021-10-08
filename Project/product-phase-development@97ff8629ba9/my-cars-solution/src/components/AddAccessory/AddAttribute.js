/**
@Author Sumitesh Naithani sumitesh.naithani@publicissapient.com
*/
import React, { useState } from "react";
import { BsCheck, BsFillTrashFill, BsPlusSquare } from "react-icons/bs";

export default function AddAttribute({ setAtt }) {
  const [formValues, setFormValues] = useState([
    { nameOfAttribute: "", value: "" },
  ]);

  const [msg, setMsg] = useState("");
  const [err, setErr] = useState("");

  let handleChange = (i, e) => {
    let newFormValues = [...formValues];
    newFormValues[i][e.target.name] = e.target.value;
    setFormValues(newFormValues);
    setErr("");
    setMsg("");
  };

  let addFormFields = () => {
    setFormValues([...formValues, { nameOfAttribute: "", value: "" }]);
  };

  let removeFormFields = (i) => {
    let newFormValues = [...formValues];
    newFormValues.splice(i, 1);
    setFormValues(newFormValues);
  };

  let handleSubmit = (event) => {
    event.preventDefault();
    let temp = {};
    formValues.forEach(
      (e = { nameOfAttribute: "", value: "" }) =>
        (temp[e.nameOfAttribute] = e.value)
    );
    let check = true;
    const keys = Object.keys(temp);
    keys.forEach((key) => {
      if (key === "") check = false;
    });
    if (check) {
      setAtt({});
      setAtt(temp);
      setMsg("Saved successfully !");
    } else {
      setErr("Save not successful !");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div style={{ display: "inline" }}>
        <label data-testid="info">Additional attribute : </label>
      </div>
      <div className="button-section d-inline" style={{ textAlign: "right" }}>
        <button
          className="btn m-1 btn-success"
          type="button"
          data-testid="addBtn"
          onClick={() => addFormFields()}
        >
          <BsPlusSquare />
        </button>
        <button
          className="btn m-1 btn-primary"
          onClick={handleSubmit}
          data-testid="submit-btn"
          type="button"
        >
          <BsCheck />
        </button>
      </div>
      {formValues.map((element, index) => (
        <div className="row my-1" key={index}>
          <div className=" col-md-4">
            <input
              type="text"
              name="nameOfAttribute"
              placeholder="Name of attribute"
              value={element.nameOfAttribute || ""}
              onChange={(e) => handleChange(index, e)}
              required={true}
              data-testid="nameOfAttribute"
              className="ms-3 form-control mb-1"
            />
          </div>
          <div className="col-md-4">
            <input
              type="text"
              name="value"
              placeholder="Value of attribute"
              value={element.value || ""}
              onChange={(e) => handleChange(index, e)}
              required={true}
              data-testid="value"
              className="ms-3 form-control mb-1"
            />
          </div>
          {index ? (
            <div className="col-md-2 my-0 py-0">
              <button
                type="button"
                data-testid="removeBtn"
                className="btn m-1 btn-danger col-md-3 my-0 py-0"
                onClick={() => removeFormFields(index)}
              >
                <BsFillTrashFill />
              </button>
            </div>
          ) : null}
        </div>
      ))}
      <div className={`text-success`}>{msg}</div>
      <div className={`text-danger`}>{err}</div>
      <div className="fw-bold">
        Please click <BsCheck /> to save attributes.
      </div>
    </form>
  );
}
