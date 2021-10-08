/**
@Author <Sanchit Mahto> <sanchit.mahto@publicissapient.com>
*/
import React, { useEffect, useState } from "react";
import "./Thumbnail.css";
import { useSelector } from "react-redux";
import { addDefaultSrc } from "../../utils/utils";

export default function Thumbnail () {
  let vehicle = useSelector((store) => store.singleVehicleReducer);

  const [picture, setPicture] = useState([]);
  const [spotLightImage, setSpotLightImage] = useState("");

  useEffect(() => {
    (() => {
      if (vehicle.pictureUrls) {
        setPicture(vehicle.pictureUrls.slice(0, 5));
      }
    })();
    return (async function () {
      setPicture([]);
    });
  }, [vehicle]);

  useEffect(() => {
    (() => {
      setSpotLightImage("");
    })();
    return (async function () {
      setSpotLightImage("");
    });
  }, []);

  useEffect(() => {
    (() => {
      setSpotLightImage(picture[0]);
    })();
  }, [vehicle, picture]);

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
              className="small-image img-thumbnail me-2 p-0"
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
