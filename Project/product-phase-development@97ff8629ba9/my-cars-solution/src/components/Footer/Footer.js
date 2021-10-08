import React from "react";
import "./Footer.css";

export default function Footer() {
  return (
    <div className="custom-footer">
      <footer className="footer fixed-bottom bg-light  text-center d-inline">
        <div className="row">
          <div className="container-fluid col-md-4"></div>
        <div className="container-fluid col-md-4">
          <span>Copyright &copy; my car solutions - 2021</span>
        </div>
        <div className="container-fluid col-md-4 translate">
        <div id="google_translate_element"></div>
        </div>
        </div>
      </footer>
    </div>
  );
}
