import React from 'react';
import { Link } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import ContactCount from '../ContactCount/ContactCount';
import { logout } from '../../actions/contactActions';

export default function Header() {
    const loginReducer = useSelector((state) => state.loginReducer);
    const dispatch = useDispatch();

    const toDisplay = loginReducer.token ? (
        <div className='container'>
            <div className='row'>
                <div className='col-4'>
                    <Link to='/'>New contact</Link>
                </div>
                <div className='col-8 text-end'>
                    Welcome {loginReducer.user.email} -
                    <ContactCount />
                    <button
                        onClick={async () => dispatch(await logout())}
                        className='btn btn-link'
                    >
                        Logout
                    </button>
                </div>
            </div>
        </div>
    ) : null;

    return (
        <>
            <div className='alert alert-primary'>
                <div className='container'>
                    <h1>Address book</h1>
                </div>
            </div>
            {toDisplay}
        </>
    );
}
