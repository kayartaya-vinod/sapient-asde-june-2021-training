/**
@Author Sakshi Yadav sakshi.yadav@publicissapient.com
*/


import React, { useState, useEffect } from "react";
import { fetchDealeruploads } from "../../redux/actionCreators/dealerUploadsActionCreator/dealerUploadsActionCreator";
import "./DisplayDealerUploads.css";
import { ID_KEY } from "../../constants";
import PleaseWait from "../PleaseWait/PleaseWait";

export default function DisplayDealerUploads () {
  let dealerId = localStorage.getItem(ID_KEY);
  const [uploads, setUploads] = useState(0);
  const [msg, setMsg] = useState("");
  useEffect(() => {
    (async function () {
      const { payload } = await fetchDealeruploads(dealerId);
      if (payload.success) {
        setUploads(payload.data);
        if (payload.data.length === 0) {
          setMsg("Files yet to be uploaded");
        }
      } else {
        setMsg(payload.message);
      }
    })();
  }, [dealerId]);

  if (uploads === 0 && msg === "") {
    return (
      <>
        <h2>Uploaded Files</h2>
        <hr /><PleaseWait />
      </>
    );
  }

  let count = 1;
  const uploadedFiles =
    msg !== "" ? (
      <h3 style={{ color: "red" }} data-testid="msg">{msg}</h3>
    ) : (
      <div className="container-fluid  ">
        <div className="row">
          <div className="col">

            <table className="table  table-hover  table-striped table-bordered  ">
              <tbody>
                <tr>
                  <td className="p" data-testid="S">
                    S.No
                  </td>
                  <td className="p" data-testid="File">
                    File Name
                  </td>
                  <td className="p">Date-Time</td>
                  <td className="p">Total Vehicles</td>
                  <td className="p">Success Ratio</td>
                </tr>
                {uploads.map((dealerfile) => {
                  return (
                    <tr>
                      <td className="q">{count++}</td>
                      <td className="q">{dealerfile.fileName}</td>
                      <td className="q">{dealerfile.dateAndTime}</td>
                      <td className="q">{dealerfile.noOfVehicles}</td>
                      <td className="q">{dealerfile.successRatio}</td>
                    </tr>
                  );
                })}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    );
  return (
    <>
      <h2>Uploaded Files</h2>
      <hr />
      {uploadedFiles}
    </>
  );
}
