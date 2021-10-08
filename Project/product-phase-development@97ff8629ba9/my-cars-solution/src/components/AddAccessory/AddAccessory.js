/**
@Author Sumitesh Naithani sumitesh.naithani@publicissapient.com
*/
import React, { useEffect, useState } from "react";
import {
  numberFields,
  textFields,
  pictureUrlsFields,
} from "../../constants/accessoryAttributes";
import { initialAccessoryState } from "../../utils/initialAccessoryState";
import { addAccessory } from "../../redux/actionCreators/addAccessoryActionCreator/addAccessoryActionCreator";
import { useSelector, useDispatch } from "react-redux";
import { useHistory } from "react-router";
import { RESET_ADD_ACCESSORY } from "../../redux/actionTypes";
import AddAttribute from "./AddAttribute";
import TextAndNumberInput from "../Inputs/TextAndNumberInput/TextAndNumberInput";
import PictureUrlsField from "../Inputs/TextAndNumberInput/PictureUrlsField";
export default function AddAccessory() {
  const [accessory, setAccessory] = useState(initialAccessoryState);
  const { success, message } = useSelector(
    (store) => store.addAccessoryReducer
  );
  const history = useHistory();
  const dispatch = useDispatch();
  const [att, setAtt] = useState({});

  useEffect(() => {
    if (success) {
      setTimeout(() => {
        dispatch({ type: RESET_ADD_ACCESSORY });
        history.push("/dealer/dashboard/dealer-accessories");
      }, 3000);
    }
  }, [success, dispatch, history]);

  const handlePictureUrls = (urls) => {
    const acc = { ...accessory };
    let pictures = [];
    for (let i = 0; i < 5; ++i) {
      urls["input-" + i] && pictures.push(urls["input-" + i]);
    }
    acc["pictureUrls"] = pictures;
    setAccessory(acc);
  };

  const handleChange = ({ target }) => {
    const acc = { ...accessory };
    if (target.value === "true" || target.value === "false") {
      acc[target.name] = target.value === "true";
    } else acc[target.name] = target.value;
    setAccessory(acc);
  };

  const handleChangeNumber = ({ target }) => {
    const acc = { ...accessory };
    acc[target.name] = parseInt(target.value);
    setAccessory(acc);
  };

  const handleSubmit = async (evt) => {
    evt.preventDefault();
    const keys = Object.keys(att);
    let temp = { ...accessory };
    keys.forEach((key) => (temp[key] = att[key]));
    setAccessory(temp);
    dispatch(await addAccessory(temp));
  };

  return (
    <div>
      <h2>Add New Accessory</h2>
      <hr />
      <br />
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
          <div className="col-md-8">
            <AddAttribute setAtt={setAtt} />
          </div>
        </div>
        <div className="row">
          <div className="col-8"></div>
          <div className="col-4 text-end ">
            <button data-testid="submitBtn" className="btn btn-primary">
              Add Accessory
            </button>
          </div>
        </div>
      </form>
      {success && (
        <p className="text-success" data-testid="successMessage">
          Added Successfully, You will be redirected to accessories shortly
        </p>
      )}
      {success === false && <p className="text-danger">Error: {message}</p>}
    </div>
  );
}
