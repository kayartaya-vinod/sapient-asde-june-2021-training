import React from 'react';
import { deleteContact } from '../../actions/contactActions';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';

// <ContactCard contact={c} deleteContact={fn} />

function ContactCard({ contact, removeContact }) {
    return (
        <div>
            <div className='row'>
                <div className='col-11'>
                    <Link
                        to={'/details/' + contact.id}
                        style={{ textDecoration: 'none' }}
                    >
                        <h4>
                            {contact.gender === 'M' ? 'Mr.' : 'Ms.'}{' '}
                            {contact.name}
                        </h4>
                    </Link>
                </div>
                <div className='col-1'>
                    <button
                        data-testid='btn-delete'
                        className='btn btn-link'
                        style={{ textDecoration: 'none' }}
                        onClick={() => removeContact(contact.id)}
                    >
                        &times;
                    </button>
                </div>
            </div>
            <p>
                Email: {contact.email} &middot; Phone: {contact.phone}
            </p>
        </div>
    );
}

export default connect(null, { removeContact: deleteContact })(ContactCard);
