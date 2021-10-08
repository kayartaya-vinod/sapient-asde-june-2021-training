import React from "react";
import { useDispatch } from "react-redux";
import { Link, useHistory } from "react-router-dom";
import { ROLE_CUSTOMER, ROLE_DEALER } from "../../constants/index";
import { logOut } from "../../redux/actionCreators/authActionCreator/authActionCreator";
import IfLoggedIn from "../IfLoggedIn/IfLoggedIn";
import IfNotLoggedIn from "../IfNotLoggedIn/IfNotLoggedIn";
import LoginButton from "../LoginButton/LoginButton";
import MenuItem from "../MenuItem/MenuItem";
import "./Header.css";

export default function Header () {
  const dispatch = useDispatch();
  const history = useHistory();

  const handleLogout = () => {
    dispatch(logOut());
    history.push("/login");
  };

  return (
    <nav className="navbar navbar-expand-lg fixed-top navbar-dark bg-primary px-3">
      <Link to="/" className="navbar-brand" data-testid="home-link">
        <img
          style={{ height: "50px" }}
          src="/assets/images/logo256x256.png"
          alt="My Car Solutions"
        />
        <span className="mx-2">&#124;</span>my car solutions
      </Link>
      <button
        className="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarColor01"
        aria-controls="navbarColor01"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon"></span>
      </button>

      <div className="collapse navbar-collapse " id="navbarColor01">
        <ul className="navbar-nav ms-auto">
          <IfLoggedIn role={ROLE_CUSTOMER}>
            <MenuItem linkTo="/customer/dashboard/my-account" text="Dashboard" />
            <li className="nav-item">
              <span
                style={{ cursor: "pointer" }}
                className="nav-link"
                data-testid="logout-btn"
                onClick={handleLogout}
              >
                Logout
              </span>
            </li>
          </IfLoggedIn>
          <IfLoggedIn role={ROLE_DEALER}>
            <MenuItem linkTo="/dealer/dashboard/dealer-vehicles" text="Dashboard" />
            <li className="nav-item">
              <span
                className="nav-link"
                data-testid="logout-btn"
                onClick={handleLogout}
                style={{ cursor: "pointer" }}
              >
                Logout
              </span>
            </li>
          </IfLoggedIn>

          <IfNotLoggedIn>
            <LoginButton />
            <MenuItem linkTo="/register" text="Register" />
          </IfNotLoggedIn>
        </ul>
      </div>
    </nav>
  );
}
