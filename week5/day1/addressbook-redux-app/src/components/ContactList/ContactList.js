import React, { useEffect } from 'react';
import ContactCard from '../ContactCard/ContactCard';
import { useSelector, useDispatch } from 'react-redux';
import { fetchContacts } from '../../actions/contactActions';

export default function ContactList() {
    const contacts = useSelector((store) => store.contactReducer);
    const dispatch = useDispatch();

    useEffect(() => {
        (async () => {
            dispatch(await fetchContacts());
        })();
    }, []); // called only once

    // array of JSX, each element in the array of JSX must have unique key property
    const list = contacts.map((c) => (
        <li className='list-group-item' key={c.id}>
            <ContactCard contact={c} />
        </li>
    ));

    return (
        <div style={{ maxHeight: '90vh', overflow: 'auto' }}>
            <ul data-testid='list' className='list-group'>
                {list}
            </ul>
        </div>
    );
}
