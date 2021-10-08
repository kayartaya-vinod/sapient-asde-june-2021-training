/**
@Author <Sumitesh Naithani> <sumitesh.naithani@publicissapient.com>
*/
import React, { useState, useEffect } from "react";
import { checkEmail } from "../../redux/actionCreators/checkEmailActionCreator/checkEmailActionCreator";
import { updatePassword } from "../../redux/actionCreators/updatePasswordActionCreator/updatePasswordActionCreator";
import { useParams } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { passwordStrengthCheck } from "../../utils/utils";
import "./UpdatePassword.css";
import LoginPage from '../LoginPage/LoginPage';
import sanitize from "mongo-sanitize";

export default function UpdatePassword() {
  let { token } = useParams();
  const dispatch = useDispatch();

  const { isValidEmail, name } = useSelector(
    (store) => store.checkEmailReducer
  );

  const { isPasswordUpdated } = useSelector(
    (store) => store.updatePasswordReducer
  );

  const [errMsg, setErrMsg] = useState("");
  const [count, setCount] = useState(0);

  useEffect(() => {
    (async () => {
      dispatch(await checkEmail(token));
      setCount(1);
    })();
  }, [isValidEmail, dispatch, token, count]);

  const passwordValidation = async (event) => {
    event.preventDefault();
    let password = passwords.password;
    let confirmedPassword = passwords.confirmPassword;
    if (password !== confirmedPassword) {
      setErrMsg("Passwords do not match");
      return;
    }

    if (passwordStrengthCheck(password)) {
      const data = await updatePassword(token, sanitize(password));
      dispatch(data);
      setErrMsg("Password is strong");
    } else {
      setErrMsg("Password is not strong");
    }
  };
  const [passwords, setPasswords] = useState({
    password: "",
    confirmPassword: "",
  });

  const handleChange = ({ target }) => {
    const pass = { ...passwords };
    pass[target.name] = sanitize(target.value);
    setErrMsg("");
    setPasswords(pass);
  };
  
  const getMessage = () => {
    if (isPasswordUpdated) {
        return (
          <> 
            <h3 className="text-success text-center">
              Your password has been updated
            </h3>
            <LoginPage/>
          </>
        )
    }
    if (count > 0) return (
      <h3 className="text-danger text-center">
            This link seems to be expired or invalid. Please try with new link again.
      </h3>
    )
    return (
      <h3 className="text-primary text-center">
            Please wait for some time.
      </h3>
    )
  };

  return isValidEmail && !isPasswordUpdated ? (
    <div className="row mid-1">
      <div className="col"></div>
      <div className="col">
        <h4 className="my-4">Hello, {name}!</h4>
        <h5>Please reset password</h5>

        <form onSubmit={passwordValidation} data-testid="formid">
          <div>
            <label data-testid="xyz" htmlFor="password" className="form-label">
              New Password
            </label>
            <input
              autoFocus
              data-testid="password-test"
              type="password"
              value={passwords.password}
              onChange={handleChange}
              name="password"
              className="form-control my-3"
              id="password"
            />
          </div>
          <div>
            <label htmlFor="confirm-password" className="form-label">
              Confirm Password
            </label>
            <input
              data-testid="confirm-password-test"
              type="password"
              value={passwords.confirmPassword}
              name="confirmPassword"
              onChange={handleChange}
              className="form-control my-3"
              id="confirmPassword"
            />
          </div>
          <div>
            <button
              data-testid="change-password-test"
              className="btn btn-primary w-20 my-3"
            >
              Change Password
            </button>
          </div>
          <div>{errMsg && <p className="text-danger">{errMsg}</p>}</div>
          <div>
            <h5>Password must contain atleast :</h5>
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
        </form>
      </div>
      <div className="col"></div>
    </div>
  ) : getMessage()
}
