import { React, useCallback, useState } from "react";
import { useDropzone } from "react-dropzone";
import { uploadFile } from "../../redux/actionCreators/uploadCSVactionCreator/uploadCSVactionCreator";
import ModalAttributes from "./ModalAttributes";
import "./UploadCSV.css";

function UploadCSV(props) {
  const [file, setfile] = useState("");
  const [msg ,  setMsg] = useState("Select a CSV file");
  const [className, setClassName] = useState("");
  const [backendMessage, setBackendMessage] = useState("");

  const submitHandler = async (evt) => {
    evt.preventDefault();
    if (file === "") {
      setMsg("Select a file before uploading");
      setClassName("text-warning");
      setTimeout(() => {setMsg("Select a CSV file");setClassName("");}, 2000);
    } else {
      setMsg("Uploading, please wait!");
      let data = new FormData();
      data.append("csvfile" ,  file);
      data.append("name", file.name);
      const { payload } = await uploadFile(data);
      setMsg("Select a CSV file");
      setfile("");
      messageBoxFunctionShow(payload);
    }
  };


  const messageBoxFunctionShow = (data) => {
    if ((typeof data) == "string") {
      const element = (
        <div className="messageRow border-danger">
          <div className="message">{data}</div>
          <div className="xButton">
            <button
              data-testid="close-button"
              className="btn btn-primary"
              onClick={() => {
                setBackendMessage("");
              }}
            >
              Close
            </button>
          </div>
        </div>
      );
      setBackendMessage(element);
    } else {
      const element = (
        <div className={data['Success'] ?"messageRow border-success":"messageRow border-danger"}>
          <div className="message">
            <ul>
              <li>Success:  { data['Success'].toString()}</li>
              <li>Upload Ratio: {data["upload-ratio"]}</li>
              <li>Date and Time of Upload:  {data["date-time"]}</li>
              <li >Message:  {data["message"]} </li>
              <li>Uploaded Files:{data["document-uploaded"]} </li>
            </ul>

          </div>
          <div className="xButton">
            <button data-testid="close-button" className="btn btn-primary" onClick={() => { setBackendMessage("") }}>Close</button>
          </div>
        </div>
      );
      setBackendMessage(element);
    }
  };

  const fileChangeHandler = (evt) => {
    setfile(evt);
    setMsg(evt.name);
  };

  const onDrop = useCallback(acceptedfile => {
    fileChangeHandler(acceptedfile[0]);
  }, []);

  const { getRootProps, getInputProps } = useDropzone({onDrop});
 
  return (
    <div>
      <ModalAttributes />
      <div>
        <div {...getRootProps({ className: "dropzone rectangle-drop-box" })}>
          <input {...getInputProps()} data-testid="uploadFile" />
          <p>Drag 'n' drop some files here, or click to select file.</p>
        </div>
        <aside>
          <h4 className={className} data-testid="msg">
            {msg}
          </h4>
        </aside>
      </div>
      <div className="mb-3" style={{ cursor: "pointer" }}>
        <button
          className="btn btn-primary"
          onClick={submitHandler}
          data-testid="submitButton"
        >
          Upload File
        </button>
      </div>
      <div className="messageBox container">
          { backendMessage}
      </div>
    </div>
  );
}

export default UploadCSV;
