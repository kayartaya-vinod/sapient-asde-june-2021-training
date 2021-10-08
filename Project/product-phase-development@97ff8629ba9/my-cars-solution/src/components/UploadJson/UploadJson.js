/**
@Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
*/
import { React ,  useState,useCallback } from "react";
import { uploadFile } from "../../redux/actionCreators/uploadJsonActionCreator/uploadJsonActionCreator";
import ModalAttributes from "./ModalAttributes";
import { useDropzone }  from "react-dropzone"
import "./UploadJson.css";

function UploadJson(props) {
  const [file, setfile] = useState("");
  const [msg ,  setMsg] = useState("Select a json file");
  const [className ,  setClassName] = useState("");

  const forSuccess = (message) => {
    setClassName("text-success");
    setMsg(message);
  }
  const forFailure = message => {
    setClassName("text-danger");
    setMsg(message);
  }
  const submitHandler = async (evt) => {
    evt.preventDefault();
    if (file === "") {
      setMsg("Select a file before uploading");
      setClassName("text-warning");
    } else {
      let data = new FormData();
      data.append("jsonfile" ,  file);
      data.append("name", file.name);
      const { payload } = await uploadFile(data);
      payload === "File uploaded successfully"?forSuccess(payload):forFailure(payload);
      setfile("");
    }
    setTimeout(() => { setMsg("Select a json file"); setClassName("")} ,  10000);
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
        <div
          {...getRootProps({ className: "dropzone rectangle-drop-box" })}
        >
          <input {...getInputProps()} data-testid="uploadFile" />
          <p>Drag 'n' drop json file here, or click to select file.</p>
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
    </div>
  );
}

export default UploadJson;