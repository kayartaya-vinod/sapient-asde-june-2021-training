import React from 'react';
import ContactCard from '../ContactCard/ContactCard';

export default function ContactList({ contacts, deleteContact }) {
    // array of JSX, each element in the array of JSX must have unique key property
    const list = contacts.map((c) => (
        <li className='list-group-item' key={c.id}>
            <ContactCard contact={c} deleteContact={deleteContact} />
        </li>
    ));

    return (
        <>
            <ul data-testid='list' className='list-group'>
                {list}
            </ul>
        </>
    );
}
