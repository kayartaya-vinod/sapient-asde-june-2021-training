import React from "react";
import { Link } from "react-router-dom";

export default function MenuItem ({ linkTo, testId = "", text }) {
  return (
    <>
      <li className="nav-item">
        <Link to={linkTo} className="nav-link" data-testid={testId}>
          {text}
        </Link>
      </li>
    </>
  );
}
