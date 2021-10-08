import React from 'react';
import { Link } from 'react-router-dom';
import ContactCount from '../ContactCount/ContactCount';
export default function Header() {
    return (
        <>
            <div className='alert alert-primary'>
                <div className='container'>
                    <h1>Address book</h1>
                </div>
            </div>
            <div className='container'>
                <div className='row'>
                    <div className='col-8'>
                        <Link to='/'>New contact</Link>
                    </div>
                    <div className='col-4 text-end'>
                        <ContactCount />
                    </div>
                </div>
            </div>
            <hr />
        </>
    );
}
