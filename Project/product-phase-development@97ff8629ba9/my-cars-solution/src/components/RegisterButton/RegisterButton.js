import React from 'react';
import { Link } from "react-router-dom";

export default function RegisterButton() {
    return (
      <div>
        <Link to="/Register" data-testid="register-button-link">
          <button type="button" className="btn btn-danger">
            Register
          </button>
        </Link>
      </div>
    );
}
