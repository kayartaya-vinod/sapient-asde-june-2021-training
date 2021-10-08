import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router";
import { getSponsoredVehicles } from "../../redux/actionCreators/sponsoredVehicleActionCreator/SponsoredVehicleActionCreator";
import { addDefaultSrc } from "../../utils/utils";
import "./SponsoredVehicle.css";

/**
 *
 * author: vikash
 */
export default function SponsoredVehicle() {
  const sponsoredVehicles = useSelector(
    (store) => store.sponsoredVehicleReducer
  );

  const dispatch = useDispatch();
  const history = useHistory();

  useEffect(() => {
    (async function () {
      dispatch(await getSponsoredVehicles());
    })();
  }, [dispatch]);

  const items = sponsoredVehicles.map((sv, index) => (
    <div
      className={`carousel-item ${index === 0 && "active"}`}
      onClick={() => history.push(`/vehicles/${sv.id}`)}
      data-testid="view_detail"
      key={index}
    >
      <div
        className="background-image"
        style={{ backgroundImage: `url(${sv.pictureUrls[0]})` }}
      ></div>
      <div className="content">
        <img
          src={sv.pictureUrls[0]}
          className="d-block w-100  custom-img"
          alt={sv.brand}
          style={{
            minWidth: "100%",
            cursor: "pointer",
            objectFit: "contain",
          }}
          onError={addDefaultSrc}
        />
        <div
          className="carousel-caption  d-md-block "
          style={{ backgroundColor: "white", opacity: 0.5,padding:0, position: "absolute",
          left: "30%",right: "30%"}}
        >
          <p style={{ fontWeight: "bold" }}>
            {sv.brand} {sv.model}
          </p>
          <p>{sv.description}</p>
        </div>
      </div>
    </div>
  ));

  return (
    <div
      id="carouselExampleCaptions"
      className="carousel slide carousel-dark p-0"
      data-bs-ride="carousel"
    >
      <div className="carousel-inner">{items}</div>
      <button
        data-bs-target="#carouselExampleCaptions"
        className="carousel-control-prev"
        data-testid="prev_vehicle"
        data-bs-slide="prev"
      >
        <span
          className="carousel-control-prev-icon"
          style={{ opacity: 0.5 }}
          aria-hidden="true"
        ></span>
      </button>
      <button
        data-bs-target="#carouselExampleCaptions"
        className="carousel-control-next"
        data-testid="next_vehicle"
        data-bs-slide="next"
      >
        <span
          className="carousel-control-next-icon"
          aria-hidden="true"
          style={{ opacity: 0.5 }}
        ></span>
      </button>
    </div>
  );
}
