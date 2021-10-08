/**
@author Neha neha1@publicisssapient.com
*/
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useLocation } from "react-router-dom";
import StarRatings from "react-star-ratings";
import { getDealerReviews } from "../../redux/actionCreators/dealerReviewAcrionCreator/dealerReviewActionCreator";
import PleaseWait from "../PleaseWait/PleaseWait";
import PreviousNext from "../PreviousNext/PreviousNext";

function useQuery () {
  return new URLSearchParams(useLocation().search);
}

export default function DealerReviews () {
  const { data, isFirst, isLast } = useSelector(
    (store) => store.dealerReviewReducer
  );
  const [pleaseWait, setPleaseWait] = useState(true);
  const dispatch = useDispatch();
  const page = useQuery().get("page") || 1;
  const pathname = "/dealer/dashboard/feedbacks";

  useEffect(() => {
    (async function () {
      setPleaseWait(true);

      dispatch(await getDealerReviews(page));
      setPleaseWait(false);
    })();
  }, [dispatch, page]);

  const displayMessage = pleaseWait ? (
    <PleaseWait />
  ) : (
    <div className="container">
      <h2>No Reviews Found</h2>
    </div>
  );

  const displayReviews = data.map((reviewObj, i) => (
    <div className="border" key={i}>
      <div className="row p-2">
        <div className="col d-flex">
          <div>
            <strong>{reviewObj.userName}</strong>

            <br></br>
            <strong>{reviewObj.vehicleName}</strong>
          </div>

          {reviewObj.Feedback.rating && (
            <div className="col-md-6 ms-auto text-end">
              <StarRatings
                rating={reviewObj.Feedback.rating}
                starRatedColor="orange"
                starEmptyColor="grey"
                numberOfStars={5}
                data-testid="stars"
                name="rating"
                starSpacing="1px"
                starDimension="30px"
              />
            </div>
          )}
        </div>
      </div>

      <div className="row p-2">
        {reviewObj.Feedback.review && (
          <div className="col-12">{reviewObj.Feedback.review}</div>
        )}
      </div>
    </div>
  ));

  return (
    <>
      <h2>My Reviews</h2>
      <hr />

      {data.length === 0 && displayMessage}

      <div className="list-group">
        {data.length > 0 && displayReviews}
      </div>

      {data.length > 0 && (
        <PreviousNext
          isFirst={isFirst}
          isLast={isLast}
          page={page}
          pathname={pathname}
        />
      )}
    </>
  );
}
