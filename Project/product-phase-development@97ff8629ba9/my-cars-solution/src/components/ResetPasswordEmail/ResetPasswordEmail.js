/**
@Author <Sanchit Mahto> <sanchit.mahto@publicissapient.com>
*/

import React, { useState } from "react";
import "./ResetPasswordEmail.css";
import { verifyEmailForForgotPassword } from "../../redux/actionCreators/verifyEmailForForgotPasswordCreator/verifyEmailForForgotPasswordCreator";
import { EMAIL_VERIFIED } from "../../redux/actionTypes";
import sanitize from "mongo-sanitize";

export default function ResetPasswordEmail() {
  const [email, setEmail] = useState("");
  const [msg, setMsg] = useState("");
  const [msgClass, setMsgClass] = useState("");

  const handleChange = ({ target }) => {
    if (!validateEmail(target.value)) {
      setMsgClass("text-warning");
      setMsg("Email not valid");
    } else {
      setMsgClass("");
      setMsg("");
    }
    setEmail(target.value);
  };

  const validateEmail = (user_email) => {
    if (/^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(user_email)) {
      return true;
    }
    return false;
  };

  const handleSubmit = async (evt) => {
    evt.preventDefault();

    if (email === "") {
      setMsgClass("text-danger");
      setMsg("Please enter email");
    } else if (validateEmail(sanitize(email))) {
      const { type } = await verifyEmailForForgotPassword(email);

      if (type === EMAIL_VERIFIED) {
        setMsgClass("alert alert-dismissible alert-success");
        setMsg("Email sent, please check your inbox");
        setEmail("");
      } else {
        setMsgClass("alert alert-dismissible alert-danger");
        setMsg("Email not registered");
      }
    } else {
      setMsgClass("text-warning");
      setMsg("Email not valid");
    }
  };

  return (
    <div className=" row">
      <div className="col-3"></div>
      <div className="col email-form">
        <div className="card p-3">
          <div className="card-body">
            <h3>Forgot Password</h3>
            <form
              className="form"
              onSubmit={handleSubmit}
              data-testid="emailForm"
            >
              <div className="form-inputs">
                <div>
                  <br></br>
                  <h6>
                    We will be sending a reset password link to your email.
                  </h6>
                </div>
                <input
                  autoFocus
                  className="form-control my-3"
                  placeholder="Enter your e-mail"
                  value={email}
                  onChange={handleChange}
                  data-testid="email"
                />
              </div>
              <div className="submitbutton my-2">
                <button
                  type="submit"
                  className="btn btn-primary "
                  data-testid="submitbtn"
                >
                  Submit
                </button>
              </div>
            </form>
            <br></br>
            <div className={msgClass} data-testid="msgDiv">
              {msg}
            </div>
          </div>
        </div>
      </div>
      <div className="col-3"></div>
    </div>
  );
}
