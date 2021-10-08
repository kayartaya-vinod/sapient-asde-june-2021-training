/**
@author Abhishek Singh abhishek.singh8@publicisssapient.com
*/

import React from "react";
import { useHistory } from "react-router-dom";

const PreviousNext = ({ isFirst, isLast, page, pathname }) => {
  const history = useHistory();

  const handlePrevious = () => {
    const location = {
      pathname,
      search: `?page=${parseInt(page) - 1}`,
    };
    history.push(location);
  };

  const handleNext = () => {
    const location = {
      pathname,
      search: `?page=${parseInt(page) + 1}`,
    };
    history.push(location);
  };

  return (
    <ul className="pagination mt-5 d-flex justify-content-center">
      {!isFirst && <li className="page-item me-4">
        <button
          className="btn page-link"
          onClick={handlePrevious}
          data-testid="previous-btn"
        >
          Previous
        </button>
      </li>}
      {!isLast && <li className="page-item">
        <button
          className="btn page-link"
          onClick={handleNext}
          data-testid="next-btn"
        >
          Next
        </button>
      </li>}
    </ul>
  );
};

export default PreviousNext;
