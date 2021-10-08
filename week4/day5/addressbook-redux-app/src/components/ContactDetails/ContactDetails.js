import React, { useEffect } from 'react';
import { fetchContactById } from '../../actions/contactActions';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';

function ContactDetails({ contact, fetchContact, match: { params } }) {
    useEffect(() => {
        fetchContact(params.contactId);
    }, [params.contactId]);

    return (
        <>
            <h3>
                {contact.gender === 'M' ? 'Mr.' : 'Ms.'} {contact.name}
            </h3>
            <table className='table'>
                <tbody>
                    <tr>
                        <td>Email address</td>
                        <td>{contact.email}</td>
                    </tr>
                    <tr>
                        <td>Phone number</td>
                        <td>{contact.phone}</td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td>{contact.city}</td>
                    </tr>
                    <tr>
                        <td>State</td>
                        <td>{contact.state}</td>
                    </tr>
                </tbody>
            </table>

            <Link to={'/edit/' + contact.id} className='btn btn-primary'>
                Edit this contact
            </Link>
        </>
    );
}

// stateAsProps
const stateAsProps = (store) => ({
    contact: store.singleContactReducer,
});
const actionsAsProps = {
    fetchContact: fetchContactById,
};
export default connect(stateAsProps, actionsAsProps)(ContactDetails);
