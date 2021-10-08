/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
import React, { useEffect, useState } from "react";
import TextAndNumberInput from "../Inputs/TextAndNumberInput/TextAndNumberInput";
import {
  numberFields,
  textFields,
  checkBoxButtonFields,
  radioButtonFields,
  pictureUrlsFields,
} from "../../constants/vehiclesAttributes";
import CheckboxButtonInput from "../Inputs/RadioAndCheckboxButtonInput/CheckboxButtonInput";
import RadioButtonInput from "../Inputs/RadioAndCheckboxButtonInput/RadioButtonInput";
import { initialVehicleState } from "../../utils/initialVehicleState";
import { addVehicles } from "../../redux/actionCreators/vehiclesActionCreator/vehiclesActionCreator";
import { useSelector, useDispatch } from "react-redux";
import { useHistory } from "react-router";
import { RESET_ADD_VEHICLE } from "../../redux/actionTypes";
import PictureUrlsField from "../Inputs/TextAndNumberInput/PictureUrlsField";
export default function AddVehicle() {
  const [vehicle, setVehicle] = useState(initialVehicleState);
  const [fuelType, setFuelType] = useState([]);
  const { success, message } = useSelector((store) => store.addVehicle);
  const history = useHistory();

  const dispatch = useDispatch();

  useEffect(() => {
    if (success) {
      setTimeout(() => {
        dispatch({ type: RESET_ADD_VEHICLE });
        history.push("/dealer/dashboard");
      }, 3000);
    }
  }, [success, dispatch, history]);

  const handleCheckboxChange = ({ target }) => {
    const v = { ...vehicle };
    if (target.checked) {
      let f = [...fuelType];

      f.push(target.value);
      setFuelType(f);

      v[target.name] = f;
      setVehicle(v);
      return;
    }
    if (!target.checked) {
      let f = [...fuelType];
      let index = f.indexOf(target.value);
      if (index !== -1) f.splice(index, 1);
      setFuelType(f);

      v[target.name] = f;
      setVehicle(v);
    }
  };

  const handlePictureUrls = (urls) => {
    const v = { ...vehicle };

    let pictures = [];
    for (let i = 0; i < 5; ++i) {
      urls["input-" + i] && pictures.push(urls["input-" + i]);
    }
    v["pictureUrls"] = pictures;
    setVehicle(v);
  };

  const handleChange = ({ target }) => {
    const v = { ...vehicle };
    if (target.value === "true") {
      v[target.name] = true;
    } else if (target.value === "false") {
      v[target.name] = false;
    } else {
      v[target.name] = target.value;
    }
    setVehicle(v);
  };

  const handleChangeNumber = ({ target }) => {
    const v = { ...vehicle };

    v[target.name] = parseInt(target.value);

    setVehicle(v);
  };

  const handleSubmit = async (evt) => {
    evt.preventDefault();
    dispatch(await addVehicles(vehicle));
  };

  return (
    <div>
      <h2>Add New Vehicle</h2>
      <hr /><br/>
      <form onSubmit={handleSubmit}>
        <div className="row">
          {textFields.map((field) => (
            <TextAndNumberInput
              dataTestId={`field-${field.name}`}
              type="text"
              mandatory={field.required}
              uniqueKey={field.name}
              attributeName={field.name}
              handleChange={handleChange}
            />
          ))}
          {numberFields.map((field) => (
            <TextAndNumberInput
              type="number"
              dataTestId={`field-${field.name}`}
              mandatory={field.required}
              uniqueKey={field.name}
              attributeName={field.name}
              handleChange={handleChangeNumber}
            />
          ))}

          {checkBoxButtonFields.map((field) => (
            <CheckboxButtonInput
              type="checkbox"
              mandatory={field.required}
              dataTestId={`field-${field.name}`}
              uniqueKey={field.name}
              attributeName={field.name}
              options={field.options}
              handleChange={handleCheckboxChange}
            />
          ))}
          {radioButtonFields.map((field) => (
            <RadioButtonInput
              uniqueKey={field.name}
              type="radio"
              mandatory={field.required}
              attributeName={field.name}
              options={field.options}
              handleChange={handleChange}
            />
          ))}
          {pictureUrlsFields.map((field) => (
            <div className="col-md-4 col-sm-6 col-12">
              <PictureUrlsField
                dataTestId={`field-${field.name}`}
                uniqueKey={field.name}
                type="text"
                mandatory={field.required}
                attributeName={field.name}
                onDone={handlePictureUrls}
              />
            </div>
          ))}
        </div>
        <div className="row">
          <div className="col-8"></div>
          <div className="col-4 text-end ">
            <button data-testid="submitBtn" className="btn btn-primary">
              Add Vehicle
            </button>
          </div>
        </div>
      </form>
      {success && (
        <p className="text-success" data-testid="successMessage">
          Added Successfully, You will be redirected to dashboard shortly.
        </p>
      )}
      {success === false && <p className="text-danger">Error: {message}</p>}
    </div>
  );
}
