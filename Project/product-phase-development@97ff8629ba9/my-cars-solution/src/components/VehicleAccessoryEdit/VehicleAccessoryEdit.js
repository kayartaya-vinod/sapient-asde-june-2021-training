/**
@Author Shubham Chaudhary - shubham.chaudhary@publicissapient.com 
*/

import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory, useParams } from "react-router";
import { fetchVehicleAccessoryById } from "../../redux/actionCreators/vehicleAccessoryActionCreator/vehicleAccessoryActionCreator";
import "./VehicleAccessoryEdit.css";
import { capitalizeFirstLetter, splitCamelCase } from "../../utils/utils";
import PleaseWait from "../PleaseWait/PleaseWait";
import PictureUrlsField from "../Inputs/TextAndNumberInput/PictureUrlsField";
import { updateAccessory } from "../../redux/actionCreators/updateAccessoryActionCreator/updateAccessoryActionCreator";
import { UPDATE_ACCESSORY_SUCCESS } from "../../redux/actionTypes";
import { BsX } from "react-icons/bs";
import AddAttribute from "../AddAccessory/AddAttribute";

function VehicleAccessoryEdit() {
  const dispatch = useDispatch();

  const vehicleAccessory = useSelector(
    (store) => store.vehicleAccessoryReducer
  );

  const [accessory, setAccessory] = useState({ pictureUrls: [] });
  const [wait, setWait] = useState(true);
  const [message, setMessage] = useState("");
  const [messageClass, setMessageClass] = useState("");
  const [temp, setTemp] = useState([]);
  const [att, setAtt] = useState({});

  const { id } = useParams();
  const history = useHistory();

  const mandatory = ["name", "description", "price", "pictureUrls"];

  useEffect(() => {
    (async function () {
      setWait(true);
      dispatch(await fetchVehicleAccessoryById(id));
      setWait(false);
    })();
  }, [dispatch, id]);

  useEffect(() => {
    const skip = ["id", "_id", "dealerId", "pictureUrls"];
    const keys = Object.keys(vehicleAccessory);
    setTemp(keys.filter((key) => !skip.includes(key)));
  }, [vehicleAccessory]);

  useEffect(() => {
    setAccessory(vehicleAccessory);
  }, [vehicleAccessory]);

  const handleChange = ({ target: { value, name } }) => {
    const updatedAccessory = { ...accessory };
    if (name === "price") {
      updatedAccessory[name] = parseFloat(value);
      setAccessory(updatedAccessory);
      return;
    }
    updatedAccessory[name] = value;
    setAccessory(updatedAccessory);
  };

  const handleSubmit = async () => {
    const keys = Object.keys(att);
    let temp2 = { ...accessory };
    keys.forEach((key) => (temp2[key] = att[key]));
    setAccessory(temp2);
    const result = await updateAccessory(temp2, id);
    if (result.type === UPDATE_ACCESSORY_SUCCESS) {
      setMessageClass("success");
      setMessage("Success! redirecting...");
      setTimeout(() => {
        setMessage("");
        history.push("/dealer/dashboard/dealer-accessories");
      }, 3000);
    } else {
      setMessageClass("danger");
      setMessage(result.payload || "Update Failed");
    }
  };

  const handleDeleteElement = (attribute) => {
    let newAccessory = { ...accessory };
    delete newAccessory[attribute];

    setAccessory(newAccessory);
    let inputsArray = [...temp];
    const index = inputsArray.indexOf(attribute);
    if (index > -1) {
      inputsArray.splice(index, 1);
      setTemp(inputsArray);
    }
  };

  const out = temp.map((key, index) => (
    <tr key={key} data-testid={`row${index}`}>
      <td>
        {splitCamelCase(capitalizeFirstLetter(key))}:{" "}
        <strong className="text-danger">
          {mandatory.includes(key) && "*"}
        </strong>
      </td>
      <td>
        <input
          value={accessory[key]}
          name={key}
          onChange={handleChange}
          data-testid={`input-${key}`}
        />
        {mandatory.includes(key) ? null : (
          <span
            onClick={() => handleDeleteElement(key)}
            data-testId={`deleteBtn-${key}`}
          >
            <BsX size="2em" color="red" />
          </span>
        )}
      </td>
    </tr>
  ));

  const handlePictureUrls = (urls) => {
    const newAccessory = { ...accessory };

    let pictures = [];
    for (let i = 0; i < 5; ++i) {
      urls["input-" + i] && pictures.push(urls["input-" + i]);
    }
    newAccessory["pictureUrls"] = pictures;
    setAccessory(newAccessory);
  };

  return wait ? (
    <div data-testid="pleaseWaitEdit">
      <PleaseWait />
    </div>
  ) : (
    <>
      <h4 data-testid="editAccessoryHeading" className="text-center mb-5">
        Edit Vehicle Accessory
      </h4>
      <div className="row">
        <div className="col-xs-12 col-md-12 details">
          <table className="table table-hover table-striped ">
            <tbody>{out}</tbody>
          </table>
          <br />
          <hr />
          <PictureUrlsField
            attributeName="Pictures"
            onDone={handlePictureUrls}
            initialValues={accessory["pictureUrls"]}
          />
          <br />
          <hr />
          <AddAttribute setAtt={setAtt} />
          <br />
          <hr />
          <div className="text-end">
            <button
              className="btn btn-primary text-end"
              onClick={handleSubmit}
              data-testid="submitBtn"
            >
              Submit
            </button>
          </div>
          <div data-testid="message" className={`text-${messageClass}`}>
            {message}
          </div>
        </div>
      </div>
    </>
  );
}

export default VehicleAccessoryEdit;
