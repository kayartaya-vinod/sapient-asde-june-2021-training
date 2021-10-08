/**
@Author Manvendra Singh manvendra.singh@publicissapient.com
*/

import React, { useState, useEffect } from "react";
import StarRatings from "react-star-ratings";
import { getAverageRating } from "../../redux/actionCreators/averageRatingActionCreator/averageRatingActionCreator";
function AverageRating({ vehicleId }) {
  const [averageRating, setAverageRating] = useState(0);
  const [totalCustomers, setTotalCustomers] = useState(0);
  useEffect(() => {
    (async () => {
      const { payload } = await getAverageRating(vehicleId);
      if (payload.success) {
        setAverageRating(payload.averageRating);
        setTotalCustomers(payload.totalCustomer);
      }
    })();
  }, [vehicleId]);
  if (averageRating !== 0) {
    return (
      <div className="text-center">
        <StarRatings
          rating={averageRating}
          starRatedColor="orange"
          starEmptyColor="grey"
          numberOfStars={5}
          data-testid="stars"
          name="rating"
          starSpacing="1px"
          starDimension="40px"
        />
        <span id="acrCustomerReviewText">{totalCustomers} Ratings</span>
        <span>
          <p>
            {averageRating} out of {5} stars
          </p>
        </span>
      </div>
    );
  } else {
    return (
      <div className="">
        <p>No Ratings Yet</p>
      </div>
    );
  }
}
export default AverageRating;
