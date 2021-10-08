/**
@Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
*/
import {React,useState} from 'react';
import { BsInfoCircleFill, BsDownload } from "react-icons/bs";
import { Modal, Button } from "react-bootstrap";
import MyFile from "./sample.csv"

function ModalAttributes(props) {
    
 const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
    return (
      <div>
        <h2 className="d-inline">
          Upload JSON File&ensp;
        </h2>
            <a href={MyFile} download="sample.json" style={{textDecoration:"none"}} data-testid="sampleDownload">
          ( sample.json <BsDownload /> )
        </a>
        <BsInfoCircleFill onClick={handleShow} data-testid="instruction"/>
        <hr />
        <Modal
          show={show}
          onHide={handleClose}
          size="lg"
          aria-labelledby="example-modal-sizes-title-lg"
        >
          <Modal.Header>
            <Modal.Title>Instructions</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            * If the Vehicle Accessory Id is already present , it will update the data.If
            not , it will store the data.
            <br />
            * You can add multiple Vehicle Accessories within a single Json file
            <br />
            * Refer to sample.json for getting a idea on json format
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleClose} data-testid="closeButton">
              Close
            </Button>
          </Modal.Footer>
        </Modal>
      </div>
    );
}

export default ModalAttributes;