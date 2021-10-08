/**
@Author Rohit Bhatt rohit.bhatt1@publicissapient.com
*/
import React, { useState, useEffect } from "react";

import {
  postReview,
  getReview,
} from "../../redux/actionCreators/reviewActionCreator/reviewActionCreator";
import "./Review.css";

export default function Review({ vehicleId }) {
  const [myReview, setMyReview] = useState("");
  const [msg, setMsg] = useState("");
  const [msg1, setMsg1] = useState("");
  const [prevReview, setPrevReview] = useState("");

  useEffect(() => {
    (async () => {
      const { payload } = await getReview(vehicleId);
      if (payload.success) {
        setMyReview(payload.review);
        setPrevReview(payload.review);
      }
    })();
  }, [vehicleId]);

  const handleChange = ({ target }) => {
    setMsg("");
    setMsg1("");
    setMyReview(target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (myReview === prevReview) return;

    if (myReview.length < 10 && myReview.length !== 0) {
      setMsg("The number of characters should not be less then 10");
      return;
    }
    if (myReview.length > 500) {
      setMsg("The number of characters cannot be greater than 500");
      return;
    }
    const data = await postReview(vehicleId, myReview);
    if (data.payload.success) {
      setMsg1("Your review has been added!");
      setPrevReview(myReview);
    }
  };

  return (
    <>
      <h3 style={{ fontWeight: "bold" }}>Reviews</h3>
      <form onSubmit={handleSubmit} data-testid="test-review-form">
        <textarea
          name="review-textarea"
          onChange={handleChange}
          value={myReview}
          id="user-review"
          cols="50"
          rows="4"
          placeholder="Enter your review here..."
          className="review-textarea"
          data-testid="test-review"
        />
        <button className="btn btn-primary my-3" data-testid="test-review-btn">
          Submit review
        </button>
      </form>
      <div className="text-danger">{msg}</div>
      <div className="text-success" data-testid="test-review-msg1">
        {msg1}
      </div>
    </>
  );
}
