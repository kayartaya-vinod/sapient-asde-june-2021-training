import React from 'react'
import {Link} from 'react-router-dom';

export default function ForgetPassword() {
    return (
        <>
        <Link to="/reset-password-email" data-testid="forget-password-link">Forgot password?</Link>
        </> 
    )
}
