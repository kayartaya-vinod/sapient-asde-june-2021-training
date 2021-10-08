/**
@Author Yogeshwar Chaturvedi - yogeshwar.chaturvedi@publicissapient.com 
*/

import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router";
import { fetchVehicleAccessoryById } from "../../../redux/actionCreators/vehicleAccessoryActionCreator/vehicleAccessoryActionCreator";
import { addDefaultSrc } from "../../../utils/utils";
import "../../Thumbnail/Thumbnail.css";

function AccessoryThumbnail() {
  const dispatch = useDispatch();
  const [picture, setPicture] = useState([]);
  const [spotLightImage, setSpotLightImage] = useState("");

  const vehicleAccessory = useSelector(
    (store) => store.vehicleAccessoryReducer
  );

  const { id } = useParams();
  useEffect(() => {
    (async function () {
      dispatch(await fetchVehicleAccessoryById(id));
    })();
  }, [dispatch, id]);

  useEffect(() => {
    (() => {
      if (vehicleAccessory.pictureUrls) {
        setPicture(vehicleAccessory.pictureUrls.slice(0, 5));
      }
    })();
  }, [vehicleAccessory]);

  useEffect(() => {
    (() => {
      setSpotLightImage("");
    })();
  }, []);

  useEffect(() => {
    (() => {
      setSpotLightImage(picture[0]);
    })();
  }, [vehicleAccessory, picture]);

  const handleMouseOver = (image) => {
    setSpotLightImage(image.target.src);
  };
  return (
    <>
      <div className="row">
        <div className="col text-center">
          <img
            src={spotLightImage}
            alt={spotLightImage}
            className="medium-image"
            data-testid="medium-image"
            onError={addDefaultSrc}
          />
        </div>
      </div>
      <br></br>
      <div className="row text-center" key={"picture"}>
        <div className="col text-center" key={"picture"}>
          {picture.map((image, index) => (
            <img
              src={image}
              className="small-image img-thumbnail p-0 me-1"
              onMouseOver={handleMouseOver}
              alt={spotLightImage}
              key={index}
              data-testid="small-image"
              onError={addDefaultSrc}
            />
          ))}
        </div>
      </div>
    </>
  );
}

export default AccessoryThumbnail;
