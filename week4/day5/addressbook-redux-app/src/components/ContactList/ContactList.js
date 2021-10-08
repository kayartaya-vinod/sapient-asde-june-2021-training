import React, { useEffect } from 'react';
import ContactCard from '../ContactCard/ContactCard';
import { connect } from 'react-redux';
import { fetchContacts } from '../../actions/contactActions';

function ContactList({ contacts, getAllContactsFromRestServer }) {
    useEffect(() => {
        getAllContactsFromRestServer();
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

// connect() returns a function, that takes ContactList as arguments, and
// returns a new component (higher order component)
// const hoc = connect();
// const newComp = hoc(ContactList);
// export default newComp;

// typically there are 2 parameters for the connect function
// 1. what is the state you want from the store (in our case contactReducer)
const stateAsProps = (store) => {
    return { contacts: store.contactReducer };
};
// 2. which action method you want thunk to call (in our case actions/contactActions.js#fetchContacts)
const actionsAsProps = { getAllContactsFromRestServer: fetchContacts };
// when we call getAllContactsFromRestServer(), redux will call fetchContacts()
export default connect(stateAsProps, actionsAsProps)(ContactList);
