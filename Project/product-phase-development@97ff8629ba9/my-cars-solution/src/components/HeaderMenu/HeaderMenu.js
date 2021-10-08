import React from "react";

export default function HeaderMenu ({ children = [], text = "" }) {
  if (typeof children[Symbol.iterator] !== 'function') {
    children = [children];
  }

  return (
    <>
      <li className="nav-item dropdown">
        <span className="dropdown-toggle navbar-brand" data-testid="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
          {text}
        </span>
        <ul className="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
          {[...children].map((obj, i) => <li key={i}> {obj} </li>)}
        </ul>
      </li>
    </>
  );
}
