/**
@Author Jaswant Arya - jaswant.arya@publicissapient.com
*/

import React, { useEffect, useState } from "react";
import "./Rating.css";
import { FaStar } from "react-icons/fa";
import {
  updateRating,
  getRating,
} from "../../redux/actionCreators/ratingActionCreator/ratingActionCreator.js";

const colors = {
  orange: "#FFBA5A",
  grey: "#a9a9a9",
};

const Rating = ({ vehicleId }) => {
  const [hoverValue, setHoverValue] = useState(undefined);
  const stars = Array(5).fill(0);
  const [msg, setMsg] = useState("");
  const [msg1, setMsg1] = useState("");
  const handleClick = (value) => {
    setCurrentValue(value);
  };
  const [currentValue, setCurrentValue] = useState(0);

  useEffect(() => {
    (async () => {
      const { payload } = await getRating(vehicleId);
      if (payload.success) setCurrentValue(payload.data.rating);
    })();
  }, [vehicleId]);

  const handleMouseOver = (newHoverValue) => {
    setHoverValue(newHoverValue);
  };

  const handleMouseLeave = () => {
    setHoverValue(undefined);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (currentValue === 0) {
      setMsg("Oops Zero is not a valid rating");
      setInterval(() => {
        setMsg("");
      }, 3000);
      return;
    }
    const data = await updateRating(vehicleId, currentValue);
    if (data.payload.success) {
      setMsg1("Your rating has been updated!");
      setInterval(() => {
        setMsg1("");
      }, 3000);
    } else {
      setMsg("Oops, something went wrong. Try again");
      setInterval(() => {
        setMsg("");
      }, 3000);
    }
  };

  return (
    <div style={styles.container}>
      <h3 style={{ fontWeight: "bold" }} data-testid="more-specification">
        Rating
      </h3>
      <div style={styles.stars}>
        {stars.map((_, index) => {
          return (
            <FaStar
              data-testid={"star" + index}
              key={index}
              size={24}
              onClick={() => handleClick(index + 1)}
              onMouseOver={() => handleMouseOver(index + 1)}
              onMouseLeave={handleMouseLeave}
              color={
                (hoverValue || currentValue) > index
                  ? colors.orange
                  : colors.grey
              }
              style={{
                marginRight: 10,
                cursor: "pointer",
              }}
            />
          );
        })}
        <button
          onClick={handleSubmit}
          className="btn btn-primary"
          data-testid="rating-btn"
        >
          {" "}
          Submit
        </button>
      </div>

      <div className="text-danger">{msg}</div>
      <div className="text-success">{msg1}</div>
    </div>
  );
};

const styles = {
  container: {
    display: "flex",
    flexDirection: "column",
  },
  stars: {
    display: "flex",
    flexDirection: "row",
  },

  button: {
    border: "1px solid #a9a9a9",
    borderRadius: 2,
    width: 70,
    padding: 5,
  },
};

export default Rating;
