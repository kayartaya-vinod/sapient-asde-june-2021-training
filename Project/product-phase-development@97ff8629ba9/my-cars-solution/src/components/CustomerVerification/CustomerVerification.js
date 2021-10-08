/**
@Author Rohit Bhatt rohit.bhatt1@publicissapient.com
*/

import React, { useEffect, useState } from "react";
import PleaseWait from "../PleaseWait/PleaseWait";
import LoginPage from "../LoginPage/LoginPage";
import { verifyCustomer } from "../../redux/actionCreators/customerVerificationActionCreator/customerVerificationActionCreator";
import { useParams } from "react-router";
import "./CustomerVerification.css";

export default function CustomerVerification() {
  const [verify, setVerify] = useState(0);
  const { token } = useParams();
  const [name, setName] = useState("");

  useEffect(() => {
    (async () => {
      let { payload } = await verifyCustomer(token);
      setName(payload.name);
      setVerify(payload.success);
    })();
  }, [token]);
  if (verify === 0) {
    return <PleaseWait />;
  } else {
    if (verify) {
      return (
        <>
          <div className="text-success top">
            <h3 data-testid="success-message">
              Congratulations {name}, your account has been verified! Login to
              your account
            </h3>
          </div>
          <LoginPage />
        </>
      );
    }
    return (
      <>
      <div className="mid text-danger" style={{marginTop:'20%', marginLeft:'14%'}} data-testid="expired-message">
        <h2 >
          This links seems to be expired or invalid, please try with a new link!
        </h2>
      </div>
      </>
    );
  }
}
