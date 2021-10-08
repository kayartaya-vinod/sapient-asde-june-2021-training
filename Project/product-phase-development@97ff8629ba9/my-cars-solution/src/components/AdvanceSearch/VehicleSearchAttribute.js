import React, { useState } from "react";
import { Checkbox, Radio } from "antd";
import { useDispatch, useSelector } from "react-redux";
import { updateFilters } from "../../redux/actionCreators/vehicleFilterAttributeActionCreator/vehicleFilterAttributeActionCreator";

function VehicleSearchAttribute ({ searchAttribute = {}, keyIndex = {} }) {

  const vehicleFilter = useSelector(
    (store) => store.vehicleFilterAttributeReducer
  );
  let initialCheckState = {};
  searchAttribute.fields.forEach((v) => {
    initialCheckState[v.field] = false;
  });
  const [checked, setChecked] = useState(initialCheckState);

  const [radio, setRadio] = useState("");

  const dispatch = useDispatch();

  const currentFilter = vehicleFilter.filter(
    (v) => v.attribute === searchAttribute.attribute
  )[0];

  const handleCheck = (e) => {
    const valueField = e.target["data-valuefield"].toString();

    if (e.target.checked === true) {
      const newCheckedState = { ...checked };
      newCheckedState[valueField] = true;
      setChecked(newCheckedState);

      let queryField = currentFilter.queryField || [""];
      queryField = queryField[0];
      if (queryField === "") {
        queryField = valueField;
      } else {
        queryField = queryField.split(",");
        const index = queryField.findIndex((v) => v === valueField);

        if (index === -1) {
          queryField.push(valueField);
        }

        queryField = queryField.join(",");
      }

      dispatch(updateFilters(searchAttribute.attribute, [queryField]));
    } else {
      const newCheckedState = { ...checked };
      newCheckedState[valueField] = false;
      setChecked(newCheckedState);

      let queryField = currentFilter.queryField || [""];
      queryField = queryField[0];
      queryField = queryField.split(",");
      const index = queryField.findIndex((v) => v === valueField);

      queryField.splice(index, 1);

      queryField = queryField.join(",");

      dispatch(updateFilters(searchAttribute.attribute, [queryField]));
    }
  };

  const handleRadio = (e) => {
    setRadio(e.target.value);
    let queryField = e.target.value;
    dispatch(updateFilters(searchAttribute.attribute, queryField));
  };

  const renderCheckBox = () => {
    return searchAttribute.fields.map((value, index) => (
      <span key={index}>
        <Checkbox
          type="checkbox"
          data-testid={value.field}
          data-valuefield={value.field}
          checked={checked[value.field]}
          onChange={handleCheck}
        />
        &nbsp;&nbsp;
        {value.displayName}
      </span>
    ));
  };
  const renderRadio = () => {
    const radioGroup = searchAttribute.fields.map((value, index) => (
      <div key={index}>
        <Radio
          key={value.field}
          value={value.field}
          data-testid={value.displayName}
        />
        &nbsp;&nbsp; &nbsp;&nbsp;
        {value.displayName}
      </div>
    ));

    return (
      <Radio.Group onChange={handleRadio} value={radio}>
        {radioGroup}
      </Radio.Group>
    );
  };

  return (
    <>
      <div className="accordion-item">
        <h2 className="accordion-header">
          <button
            className="accordion-button collapsed"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target={`#panelsStayOpen-${keyIndex}`}
            aria-controls={`panelsStayOpen-${keyIndex}`}
          >
            {searchAttribute.displayName}
          </button>
        </h2>
        <div
          id={`panelsStayOpen-${keyIndex}`}
          className="accordion-collapse collapse"
          aria-labelledby={`panelsStayOpen-${keyIndex}`}
        >
          <div
            className="accordion-body d-flex flex-column"
            style={{ overflow: "auto", height: "10rem" }}
          >
            {searchAttribute.componentType === "check" && renderCheckBox()}
            {searchAttribute.componentType === "radio" && renderRadio()}
          </div>
        </div>
      </div>
    </>
  );
}

export default VehicleSearchAttribute;
