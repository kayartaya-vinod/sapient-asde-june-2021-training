import React from 'react';

// <ContactCard contact={c} deleteContact={fn} />

export default function ContactCard({ contact, deleteContact }) {
    return (
        <div>
            <div className='row'>
                <div className='col-11'>
                    <h4>
                        {contact.gender === 'Male' ? 'Mr.' : 'Ms.'}{' '}
                        {contact.name}
                    </h4>
                </div>
                <div className='col-1'>
                    <button
                        data-testid='btn-delete'
                        className='btn btn-link'
                        style={{ textDecoration: 'none' }}
                        onClick={() => deleteContact(contact.id)}
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
