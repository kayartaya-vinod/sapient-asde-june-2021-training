import {React,useState} from 'react';
import { BsInfoCircleFill, BsDownload } from "react-icons/bs";
import { Modal, Button } from "react-bootstrap";
import MyFile from "./sampleCSV.csv"

function ModalAttributes(props) {
    
 const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
    return (
      <div>
        <h2 className="d-inline">
          Upload CSV File&ensp;
        </h2>
            <a href={MyFile} download="sample.csv" style={{textDecoration:"none"}} data-testid="sampleDownload">
          ( sample.csv <BsDownload /> )
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
            * If the vehicleid is already present , it will update the data.If
            not , it will store the data.
            <br />
            * All this data should be in a predefined attributes.
            <br />
            * For arrays , the format will be "[abc , ...]".
            <br />
            * For objects , the format will be "abc , ...".
            <br />* 'vin' should be unique for all the documents.`
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