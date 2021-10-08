import React, { useState, useRef } from "react";
import { signup } from "../../redux/actionCreators/signupActionCreator/signupActionCreator";
import { siteKey } from "../../constants/siteKey";
import ReCAPTCHA from "react-google-recaptcha";
import { SIGNUP_FAILED } from "../../redux/actionTypes";
import { passwordStrengthCheck } from "../../utils/utils";
import "./Register.css";
import { Link } from "react-router-dom";
import sanitize from "mongo-sanitize";
const re =
  /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

export default function Register() {
  const recaptchaRef = useRef();

  const [recpaptchaMsg, setRecpaptchaMsg] = useState("");
  const validateInfo = (value) => {
    let e = {};

    if (!value.firstName) {
      e.firstname = "First name is required";
    }
    if (!value.email) {
      e.email = "Email required";
    } else if (!re.test(value.email)) {
      e.email = "Email address is invalid";
    }
    if (!value.password) {
      e.pswd = "Field is required";
    } else if (!passwordStrengthCheck(value.password)) {
      e.pswd = "Please enter password according to instruction";
    }
    if (!value.confirmPassword) {
      e.confirmpswd = "Field is required";
    } else if (value.confirmPassword !== value.password) {
      e.confirmpswd = "Passwords do not match";
    }
    if (!value.userType) {
      e.userType = "Usertype is required";
    }
    return e;
  };

  const [user, setUser] = useState({
    userType: "",
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    confirmPassword: "",
  });
  const [errors, setErrors] = useState({});
  const handleChange = ({ target }) => {
    const v = { ...user };
    v[target.name] = sanitize(target.value);
    setUser(v);
  };
  const onChange = () => {
    setRecpaptchaMsg("");
  };
  const [emailConfirmMsg, setEmailConfirmMsg] = useState("");
  const [emailErrorMsg, setEmailErrorMsg] = useState("");
  const handleSubmit = async (evt) => {
    evt.preventDefault();
    setEmailConfirmMsg("");
    setEmailErrorMsg("");
    const recaptchaValue = recaptchaRef.current.getValue();
    let errorInForm = validateInfo(user);
    setErrors(errorInForm);
    if (
      !errorInForm.firstname &&
      !errorInForm.email &&
      !errorInForm.userType &&
      !errorInForm.pswd &&
      !errorInForm.confirmpswd &&
      recaptchaValue
    ) {
      const { confirmPassword, ...currentUser } = user;
      const finalCurrentUser = {
        firstName: sanitize(currentUser.firstName),
        lastName: sanitize(currentUser.lastName),
        email: sanitize(currentUser.email),
        password: sanitize(currentUser.password),
      };
      if (currentUser.userType === "D") {
        finalCurrentUser.userType = "dealer";
      } else {
        finalCurrentUser.userType = "customer";
      }
      const data = await signup(finalCurrentUser);
      if (data.type === SIGNUP_FAILED) {
        setEmailErrorMsg(data.payload);
        user.email="";
        setUser({ ...user });
      } else {
        setEmailConfirmMsg(
          "Hello " +
            data.payload.name +
            ", Email confirmation link has been sent to the your mail."
        );
        setUser({
          userType: "",
          firstName: "",
          lastName: "",
          email: "",
          password: "",
          confirmPassword: "",
        });
      }
    } else {
      if (!recaptchaValue) {
        setRecpaptchaMsg("Confirm not a robot");
      }
      setUser({ ...user });
    }
  };

  return (
    <div data-testid="register">
      <div className="container">
        <div className="card p-3">
          <div className="card-body">
            {emailConfirmMsg.length !== 0 && (
              <p className="text-success">{emailConfirmMsg}</p>
            )}
            {emailErrorMsg.length !== 0 && (
              <p className="text-danger">{emailErrorMsg}</p>
            )}

            <h2>Create Account</h2>
            <br />
            <form onSubmit={handleSubmit}>
              <div className="form-group row mb-3">
                <label htmlFor="firstname" className="col-sm-2 col-form-label">
                  First Name
                  <span className="text-danger">*</span>
                </label>

                <div className="col-sm-10">
                  <input
                    id="firstname"
                    type="text"
                    name="firstName"
                    className="form-control "
                    placeholder="John "
                    autoFocus
                    data-testid="firstname"
                    value={user.firstName}
                    onChange={handleChange}
                  />

                  <div className="text-danger">{errors.firstname}</div>
                </div>
              </div>
              <div className="form-group row mb-3">
                <label htmlFor="lastname" className="col-sm-2 col-form-label">
                  Last Name
                </label>
                <div className="col-sm-10">
                  <input
                    id="lastname"
                    type="text"
                    name="lastName"
                    className="form-control "
                    placeholder="Doe"
                    data-testid="lastname"
                    value={user.lastName}
                    onChange={handleChange}
                  />
                </div>
              </div>
              <div className="form-group row mb-3">
                <label htmlFor="email" className="col-sm-2 col-form-label">
                  Email<span className="text-danger">*</span>
                </label>

                <div className="col-sm-10">
                  <input
                    id="email"
                    type="email"
                    name="email"
                    className="form-control "
                    placeholder="@exmple.com"
                    data-testid="email"
                    value={user.email}
                    onChange={handleChange}
                  />

                  {errors.email && (
                    <p className="text-danger">{errors.email}</p>
                  )}
                </div>
              </div>

              <div className="form-group row mb-3">
                <label htmlFor="UserType" className="col-sm-2 col-form-label">
                  User Type
                  <span className="text-danger">*</span>
                </label>

                <div className="col-sm-10">
                  <label>
                    <input
                      type="radio"
                      value="C"
                      checked={user.userType === "C"}
                      onChange={handleChange}
                      name="userType"
                    />
                    &nbsp; Customer &nbsp;
                  </label>
                  <label>
                    <input
                      type="radio"
                      value="D"
                      data-testid="userType"
                      checked={user.userType === "D"}
                      onChange={handleChange}
                      name="userType"
                    />
                    &nbsp; Dealer &nbsp;
                  </label>
                  {errors.email && (
                    <p className="text-danger">{errors.userType}</p>
                  )}
                </div>
              </div>

              <div className="form-group row mb-3">
                <label htmlFor="password" className="col-sm-2 col-form-label">
                  Password
                  <span className="text-danger">*</span>
                </label>
                <div className="col-sm-10">
                  <input
                    id="password"
                    type="password"
                    name="password"
                    className="form-control"
                    placeholder="Password"
                    data-testid="password"
                    value={user.password}
                    onChange={handleChange}
                  />

                  {errors.pswd && <p className="text-danger">{errors.pswd}</p>}
                </div>
              </div>

              <div className="form-group row mb-3">
                <label
                  htmlFor="confirm-password"
                  className="col-sm-2 col-form-label"
                >
                  Confirm Password
                  <span className="text-danger">*</span>
                </label>
                <div className="col-sm-10">
                  <input
                    id="confirmPassword"
                    type="password"
                    name="confirmPassword"
                    className="form-control"
                    placeholder="Confirm password"
                    data-testid="confirmpassword"
                    value={user.confirmPassword}
                    onChange={handleChange}
                  />

                  {errors.confirmpswd && (
                    <p className="text-danger">{errors.confirmpswd}</p>
                  )}
                </div>
              </div>
              <div className="form-group row mb-3 col-xs-12">
                <div className=" col-md-2 col-form-label col-xs-12"></div>
                <div className="col-5 col-form-label" data-testid="captcha">
                  <div className="g-recaptcha">
                    <ReCAPTCHA
                      ref={recaptchaRef}
                      sitekey={siteKey}
                      onChange={onChange}
                    />
                    {recpaptchaMsg.length !== 0 && (
                      <p className="text-danger">{recpaptchaMsg}</p>
                    )}
                  </div>
                  <div className="submitbutton my-2 col-xs-12 d-flex">
                    {/*<input
                      type="submit"
                      value="Register"
                      className="btn btn-success"
                     
                    />*/}
                    <button
                      data-testid="btn-submit"
                      className="btn btn-primary"
                    >
                      Register
                    </button>
                    &nbsp; &nbsp;
                    <div className="col-md-5 col-xs-12 p-2">
                      <Link to="/login">Login here</Link>
                    </div>
                  </div>
                </div>
                <div className="col-md-5 col-form-label col-xs-12">
                  Password must contain atleast :
                  <div>
                    <ul>
                      <li>One lower case english alphabet [a-z]</li>
                      <li>One upper case english alphabet [A-Z]</li>
                      <li>One special character [!@#$%^&*]</li>
                      <li>One digit [0-9]</li>
                      <li>Length of 8 characters</li>
                    </ul>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
